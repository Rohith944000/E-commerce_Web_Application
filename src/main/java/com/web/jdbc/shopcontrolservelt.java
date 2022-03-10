package com.web.jdbc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;




/**
 * Servlet implementation class shopcontrolservelt
 */
@WebServlet("/shopcontrolservelt")
public class shopcontrolservelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private shopDbUtil shopDbUtil;
	
	@Resource(name="jdbc/eshop")
	private DataSource dataSource;
    
	@Override
	public void init() throws ServletException {
		super.init();
		
		
		try {
			shopDbUtil = new shopDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //TODO Auto-generated method stub
		try {
			
			String theCommand = request.getParameter("command");
			
			if (theCommand == null) {
				theCommand = "Login";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "Login":
				loginPage(request, response);
				break;
				
			case "userlogin":
				userLogin(request, response);
				break;
				
			case "ADD":
				addUser(request, response);
				break;
				
			case "LOAD":
				loadProducts(request, response);
				break;
			
			case "Cart":
				addToCart(request, response);
				break;
			case "Remove":
				removeFromCart(request, response);
				break;
			case "addone":
				addOne(request, response);
				break;
			case "removeone":
				removeOne(request, response);
				break;
			case "CheckOut":
				CheckOut(request, response);
				break;
				
				
			default:
				loginPage(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
	}
	
	private void loginPage(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
					
			// send to JSP page (view)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/loginwindow.jsp");
			dispatcher.forward(request, response);
		}
	
	private void userLogin(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			String Name = request.getParameter("UserName");
			String Password = request.getParameter("password");
		            shopuser user = shopDbUtil.checkLogin(Name, Password);
		            Hashtable<String, item> cart = new Hashtable<>();
		            String destPage = "loginwindow.jsp";
		             
		            if (user != null) {
		                HttpSession session = request.getSession();
		                session.setAttribute("user", user);
		                session.setAttribute("cart", cart);
		                loadProducts(request, response);
		            } else {
		                String message = "Invalid email/password";
		                request.setAttribute("message", message);
		                RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
			            dispatcher.forward(request, response);
		            }
		             
		           
		}
	
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		String name = request.getParameter("Name");
		String password = request.getParameter("Password");
		String email = request.getParameter("email");		
		
		if(name == "" || password == "" || email == "" ) {
			String message = "Please enter valid data";
	        request.setAttribute("message", message);		
	        RequestDispatcher dispatcher = request.getRequestDispatcher("Register.jsp");
	        dispatcher.forward(request, response);
		}
		else {
			shopuser user = new shopuser(name, password, email);
			shopDbUtil.adduser(user);
			String message = "User Added Sucessfully";
	        request.setAttribute("message", message);		
	        RequestDispatcher dispatcher = request.getRequestDispatcher("Register.jsp");
	        dispatcher.forward(request, response);
		}
		
	}
	
	private void loadProducts(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
			String category = (String)request.getParameter("Category");
			
			if(category == null ) {
				List<product> products = shopDbUtil.getProducts();
				request.setAttribute("Product_List", products);			
				RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
				dispatcher.forward(request, response);
			}
			else {
					List<product> products = shopDbUtil.getCategoryProducts(category);
					request.setAttribute("Product_List", products);			
					RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
					dispatcher.forward(request, response);
			}
			
		}
	
	private void addToCart(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
			String ids[] = request.getParameterValues("addtocart");
			HttpSession session = request.getSession();
			Hashtable<String, item> cart = (Hashtable<String, item>)session.getAttribute("cart");
			if(ids == null && cart.isEmpty()) {
				String message = "Please select items to add in the cart!!!";
				List<product> products = shopDbUtil.getProducts();
				request.setAttribute("messages", message);	
				request.setAttribute("Product_List", products);			
				RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
				dispatcher.forward(request, response);
			}
			else {
				if(ids!=null) {
					for(int i = 0; i < ids.length;i++) {
						product tempproduct = shopDbUtil.getProductById(ids[i]);
						item temItem = new item(tempproduct,1);
						cart.put(ids[i], temItem);
					}
					session.setAttribute("cart", cart);
				}
				
				displayCart(request,response);
			}
			
		}
	
	private void removeFromCart(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
			String id = request.getParameter("productId");
			HttpSession session = request.getSession();
			Hashtable<String, item> cart = (Hashtable<String, item>)session.getAttribute("cart");
			cart.remove(id);
			session.setAttribute("cart", cart);
			displayCart(request,response);
		}
	
	private void addOne(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
			String id = request.getParameter("productId");
			HttpSession session = request.getSession();
			Hashtable<String, item> cart = (Hashtable<String, item>)session.getAttribute("cart");
			item tempItem = (item)cart.get(id);
			tempItem.addQuantity();
			cart.put(id, tempItem);
			session.setAttribute("cart", cart);
			displayCart(request,response);	
		}
	
	private void removeOne(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
			String id = request.getParameter("productId");
			HttpSession session = request.getSession();
			Hashtable<String, item> cart = (Hashtable<String, item>)session.getAttribute("cart");
			item tempItem = (item)cart.get(id);
			
			if(tempItem.getQuantity() == 1) {
				displayCart(request,response);
			}
			else {
				tempItem.decQuantity();
				cart.put(id, tempItem);
				session.setAttribute("cart", cart);
				displayCart(request,response);
			}
				
		}
	
	private void CheckOut(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		HttpSession session = request.getSession();
		Hashtable<String, item> cart = (Hashtable<String, item>)session.getAttribute("cart");
		if(cart.isEmpty()) {
			String message = "Please select items to add in the cart!!!";
			List<product> products = shopDbUtil.getProducts();
			request.setAttribute("messages", message);	
			request.setAttribute("Product_List", products);			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
			dispatcher.forward(request, response);
			}
		else {
			List<item> item = new ArrayList<item>();
			Enumeration<String> e = cart.keys();
			while (e.hasMoreElements()) {
	            String key = e.nextElement();
	            item.add(cart.get(key));
			}
			request.setAttribute("List", item);			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/checkout.jsp");
			dispatcher.forward(request, response);
			}
		}
	
	private void displayCart(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
			HttpSession session = request.getSession();
			Hashtable<String, item> cart = (Hashtable<String, item>)session.getAttribute("cart");
			List<item> item = new ArrayList<item>();
			Enumeration<String> e = cart.keys();
			while (e.hasMoreElements()) {
	            String key = e.nextElement();
	            item.add(cart.get(key));
			}
			request.setAttribute("item_List", item);			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/cartpage.jsp");
			dispatcher.forward(request, response);
		}

}

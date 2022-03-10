package com.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class shopDbUtil {

	private DataSource dataSource;

	public shopDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	public shopuser checkLogin(String userName, String password) throws Exception{
		shopuser user = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
			myConn = dataSource.getConnection();
			
			
			String sql = "SELECT * FROM user WHERE name = ? and password = ?";
			myStmt = myConn.prepareStatement(sql);
	        myStmt.setString(1, userName);
	        myStmt.setString(2, password);
			myRs = myStmt.executeQuery();
			
			
			if (myRs.next()) {
				String name = myRs.getString("name");
				String password1 = myRs.getString("password");
				String email = myRs.getString("email");
				
				
				user = new shopuser(name, password1, email);
			}
			
			close(myConn, myStmt, myRs);
			return user;
				
			// clean up JDBC objects
			
	}
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public void adduser(shopuser user) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into user " +  "(name, password, email) " + "values (?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the student
			myStmt.setString(1, user.getName());
			myStmt.setString(2, user.getPassword());
			myStmt.setString(3, user.getEmail());
			
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	public List<product> getProducts() throws Exception {
		
		List<product> products = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from product";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				String id = myRs.getString("product_id");
				String name = myRs.getString("product_name");
				double price = myRs.getDouble("product_price");
				String category = myRs.getString("category");
				
				
				product tempProduct = new product(id, name, price, category);
				
				
				products.add(tempProduct);				
			}
			
			return products;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}
	
	public List<product> getCategoryProducts(String type) throws Exception {
		List<product> products = new ArrayList<>();
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "SELECT * FROM product WHERE category = ?";
			myStmt = myConn.prepareStatement(sql);
	        myStmt.setString(1, type);
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				
				// retrieve data from result set row
				String id = myRs.getString("product_id");
				String name = myRs.getString("product_name");
				double price = myRs.getDouble("product_price");
				String category = myRs.getString("category");
				
				
				product tempProduct = new product(id, name, price, category);
				
				
				products.add(tempProduct);				
			}
			
			return products;
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}	
	}
	
	public product getProductById(String id) throws Exception {
		product product = new product();
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = dataSource.getConnection();
			
			// create sql to get selected student
			String sql = "SELECT * FROM product WHERE product_id = ?";
			myStmt = myConn.prepareStatement(sql);
	        myStmt.setString(1, id);
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				
				// retrieve data from result set row
				String pid = myRs.getString("product_id");
				String name = myRs.getString("product_name");
				double price = myRs.getDouble("product_price");
				String category = myRs.getString("category");
				
				
				product = new product(pid, name, price, category);
				
								
			}
			
			return product;
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}	
	}
	

	
}
















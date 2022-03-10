<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>

<head>
	<title>Home Page</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/shop.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>E-Shop </h2>
			
		</div>
	</div>
	<div>
		<h4>Welome ${user.getName()} (<a href="logout">Logout</a>),</h4>
	</div>
	
	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Student -->
			
			<form action="shopcontrolservelt" method="GET">
		
			<input type="hidden" name="command" value="LOAD" />
			<table>
			<tr><td>Select Category :</td></tr> 
			<tr><td><input type="radio" name="Category" value="Apparel"> Apparel</td></tr>
			<tr><td><input type="radio" name="Category" value="Electronics"> Electronics</td></tr>
			<tr><td><input type="radio" name="Category" value="Grocery"> Grocery</td></tr>
			<tr><td><input type="radio" name="Category" value="Jewellery"> Jewellery</td></tr>
			<!-- <tr><td><input type="radio" name="Category" value="All"> All</td></tr> -->
			
			</table>
			<input type="submit" value="Submit" class="save"/>
			
			</form>
			
			<form action="shopcontrolservelt" method="GET">
		
			<input type="hidden" name="command" value="Cart" />
			<table>
			
				<tr>
					<th>Select Item</th>
					<th>Product</th>
					<th>Category</th>
					<th>Price</th>
					
				</tr>
				
				<c:forEach var="tempProduct" items="${Product_List}">													
					<tr>
						<td><input type="checkbox" name="addtocart" value="${tempProduct.getId()}"></td>
						<td> ${tempProduct.getName()} </td>
						<td> ${tempProduct.getcategory()} </td>
						<td> ${tempProduct.getPrice()} </td>
						
						
					</tr>
				
				</c:forEach>
				
			</table>
			<input type="submit" value="Add to cart" class="save"/>	
			</form>
			${messages}
		</div>
	</div>
	
</body>

</html>
	
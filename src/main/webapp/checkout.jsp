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
		<h4>Welome ${user.getName()} (<a href="logout">Logout</a>),</h4><br>
		<h5>Thanks for placing order please find your itenary below, Invoice has been sent to your registerd mail id (${user.getEmail()}) </h5>
	</div>
	
	<div id="container">
	
		<div id="content">
			<form action="shopcontrolservelt" method="GET">
		
			<input type="hidden" name="command" value="Cart" />
			<table>
			
				<tr>
					<th>Product</th>
					<th>Category</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Total Price</th>
					
				</tr>
				
				<c:forEach var="item" items="${List}">												
					<tr>
						
						<td> ${item.getProduct().getName()} </td>
						<td> ${item.getProduct().getcategory()} </td>
						<td> ${item.getProduct().getPrice()} </td>
						<td> ${item.getQuantity()} </td>
						<td> ${item.totalPrice()} </td>
						
					</tr>
				
				</c:forEach>
				
			</table>
			</form>
		</div>
	</div>
	
</body>

</html>
	
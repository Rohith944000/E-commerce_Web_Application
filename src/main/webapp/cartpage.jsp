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
		
			
			<form action="shopcontrolservelt" method="GET">
		
			<input type="hidden" name="command" value="LOAD" />
			<input type="submit" value="Home" class="save"/>
			
			</form>
			
			<form action="shopcontrolservelt" method="GET">
		
			<input type="hidden" name="command" value="CheckOut" />
			<table>
			
				<tr>
					<th></th>
					<th>Product</th>
					<th>Category</th>
					<th>Price</th>
					<th>Quantity</th>
					
				</tr>
				
				<c:forEach var="item" items="${item_List}">	
					<c:url var="Removelink" value="shopcontrolservelt">
						<c:param name="command" value="Remove" />
						<c:param name="productId" value="${item.getProduct().getId()}" />
					</c:url>
					<c:url var="addone" value="shopcontrolservelt">
						<c:param name="command" value="addone" />
						<c:param name="productId" value="${item.getProduct().getId()}" />
					</c:url>
					<c:url var="removeone" value="shopcontrolservelt">
						<c:param name="command" value="removeone" />
						<c:param name="productId" value="${item.getProduct().getId()}" />
					</c:url>												
					<tr>
						<td>
						<a href="${Removelink}">Remove</a> 
						</td>
						<td> ${item.getProduct().getName()} </td>
						<td> ${item.getProduct().getcategory()} </td>
						<td> ${item.getProduct().getPrice()} </td>
						<td><a href="${addone}">+</a>  ${item.getQuantity()}  <a href="${removeone}">-</a> </td>
						
						
					</tr>
				
				</c:forEach>
				
			</table>
				<input type="submit" value="CheckOut" class="save"/>	
			</form>
		</div>
	</div>
	
</body>

</html>
	
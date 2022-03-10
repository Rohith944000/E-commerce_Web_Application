<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
	<title>Login Page</title>


	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/shop.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>E-Shop</h2>
		</div>
	</div>
	
	<div id="container">
		<h5>New User? Please register here</h5>
		<input type="button" value="Register" 
			onclick="window.location.href='Register.jsp'; return false;"
				   			class="save"/>
		
		<form action="shopcontrolservelt" method="GET">
			<input type="hidden" name="command" value="userlogin" />
			<table>
				<tbody>
					<tr>
						<td><label>User Name:</label></td>
						<td><input type="text" name="UserName"/></td>
					</tr>

					<tr>
						<td><label>Password:</label></td>
						<td><input type="password" name="password" /></td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Submit" class="save" /></td>
					</tr>
					<br>
					${message}
					<br><br>
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
	</div>
</body>

</html>












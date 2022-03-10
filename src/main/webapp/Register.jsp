<!DOCTYPE html>
<html>

<head>
	<title>Register page</title>

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
		<h3>Please enter all fields</h3>
		
		<form action="shopcontrolservelt" method="GET">
		
			<input type="hidden" name="command" value="ADD" />
			
			<table>
				<tbody>
					<tr>
						<td><label>Name:</label></td>
						<td><input type="text" name="Name" /></td>
					</tr>

					<tr>
						<td><label>Password:</label></td>
						<td><input type="text" name="Password" /></td>
					</tr>

					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>${message}</p>
		
		<p>
			<a href="shopcontrolservelt">Back to Login Page</a>
		</p>
	</div>
</body>

</html>












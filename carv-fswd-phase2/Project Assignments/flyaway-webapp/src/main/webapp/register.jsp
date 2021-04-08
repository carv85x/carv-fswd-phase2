<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<style>	
		.details {
			width: 50%;			
			margin: 0 auto;
			padding: 12px 20px;
			display: block;
		}
		
		input[type=text] {			
			width: 100%;
			padding: 12px 20px;
			margin:0 auto;
			box-sizing: border-box;
		}
		
		input[type=submit] {
			background-color: black;
			border: none;
			color: white;
			padding: 10px 20px;
			text-decoration: none;
			cursor: pointer;
			font-family:courier;
			font-size: 125%;
			font-weight: bold;	
			margin: 0 auto;
			text-align: center;
		}
		
		input[type=submit]:hover {
			background-color: #383838;
		}
		
		.submit {
		margin-top: 5%;
		float: none;
		padding: 12px 20px;	
		text-align: center;
		}		
	</style>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>
	<%@ include file="header.jsp" %>
		
		<div class="details">
		<form action="register_user" method="post">
			User ID: <input type="text" name="userId" required <% if(session.getAttribute("session_userId") != null) out.print("disabled"); %>><br><br>
			password: <input type="text" name="password" required <% if(session.getAttribute("session_userId") != null) out.print("disabled"); %>><br><br>
			Email: <input type="text" name="email" required <% if(session.getAttribute("session_userId") != null) out.print("disabled"); %>><br><br>
			Type: <select name="type">
				<option value="admin">Admin</option>
				<option value="user">User</option>
			</select>
			<div class="submit"><input type="submit" value="SUBMIT" <% if(session.getAttribute("session_userId") != null) out.print("disabled"); %>></div>		
		</form>	
		</div>
	<%@ include file="footer.jsp" %>
</body>
</html>
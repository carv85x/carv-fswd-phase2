<%@ page import="com.model.Manager" %>
<%@ page import="com.dos.UserDO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<style> 
		
		form {
			width: 100%;
	
		}
		
		input[type=text] {
			width: 30%;
			padding: 12px 20px;
			display: block;
			margin:0 auto;
			box-sizing: border-box;
		}
		
		input[type=password] {
			width: 30%;
			padding: 12px 20px;
			display: block;
			margin:0 auto;
			box-sizing: border-box;
		}
		
		input[type=submit] {
			background-color: black;
			border: none;
			color: white;
			padding-top: 5%;
			padding: 10px 18px;
			text-decoration: none;
			display: block;
			margin: 0 auto;
			cursor: pointer;
			font-family:courier;
			font-size: 125%;
			font-weight: bold;	
		}
		
		input[type=submit]:hover {
			background-color: #111111;
		}
		
		h1 {		
			font-family:courier;
			font-size: 150%;
			font-weight: bold;	
			text-align: center;
		}
		
		center {
		
			font-family:courier;
			font-size: 100%;
			font-weight: bold;	
			text-align: center;
			padding-top: 2%;
			color: red;	
		}
		
		#register {
			padding-top: 5%;
			font-family:courier;
			font-size: 100%;
			font-weight: bold;	
			text-align: center;
			margin:0 auto;
			color: black;
		}
		
	</style>
	<meta charset="ISO-8859-1">
	<title>Login</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	
	<%		
			
		if(session.getAttribute("session_userId") != null) {
			
			String userId = session.getAttribute("session_userId").toString();
			
			Manager manager = new Manager();
			
			UserDO user = manager.getUser(userId);
			
			if(user.getType().equals("admin")) {
				response.sendRedirect("admin.jsp");
			} else {
				response.sendRedirect("user.jsp");
			}
		}
	
	%>
		
	<h1>Enter your credentials to login</h1>
	<form action="authenticate" method="post">
		<input type="text" name="session_userId" placeholder="Enter Your Email or UserId"><br>
		<input type="password" name="password" placeholder="Enter Your Password"><br>
		<input type="submit" value="SUBMIT">
			
	</form>
	<div id="register">
		<a id="register" href="register.jsp">Register</a>
	</div>
		
	<%@ include file="footer.jsp" %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<style>
		#header {
			 list-style-type: none;
			 margin: 0;
			 padding: 0;
			 overflow: hidden;
			 background-color: black;
		}
		
		#home {
			 float: left;
		}
		
		#home a {
			 display: block;
			 color: white;
			 text-align: center;
			 padding: 16px;
			 text-decoration: none;
		}
		
		li a:hover {
			 background-color: #111111;
		}
		
		#login {
			float: right;
			display: block;
			color: #F4F4F4;
			text-align: center;
			padding-right: 20px;
			padding-top: 40px;
			text-decoration: none;
			font-family:courier;
			font-size: 150%;
			font-weight: bold;
		}
		
		#login:hover {
			color: #FFFFFF;
		}	
	</style>
<meta charset="ISO-8859-1">
<title>FlyAway - Header</title>
</head>
<body>
	<ul id="header">
		<li id="home"><a href="index.jsp"><img src="flyaway_home.png"></a></li>
		<%
			if(session.getAttribute("session_userId") == null)
				out.print("<li id=\"login\"><a href=\"login.jsp\">Login</a></li>");
			else
				out.print("<li id=\"login\"><a href=\"admin.jsp\">" + session.getAttribute("session_userId") + "</a></li>");
		%>
	</ul>
</body>
</html>
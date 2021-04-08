<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Authenticate App</title>
</head>
<body>
<h1> Welcome <% application.getAttribute("uName"); %></h1>

<h1> Welcome <% application.getAttribute("uName").toString(); %></h1>

<%
	String userName = application.getAttribute("uName").toString();

	out.println("<h1> Welcome " + userName + "</h1>");	
%>

</body>
</html>
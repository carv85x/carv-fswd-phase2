<%@ page import="com.model.Manager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.dos.AirlineDO" %>
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
		margin-top: 10%;
		float: none;
		padding: 12px 20px;	
		text-align: center;
		}		
	</style>
<meta charset="ISO-8859-1">
<title>Add Airport</title>
</head>
<body>
	<%@ include file="header.jsp" %>	
	
	<div class="details">
		<form action="add_airport">
		<%
			out.print("Airport ID: <input type=\"text\" name=\"airportId\" required>");
			out.print("Airport Name: <input type=\"text\" name=\"airportName\" required>");
			out.print("City: <input type=\"text\" name=\"city\" required>");
			out.print("Country: <input type=\"text\" name=\"country\" required>");	
		%>
			<div class="submit"><input type="submit" value="ADD"></div>
		</form>	
	</div>	
	<%@ include file="footer.jsp" %>	
</body>
</html>
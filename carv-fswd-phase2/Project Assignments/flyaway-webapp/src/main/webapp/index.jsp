<%@ page import="com.model.Manager" %>
<%@ page import="com.dos.AirportDO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<style> 
		
		form {
/* 			padding-top:3%; */
			width: 100%;
	
		}
		
		.loc {
			width: 20%;
			padding: 12px 20px;
/* 			display: block; */
			margin:0 auto;
			box-sizing: border-box;
		}
		
		.txt {
			font-family:courier;
			font-size: 75%;
			font-weight: bold;
			overflow: auto;	
		}
		
		.num {
			width: 11%;
			padding: 12px 20px;
/* 			display: block; */
			margin:0 auto;
			box-sizing: border-box;
		}
		
		.date {
			width: 15%;
			padding: 10px 18px;
/* 			display: block; */
			margin:0 auto;
			box-sizing: border-box;
		}
		
		input[type=submit] {
			background-color: black;
			border: none;
			color: white;
			padding-top: 10%;
			padding: 8px 16px;
			text-decoration: none;
 			display: block; 
			margin:auto;
			margin-top: 10px;
			cursor: pointer;
			font-family:courier;
			font-size: 125%;
			font-weight: bold;	
			float:none;
			text-align: center;
		}
		
		input[type=submit]:hover {
			background-color: #111111;
		}
		
		h1 {		
			font-family:courier;
			font-size: 200%;
			font-weight: bold;	
			text-align: center;
			color: black;	
		}
		
		.main {
		
			text-align: center;
			padding-top: 0%;

		}
		
	</style>
	<meta charset="ISO-8859-1">
	<title>FlyAway</title>
</head>
<body>
	<%@ include file="header.jsp" %>	
	
	<%
		// Reset number of passengers
		session.setAttribute("numOfPassengers", "0");
		
		Manager manager = new Manager();
	
		List<AirportDO> airports = manager.getAirports();		
		
	%>	

	<div class="main">
		<h1>Welcome to FlyAway</h1>
		
		<form action = "flights.jsp">
			<p class="txt">Departure</p>
			<select name="departure" class="loc">
			<%				
				for(AirportDO airport: airports) {
					out.print("<option value=\"" + airport.getAirportId() + "\" required>" + airport.getCity() + "</option>");
				}
			%>
			</select>
			<p class="txt">Arrival</p>
			<select name="arrival" class="loc">
			<%				
				for(AirportDO airport: airports) {
					out.print("<option value=\"" + airport.getAirportId() + "\" required>" + airport.getCity() + "</option>");
				}
			%>
			</select>
			<p class="txt">Travel Date</p>
			<input class="date" type="date" name="dateDeparture" placeholder="Enter Departure Date" required>
			<p class="txt">Passengers</p>
			<input class="num" type="number" name="numOfPassengers" placeholder="No. of Tickets" value="1" max="5" required>	
			<input type="submit" value="SEARCH">
		</form>	
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>
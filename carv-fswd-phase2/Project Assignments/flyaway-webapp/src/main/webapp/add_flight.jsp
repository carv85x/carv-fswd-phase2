<%@ page import="com.model.Manager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.dos.FlightDO" %>
<%@ page import="com.dos.AirportDO" %>
<%@ page import="com.dos.AirlineDO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<style>	
		.details {
			width: 90%;			
			margin: 0 auto;
			padding: 12px 20px;
			display: block;
		}
		
		input[type=text] {			
			width: 30%;
			padding: 12px 20px;
			margin:0 auto;
			box-sizing: border-box;
		}
		
		input[type=date] {			
			width: 15%;
			padding: 12px 20px;
			margin:0 auto;
			box-sizing: border-box;
		}
		
		input[type=option] {			
			width: 30%;
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
		margin-top: 0%;
		float: none;
		padding: 12px 20px;	
		text-align: center;
		}		
	</style>

<meta charset="ISO-8859-1">
<title>Add Flight</title>
</head>
<body>
	<%@ include file="header.jsp" %>	
	
	<div class="details">
		<form action="add_flight">
		<%		
			Manager manager = new Manager();
		
			List<AirlineDO> airlines = manager.getAirlines();
			List<AirportDO> airports = manager.getAirports();
		
			out.print("Flight No.: <input type=\"text\" name=\"flightNo\" required><br><br>");
			// Select for departures
			%>
			Airline: <select name="airlineId">
			<%				
				for(AirlineDO airline: airlines) {
					out.print("<option value=\"" + airline.getAirlineId() + "\" required>" + airline.getAirlineName() + "</option>");
				}
			%>
			</select>
			<br><br>
			<%
			// Select for departures
			%>
			Departure: <select name="departure">
			<%				
				for(AirportDO airport: airports) {
					out.print("<option value=\"" + airport.getAirportId() + "\" required>" + airport.getCity() + "</option>");
				}
			%>
			</select>
			<%
			out.print(" Departure Date: <input type=\"date\" name=\"dateDeparture\" required>");
			out.print(" Departure Time: <input type=\"time\" name=\"timeDeparture\" required>");	
			// Select for departures
			%>
			<br><br>
			Arrival: <select name="arrival">
			<%				
				for(AirportDO airport: airports) {
					out.print("<option value=\"" + airport.getAirportId() + "\" required>" + airport.getCity() + "</option>");
				}
			%>
			</select>
			<%
			out.print(" Arrival Date: <input type=\"date\" name=\"dateArrival\" required>");
			out.print(" Arrival Time: <input type=\"time\" name=\"timeArrival\" required>");
			out.print("<br>");
			out.print("<br>");
			out.print("Capacity: <input type=\"number\" name=\"passengerCapacity\" required>");
			out.print("<br>");
			out.print("<br>");
			out.print("Ticket Price: <input type=\"number\" name=\"ticketPrice\" required>");
		%>
			<div class="submit"><input type="submit" value="ADD"></div>
		</form>	
	</div>	
	<%@ include file="footer.jsp" %>	
</body>
</html>
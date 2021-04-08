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
<title>Update Flight</title>
</head>
<body>
	<%@ include file="header.jsp" %>	
	
	<div class="details">
		<form action="update_flight">
		<%		
			Manager manager = new Manager();
			
			FlightDO flight = manager.getFlight(Integer.valueOf(request.getParameter("flightId")));	
			List<AirlineDO> airlines = manager.getAirlines();
			List<AirportDO> airports = manager.getAirports();
			
			out.print("<input type=\"hidden\" name=\"flightId\" value=\"" + flight.getId() + "\">");
			out.print("Flight No.: <input type=\"text\" name=\"flightNo\" value=\"" + flight.getFlightNo() + "\" disabled><br><br>");
			// Select for departures
			%>
			Airline: <select name="airlineId">
			<%				
				for(AirlineDO airline: airlines) {
					if(flight.getAirlineId().equals(airline.getAirlineId()))
						out.print("<option value=\"" + airline.getAirlineId() + "\" selected=\"selected\" required>" + airline.getAirlineName() + "</option>");
					else
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
					if(flight.getDeparture().equals(airport.getAirportId()))
						out.print("<option value=\"" + airport.getAirportId() + "\" selected=\"selected\" required>" + airport.getCity() + "</option>");
					else
						out.print("<option value=\"" + airport.getAirportId() + "\" required>" + airport.getCity() + "</option>");
				}
			%>
			</select>
			<%
			out.print(" Departure Date: <input type=\"date\" name=\"dateDeparture\" value=\"" + flight.getDateDeparture() + "\" required>");
			out.print(" Departure Time: <input type=\"time\" name=\"timeDeparture\" value=\"" + flight.getTimeDeparture() + "\" required>");	
			// Select for departures
			%>
			<br><br>
			Arrival: <select name="arrival">
			<%				
				for(AirportDO airport: airports) {
					if(flight.getArrival().equals(airport.getAirportId()))
						out.print("<option value=\"" + airport.getAirportId() + "\" selected=\"selected\" required>" + airport.getCity() + "</option>");
					else
						out.print("<option value=\"" + airport.getAirportId() + "\" required>" + airport.getCity() + "</option>");
				}
			%>
			</select>
			<%
			out.print(" Arrival Date: <input type=\"date\" name=\"dateArrival\" value=\"" + flight.getDateArrival() + "\" required>");
			out.print(" Arrival Time: <input type=\"time\" name=\"timeArrival\" value=\"" + flight.getTimeArrival() + "\" required>>");
			out.print("<br>");
			out.print("<br>");
			out.print("Capacity: <input type=\"number\" name=\"passengerCapacity\" value=\"" + flight.getPassengerCapacity() + "\" required>");
			out.print("<br>");
			out.print("<br>");
			out.print("Ticket Price: <input type=\"number\" name=\"ticketPrice\" value=\"" + flight.getTicketPrice() + "\" required>");
		%>
		<%
// 			Manager manager = new Manager();
		
// 			FlightDO flight = manager.getFlight(Integer.valueOf(request.getParameter("flightId")));		
			
// 			out.print("<input type=\"hidden\" name=\"flightId\" value=\"" + flight.getId() + "\">");
// 			out.print("Flight No:  <input type=\"text\" value=\"" + flight.getFlightNo() + "\" disabled>");
// 			out.print("Airline ID: <input type=\"text\" name=\"airlineId\" value=\"" + flight.getAirlineId() + "\" required>");
// 			out.print("Departure: <input type=\"text\" name=\"departure\" value=\"" + flight.getDeparture() + "\" required>");
// 			out.print("Departure Date: <input type=\"date\" name=\"dateDeparture\" value=\"" + flight.getDateDeparture() + "\" required>");
// 			out.print("Departure Time: <input type=\"time\" name=\"timeDeparture\" value=\"" + flight.getTimeDeparture() + "\" required>");
// 			out.print("Arrival: <input type=\"text\" name=\"arrival\" value=\"" + flight.getArrival() + "\" required>");
// 			out.print("Arrival Date: <input type=\"date\" name=\"dateArrival\" value=\"" + flight.getDateArrival() + "\" required>");
// 			out.print("Arrival Time: <input type=\"time\" name=\"timeArrival\" value=\"" + flight.getTimeArrival() + "\" required>");
// 			out.print("Capactiy: <input type=\"number\" name=\"passengerCapacity\" value=\"" + flight.getPassengerCapacity() + "\" required>");
// 			out.print("Ticket Price: <input type=\"number\" name=\"ticketPrice\" value=\"" + flight.getTicketPrice() + "\" required>");
		
		%>
			<div class="submit"><input type="submit" value="UPDATE"></div>
		</form>	
	</div>	
	<%@ include file="footer.jsp" %>	
</body>
</html>
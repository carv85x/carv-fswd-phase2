<%@ page import="com.model.Manager" %>
<%@ page import="com.dos.FlightDO" %>
<%@ page import="com.dos.AirlineDO" %>
<%@ page import="com.dos.AirportDO" %>
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
		}
		
		.input1 {
			float: left;
			padding: 12px 20px;
			display: block;
		}
		
		.input2 {
			margin-top: 10%;
			float: none;
			padding: 12px 20px;	
			text-align: center;
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
		
		h1 {		
			font-family:courier;
			font-size: 150%;
			font-weight: bold;	
			text-align: center;
		}
		
		.txt {
			font-family:courier;
			font-size: 75%;
			font-weight: bold;
			overflow: auto;	
		}
		
	
	</style>

	<meta charset="ISO-8859-1">
	<title>Flight</title>
</head>
<body>
	<%@ include file="header.jsp" %>	
	
	<%
		Manager manager = new Manager();		
	
		if(request.getParameter("flightId") == null || request.getParameter("numOfPassengers") == null) {		
			response.sendRedirect("index.jsp");
		}
		else {
			
			if(request.getParameter("flightId").equals("") || request.getParameter("numOfPassengers").equals("")) {
				response.sendRedirect("index.jsp");
			} else {
				FlightDO flight = manager.getFlight(Integer.valueOf(request.getParameter("flightId")));	
				
				// Get number of passengers
				int numOfPassengers = Integer.valueOf(request.getParameter("numOfPassengers"));			
	%>
	
	<form class="details" action="passenger.jsp">

		<%	
			
			out.print("<input type=\"hidden\" name= \"flightId\" value=\"" + flight.getId() + "\">");
			out.print("<input type=\"hidden\" name= \"numOfPassengers\" value=\"" + numOfPassengers + "\">");	
			
			// Input 1 - START
			out.print("<div class =\"input1\">");			
			// Departure
			out.print("<p class=\"txt\">Departure </p><input type=\"text\" disabled value=\"" + manager.getAirportById(flight.getDeparture()).getCity() + "\">");					
			// Departure Date
			out.print("<p class=\"txt\">Departure Date</p><input type=\"text\" disabled value=\"" + flight.getDateDeparture() + "\">");			
			// Departure Time
			out.print("<p class=\"txt\">Departure Time </p><input type=\"text\" disabled value=\"" + flight.getTimeDeparture() + "\">");		
			// Input 1 - END
			out.print("</div>");
			
			// Input 1 - START
			out.print("<div class =\"input1\">");			
			// Arrival
			out.print("<p class=\"txt\">Arrival </p><input type=\"text\" disabled value=\"" + manager.getAirportById(flight.getArrival()).getCity() + "\">");			
			// Arrival Date
			out.print("<p class=\"txt\">Arrival Date </p><input type=\"text\" disabled value=\"" + flight.getDateArrival() + "\">");					
			// Arrival Time
			out.print("<p class=\"txt\">Arrival Time </p><input type=\"text\" disabled value=\"" + flight.getTimeArrival() + "\">");	
			// Input 1 - END
			out.print("</div>");

			// Input 1 - START
			out.print("<div class =\"input1\">");		
			// Ticket Price
			out.print("<p class=\"txt\">Ticket Price </p><input type=\"text\" disabled value=\"$" + flight.getTicketPrice() + "\">");			
			// No. of Passengers
			out.print("<p class=\"txt\">Passengers </p><input type=\"text\" disabled value=\"" + numOfPassengers + "\">");			
			// Total Price
			out.print("<p class=\"txt\">Total Price </p><input type=\"text\" disabled value=\"$" + flight.getTicketPrice() * numOfPassengers + "\">");
			// Input 1 - END
			out.print("</div>");
			
			// Input 1 - START
			out.print("<div class =\"input1\">");			
			// Flight No.
			out.print("<p class=\"txt\">Flight No. </p><input type=\"text\" disabled value=\"" + flight.getFlightNo() + "\">");		
			// Airline
			out.print("<p class=\"txt\">Airline </p><input type=\"text\" disabled value=\"" + manager.getAirline(flight.getAirlineId()).getAirlineName() + "\">");		
			
			// Input 2 - START
			out.print("<div class =\"input2\">");	
			out.print("<input type=\"submit\" value=\"BOOK\">");
			// Input 2 - END
			out.print("</div>");
			
			// Input 1 - END
			out.print("</div>");
			
				}
			}%>	
	</form>

	
	<%@ include file="footer.jsp" %>	
</body>
</html>
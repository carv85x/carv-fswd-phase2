<%@ page import="com.model.Manager" %>
<%@ page import="com.dos.FlightDO" %>
<%@ page import="com.dos.PassengerDO" %>
<%@ page import="com.dos.AirportDO" %>
<%@ page import="com.dos.UserDO" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirm</title>
</head>
<body>
<%@ include file="header.jsp" %>	

	<p> Flight book successfully </p>
	
	<%
		
		if(request.getParameter("flightId") != null
		&& request.getParameter("numOfPassengers") != null) {
		
			if(session.getAttribute("session_userId") != null) {
				
				Manager manager = new Manager();
				
				String flightId = request.getParameter("flightId");
				String numOfPassengers = request.getParameter("numOfPassengers");
				
	
				FlightDO flight = manager.getFlight(Integer.valueOf(flightId));
				
				String flightNo = flight.getFlightNo();
				String departure = manager.getAirportById(flight.getDeparture()).getCity();
				String dateDeparture= flight.getDateDeparture().toString();
				String timeDeparture = flight.getTimeDeparture().toString();
				String arrival = manager.getAirportById(flight.getArrival()).getCity();
				String dateArrival= flight.getDateDeparture().toString();
				String timeArrival = flight.getTimeDeparture().toString();				
				
				out.print("<div class=\"flight_details\">");
				out.print("<p> Flight No: " + flightNo +" </p>");
				out.print("<p> Departure: " + departure +" </p>");
				out.print("<p> Departure Date: " + dateDeparture +" </p>");
				out.print("<p> Departure Time: " + timeDeparture +" </p>");
				out.print("<p> Arrival: " + flightNo +" </p>");
				out.print("<p> Arrival Date: " + flightNo +" </p>");
				out.print("<p> Arrival Time: " + flightNo +" </p>");
				out.print("<p> Number of Tickets: " + numOfPassengers +" </p>");
				out.print("</div>");
				
				String email = manager.getUser(session.getAttribute("session_userId").toString()).getEmail();				
				List<PassengerDO> passengers = manager.getPassengersForConfirmation(flightNo, email);
				
				out.print("<div class=\"passenger_details\">");
				
				for(PassengerDO passenger : passengers) {
					
					out.print("<p> Passenger Details: </p>");
					out.print("<p> Passenger Name: " + passenger.getFirstName() + " " + passenger.getLastName() + " </p><br>");					
				}
				
				out.print("</div>");
				
				
			} else {
				
				response.sendRedirect("index.jsp");
			}
			
		} else {
			
			response.sendRedirect("index.jsp");
		}
		
		Manager manager = new Manager();
	
		
	
	%>
	
	
	
<%@ include file="footer.jsp" %>	
</body>
</html>
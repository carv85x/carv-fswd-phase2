<%@ page import="com.model.Manager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.dos.FlightDO" %>
<%@ page import="com.dos.AirlineDO" %>
<%@ page import="com.dos.AirportDO" %>
<%@ page import="com.dos.TripDO" %>
<%@ page import="com.dos.PassengerDO" %>
<%@ page import="com.dos.UserDO" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<style>		
		.body {
		   padding:10px;
		   padding-bottom:100px;   /* Height of the footer */
		}
		
		table {
		  font-family: arial, sans-serif;
		  border-collapse: collapse;
		  width: 100%;
		  
		}
		
		td, th {
		  border: 1px solid #dddddd;
		  text-align: left;
		  padding: 8px;
		}
		
		tr:nth-child(even) {
		  background-color: #dddddd;
		}
		
		caption {
			font-family: arial, sans-serif;
			font-size: 125%;
			font-weight: bold;
		}
	</style>
	<meta charset="ISO-8859-1">
	<title>Trips</title>
</head>
<body>
	
	<%@ include file="header.jsp" %>	
	
	<div class="body">
	<div class="table">
		<table style="width:100%">
			<caption>Flights</caption>
			<tr>
				<th>Flight No</th>
				<th>Airline ID</th>
				<th>Departure</th>
				<th>Departure Date</th>
				<th>Departure Time</th>
				<th>Arrival</th>
				<th>Arrival Date</th>
				<th>Arrival Time</th>
				<th>PassportNo</th>
				<th>Passenger Name</th>
				<th></th>
			</tr>
			<%	
				// Init. Manager instance
				Manager manager = new Manager();				
	
				try {
					
					// Get User
					UserDO user = manager.getUser(session.getAttribute("session_userId").toString());
					
					// Declare list of trips
					List<TripDO> trips;
					
					if(user.getType().equals("admin")) 
						trips = manager.getTrips();
					else 
						trips = manager.getTripsByUser(user.getEmail());										
									
					for(TripDO trip : trips) {
						
						FlightDO flight = manager.getFlightByNo(trip.getFlightNo());
						PassengerDO passenger = manager.getPassenger(trip.getPassportNo());
						
						out.print("<tr>");
						out.print("<td>" + flight.getFlightNo() + "</td>");
						out.print("<td>" + manager.getAirline(flight.getAirlineId()).getAirlineName()  + "</td>");
						out.print("<td>" + manager.getAirportById(flight.getDeparture()).getCity() + "</td>");
						out.print("<td>" + flight.getDateDeparture() + "</td>");
						out.print("<td>" + flight.getTimeDeparture() + "</td>");
						out.print("<td>" + manager.getAirportById(flight.getArrival()).getCity() + "</td>");
						out.print("<td>" + flight.getDateArrival() + "</td>");
						out.print("<td>" + flight.getTimeArrival() + "</td>");
						out.print("<td>" + trip.getPassportNo() + "</td>");
						out.print("<td>" + passenger.getFirstName() + " " + passenger.getLastName() + "</td>");
						
						// Input to update
//	 					out.print("<td>");
//	 					out.print("<form action=\"update_flight.jsp\">");
//	 					out.print("<input type=\"hidden\" name=\"flightId\" value=\"" + flight.getId() + "\">");
//	 					out.print("<input type=\"submit\" value=\"UPDATE\">");
//	 					out.print("</form>");
//	 					out.print("</td>");
						
//	 					// Input to delete
//	 					out.print("<td>");
//	 					out.print("<form action=\"delete_flight\">");
//	 					out.print("<input type=\"hidden\" name=\"flightId\" value=\"" + flight.getId() + "\">");
//	 					out.print("<input type=\"submit\" value=\"DELETE\">");
//	 					out.print("</form>");
//	 					out.print("</td>");
						
						out.print("</tr>");
					}			
					
				} catch(java.lang.NullPointerException e) {					
					response.sendRedirect("index.jsp");
				}
				
				
			%>
		</table>
	</div>
	</div>
	<%@ include file="footer.jsp" %>	
</body>
</html>
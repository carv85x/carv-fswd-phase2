<%@ page import="com.model.Manager" %>
<%@ page import="com.dos.FlightDO" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="org.hibernate.HibernateException" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<style>
		.error {
			font-family:courier;
			font-size: 100%;
			font-weight: bold;	
			text-align: center;
			padding-top: 2%;
			color: red;			
		}
		
		.flights {
			display: block;
			columns: 50%;
			padding-right: 25px;
			overflow-x:hidden;
	
		}
		
		p {
			font-family:courier;
			font-size: 90%;
			font-weight: bold;			
		}
		
		.txt {
			font-family:courier;
			font-size: 100%;
			font-weight: bold;			
		}
		
		.first_line {
			font-family:courier;
			font-size: 90%;
			float: left;
			padding-right: 50px;		
		}
				
		.btn_fline_even {
			font-family:courier;
			font-size: 100%;
			font-weight: bold;
			float: RIGHT;
			background-color: #E0FFFF;
			border: none;
			color: black;
			padding: 20px 20px;
			text-decoration: none;
			margin: 0 auto;
			cursor: pointer;		
		}
		
		.btn_fline_odd {
			font-family:courier;
			font-size: 100%;
			font-weight: bold;
			float: RIGHT;
			background-color: #B0E0E6;
			border: none;
			color: black;
			padding: 20px 20px;
			text-decoration: none;
			margin: 0 auto;
			cursor: pointer;		
		}
		
		.btn_fline_even:hover {
			font-size: 105%;		
		}
		
		.btn_fline_odd:hover {
			font-size: 105%;		
		}
		
		.even {
			background-color: #E0FFFF;
		}
		
		.odd {
			background-color: #B0E0E6;
		}
	
		
	</style>

	<meta charset="ISO-8859-1">
	<title>Flights</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	
	<%
		if(request.getParameter("departure") == null 
		|| request.getParameter("arrival") == null
		|| request.getParameter("dateDeparture") == null
		|| request.getParameter("numOfPassengers") == null) {
			
			response.sendRedirect("index.jsp");
			
		} else {
			
			if(request.getParameter("departure").equals("") 
			|| request.getParameter("arrival").equals("") 
			|| request.getParameter("dateDeparture").equals("") 
			|| request.getParameter("numOfPassengers").equals("")) {		
				
				response.sendRedirect("index.jsp");
				
			} else {
		
				// Declare Request Dispatcher
				RequestDispatcher rd;
				
				// Declare search variables
				String departure = request.getParameter("departure");
		
				String arrival = request.getParameter("arrival");
		
				String dateDeparture = request.getParameter("dateDeparture");
				int numOfPassengers = Integer.valueOf(request.getParameter("numOfPassengers"));
				boolean ignoreFullFlight = true;
				
				// Check required parameters
				if(departure.equals(arrival)) {
		
					out.println("<center class=\"error\"><span>Departure cannot be equal to Arrival</span></center>");
				} else {
					
					try {
						// Init. manager instance
						Manager manager = new Manager();			
						
						List<FlightDO> flights = manager.getFlights(departure, arrival, dateDeparture, numOfPassengers, ignoreFullFlight);
						
						// No Flight Found
						if(flights.size() == 0)
							out.print("<p class=\"txt\">No Flights Found</p>");
						
						// Switch background color
						int background = 0;
						
						// Set button background
						String btnColor;
						
						for(FlightDO flight : flights) {
							
							if(background % 2 == 0) {
								out.print("<div class=\"even\">");
								btnColor = "\"btn_fline_even\"";
							}
							else {
								out.print("<div class=\"odd\">");
								btnColor = "\"btn_fline_odd\"";						
							}
							
							// Draw list of flight details
							out.print("<ul class=\"flights\">");
							out.print("<li class=\"first_line\"><p> Airline ID: </p> " + flight.getAirlineId() + "</li>");
							out.print("<li class=\"first_line\"><p> Departure: </p>" + manager.getAirportById(flight.getDeparture()).getCity() + "</li>");
							out.print("<li class=\"first_line\"><p> Departure Date: </p>" + flight.getDateDeparture() + "</li>");
							out.print("<li class=\"first_line\"><p> Departure Time: </p>" + flight.getTimeDeparture() + "</li>");
							out.print("<li class=\"first_line\"><p> Arrival: </p>" + manager.getAirportById(flight.getArrival()).getCity() + "</li>");
							out.print("<li class=\"first_line\"><p> Arrival Date: </p>" + flight.getDateArrival() + "</li>");
							out.print("<li class=\"first_line\"><p> Arrival Time: </p>" + flight.getTimeArrival() + "</li>");
							out.print("<li class=\"first_line\"><p> Flight No.: </p>" + flight.getFlightNo() + "</li>");
							out.print("<li class=\"first_line\"><p> Ticket Price: </p>$" + flight.getTicketPrice() + "</li>");
							
							// Draw BOOK button to select flight
							out.print("<form action=\"flight.jsp\" method=\"post\">");
							out.print("<input type=\"hidden\" name=\"numOfPassengers\" value=\"" + request.getParameter("numOfPassengers") + "\">");
							out.print("<input type=\"hidden\" name=\"flightId\" value=\"" + flight.getId() + "\">");
							out.print("<input class=" + btnColor + "type=\"submit\" value=\"BOOK\">");
							out.print("</form>");
						
							out.print("</ul>");					
							out.print("<br>");
							out.print("</div>");
							
							background += 1;
							
						}	
						
						
					} catch(HibernateException e) {
						
						out.print("<span>Unable to start connection to DB</span>");
					}				
				}
			}
		}
	
	%>
	
	<%@ include file="footer.jsp" %>
</body>
</html>
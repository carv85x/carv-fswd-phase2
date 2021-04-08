<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.model.Manager" %>
<%@ page import="com.dos.UserDO" %>
<!DOCTYPE html>
<html>
<head>
<style>
	.menu {
		float: left;
		padding-left: 5%;
		overflow: visible;
	}
	
	.admin {
		float: left;
		padding-left: 10%;
		padding-top:10%;
		font-family:courier;
		font-size: 100%;
		font-weight: bold;
		color: black;
	}

</style>
<meta charset="ISO-8859-1">
<title>Admin</title>
</head>
<body>

	<%@ include file="header.jsp" %>
	
	<%
		if(session.getAttribute("session_userId") != null) {
			
			String userId = session.getAttribute("session_userId").toString();
			
			Manager manager = new Manager();
			
			UserDO user = manager.getUser(userId);
			
			if(user.getType().equals("admin")) {
				
				// Add admin links
				%>
					<div class="menu">
						<a class="admin" href="change_password.jsp">Change Password</a><br>
						<a class="admin" href="view_airports.jsp">View Airports</a><br>
						<a class="admin" href="add_airport.jsp">Add Airport</a><br>
						<a class="admin" href="view_airlines.jsp">View Airlines</a><br>
						<a class="admin" href="add_airline.jsp">Add Airline</a><br>
						<a class="admin" href="view_flights.jsp">View Flights</a><br>
						<a class="admin" href="add_flight.jsp">Add Flight</a><br>
						<a class="admin" href="view_trips.jsp">View Trips</a><br>				
					</div>
				<%
				
			} else {
				response.sendRedirect("user.jsp");
			}
			
		} else {
			response.sendRedirect("login.jsp");
		}
	
	%>
	
	<%@ include file="footer.jsp" %>

</body>
</html>
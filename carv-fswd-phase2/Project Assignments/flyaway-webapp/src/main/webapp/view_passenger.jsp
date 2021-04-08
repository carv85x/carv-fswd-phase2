<%@ page import="com.model.Manager" %>
<%@ page import="com.dos.FlightDO" %>
<%@ page import="com.dos.PassengerDO" %>
<%@ page import="com.dos.UserDO" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<style>				
		.details {
			width: 100%;			
			margin: 0 auto;
		}
		
		.input1 {
			float: left;
 			padding: 10px 15px; 
			display: block;
		}
		
		input[type=text] {
			width: 100%;
			padding: 4px 8px;
			margin:0 auto;
			box-sizing: border-box;
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
	<title>Passenger</title>
</head>
<body>
	<%@ include file="header.jsp" %>	
	
	<%			
		if(session.getAttribute("session_userId") != null) {
			
			// Init. Manager
			Manager manager = new Manager();
			
			// Get email for current user
			String email = manager.getUser(session.getAttribute("session_userId").toString()).getEmail();
			
			// Get passengers under current username
			List<PassengerDO> passengers = manager.getPassengersByEmail(email);			
			
			for(PassengerDO passenger : passengers) {
						
				out.print("<div class=\"input1\">");
				out.print("<p class=\"txt\">Passenger Details:</p>");
				out.print("<p class=\"txt\">First Name: </p><input type=\"text\" value=\"" + passenger.getFirstName() + "\" disabled>");				
				out.print("<p class=\"txt\">Last Name: </p><input type=\"text\" value=\"" + passenger.getLastName() + "\" disabled>");
				out.print("<p class=\"txt\">Email: </p><input type=\"text\" value=\"" + passenger.getEmail() + "\" disabled>");
				out.print("<p class=\"txt\">Address: </p><input type=\"text\" value=\"" + passenger.getAddress() + "\" disabled>");
				out.print("<p class=\"txt\">Gender: </p><input type=\"text\" value=\"" + passenger.getGender() + "\" disabled>");
				out.print("<p class=\"txt\">DOB: </p><input type=\"text\" value=\"" + passenger.getDateOfBirth() + "\" disabled>");
				out.print("<p class=\"txt\">Passport NO: </p><input type=\"text\" value=\"" + passenger.getPassportNo() + "\" disabled>");
				out.print("</div>");				
			}
			
		} else {
			response.sendRedirect("login.jsp");
		}	
	%>
	
	<%@ include file="footer.jsp" %>	
</body>
</html>
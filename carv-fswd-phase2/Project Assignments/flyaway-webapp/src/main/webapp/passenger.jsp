<%@ page import="com.model.Manager" %>
<%@ page import="com.dos.FlightDO" %>
<%@ page import="com.dos.PassengerDO" %>
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
			padding: 12px 20px;
			display: block;
		}
		
		.submit {
			margin-top: 25%;
			float: right;
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
	<title>Passenger</title>
</head>
<body>
	<%@ include file="header.jsp" %>	
	
	<%
		if(request.getParameter("flightId") != null
		&& request.getParameter("numOfPassengers") != null) {
			
			if(session.getAttribute("session_userId") != null) {
				
				// Init. Manager
				Manager manager = new Manager();
				
				// Init. num. of passengers
				int numOfPassengers = Integer.valueOf(request.getParameter("numOfPassengers"));
				
				// Init. form and set current flight parameters
				out.print("<form class=\"details\" action=\"register_passengers\" method=\"post\">");
				out.print("<input type=\"hidden\" name=\"numOfPassengers\" value=\"" + request.getParameter("numOfPassengers") + "\">");
				out.print("<input type=\"hidden\" name=\"flightId\" value=\"" + request.getParameter("flightId")  + "\">");
	
				// Loop form to enter passenger for each ticket
				for(int i = 0; i < numOfPassengers; i++) {
					
					// Passenger input
					%>
						<div class="input1">
							<p class="txt">Passenger <% out.print(i+1); %>:</p>
							<p class="txt">First Name: </p><input type="text" name="firstName<% out.print(i); %>" required>
							<p class="txt">Last Name: </p><input type="text" name="lastName<% out.print(i); %>"required>
							<p class="txt">Address: </p><input type="text" name="address<% out.print(i); %>"required>
							<p class="txt">Gender: </p><select name="gender<% out.print(i); %>"required>
										<option value="male">Male</option>
										<option value="female">Female</option>
										<option value="other">Other</option>
									</select>
							<p class="txt">DOB: </p><input type="date" name="dob<% out.print(i); %>"required>
							<p class="txt">Passport No: </p><input type="text" maxlength="10" name="passportNo<% out.print(i); %>"required>
							<br>
						</div>
					<%

				}
				%> 		<div class="submit"><input type="submit" value="SUBMIT"></div>
					</form> <%
				
			} else {
				session.setAttribute("currentPage", "passenger");
				response.sendRedirect("login.jsp");
			}
			
			
		} else {
			response.sendRedirect("index.jsp");
		}
	
	%>
	
	<%@ include file="footer.jsp" %>	
</body>
</html>
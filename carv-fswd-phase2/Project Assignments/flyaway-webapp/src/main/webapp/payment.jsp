<%@ page import="com.model.Manager" %>
<%@ page import="com.dos.FlightDO" %>
<%@ page import="com.dos.AirlineDO" %>
<%@ page import="com.dos.AirportDO" %>
<%@ page import="com.dos.PassengerDO" %>
<%@ page import="java.time.LocalDate" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
</head>
<body>
	
	<%@ include file="header.jsp" %>
	
	<%
		Manager manager = new Manager();
		
		if(request.getParameter("flightId") == null 
		|| request.getParameter("numOfPassengers") == null ) {
			response.sendRedirect("index.jsp");
		}
		else {
			
			if(request.getParameter("flightId").equals("") || request.getParameter("numOfPassengers").equals("")) {
				response.sendRedirect("index.jsp");
				
			} else {				
				
				FlightDO flight = manager.getFlight(Integer.valueOf(request.getParameter("flightId")));
				
				int numOfPassengers = Integer.valueOf(request.getParameter("numOfPassengers"));
				
				double total = flight.getTicketPrice() * numOfPassengers;
				
				out.println("Total: <br> <p>$" + total + "</p><br>");
	
	%>
	
	<form action="confirm.jsp" method="post">	
		<%
			// Save flightId and No of tickets to forward for confirmation
			out.print("<input type=\"hidden\" name=\"flightId\" value=\"" + request.getParameter("flightId") + "\">");
			out.print("<input type=\"hidden\" name=\"numOfPassengers\" value=\"" + request.getParameter("numOfPassengers") + "\">");
		%>
		
		Enter Payment Information:
		<br>
		Full Name: <input type="text" name="fullName" required>
		Card No: <input type="text" name="cardNo" maxlength="16" required>
		CVC: <input type="text" name="cvc" maxlength="3" required>		
		Exp. Date:
		<%
			// Set min. month and year based on current date
			String minYear = String.valueOf(LocalDate.now().getYear());
			String minMonth = String.valueOf(LocalDate.now().getMonthValue());
			if(minMonth.length() < 2)
				minMonth = "0" + minMonth;
			
			String minDate = minYear + "-" + minMonth;
			
			out.println("<input type=\"month\" name=\"expMonth\" min=\"" + minDate + "\" required>");		
		%>
		Card Type:
		<select name="ccType">
			<option value="VISA">Visa</option>
			<option value="MASTERCARD">Mastercard</option>	
		</select>
		<br>
		<br>
		Address Line 1: <input type="text" name="addrLine1" required> <br>
		Address Line 2: <input type="text" name="addrLine2" required> <br>
		City: <input type="text" name="city" required> <br>
		State: <input type="text" name="state" maxlength="2" required> <br>
		Zipcode: <input type="text" name="zipcode" maxlength="5" required> <br>
		
		<input type="submit" value="SUBMIT">
	</form>
	<%		}
		}%>
	<%@ include file="footer.jsp" %>
</body>
</html>
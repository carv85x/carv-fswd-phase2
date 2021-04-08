<%@ page import="com.model.Manager" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.dos.AirportDO" %>
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
<title>Airports</title>
</head>
<body>
	
	<%@ include file="header.jsp" %>	
	
	<div class="body">
	<div class="table">
		<table style="width:100%">
			<caption>Airports</caption>
			<tr>
				<th>Airport ID</th>
				<th>Airport Name</th>
				<th>City</th>
				<th>Country</th>
				<th></th>
				<th></th>
			</tr>
			<%	
				// Init. Manager instance
				Manager manager = new Manager();
			
				// Init. airports list
				List<AirportDO> airports = manager.getAirports();
				
				for(AirportDO airport : airports) {
					out.print("<tr>");
					out.print("<td>" + airport.getAirportId() + "</td>");
					out.print("<td>" + airport.getAirportName() + "</td>");
					out.print("<td>" + airport.getCity() + "</td>");
					out.print("<td>" + airport.getCountry() + "</td>");
					
					// Input to update
					out.print("<td>");
					out.print("<form action=\"update_airport.jsp\">");
					out.print("<input type=\"hidden\" name=\"airportId\" value=\"" + airport.getAirportId() + "\">");
					out.print("<input type=\"submit\" value=\"UPDATE\">");
					out.print("</form>");
					out.print("</td>");
					
					// Input to delete
					out.print("<td>");
					out.print("<form action=\"delete_airport\">");
					out.print("<input type=\"hidden\" name=\"airportId\" value=\"" + airport.getAirportId() + "\">");
					out.print("<input type=\"submit\" value=\"DELETE\">");
					out.print("</form>");
					out.print("</td>");
					
					out.print("</tr>");
				}			
				
			%>
		</table>
	</div>
	</div>
	
	<%@ include file="footer.jsp" %>	
</body>
</html>
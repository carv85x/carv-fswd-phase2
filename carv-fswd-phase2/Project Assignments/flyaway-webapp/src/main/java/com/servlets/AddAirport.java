package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dos.AirportDO;
import com.model.Manager;

@WebServlet("/add_airport")
public class AddAirport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd;
		
		PrintWriter out = response.getWriter();
		
		Manager manager = new Manager();
		
		String airportId = request.getParameter("airportId");
		String airportName = request.getParameter("airportName");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		
		AirportDO airport = new AirportDO(airportId, airportName, city, country);
		
		try {
			manager.addAirport(airport);
			
			response.sendRedirect("view_airports.jsp");				
			
		} catch(org.hibernate.exception.ConstraintViolationException e) {
			
			// Set RequestDispatcher
			rd = request.getRequestDispatcher("add_airport.jsp");
			
			// Set RequestDispatcher to include PW output
			rd.include(request, response);
			out.print("<span>Record already exists!</span>");
		}		

	}



}

package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dos.AirlineDO;
import com.model.Manager;

@WebServlet("/add_airline")
public class AddAirline extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd;
		
		PrintWriter out = response.getWriter();
		
		Manager manager = new Manager();
		
		String airlineId = request.getParameter("airlineId");
		String airlineName = request.getParameter("airlineName");
		String country = request.getParameter("country");
		
		AirlineDO airline = new AirlineDO(airlineId, airlineName, country);
		
		try {
			manager.addAirline(airline);
			
			response.sendRedirect("view_airlines.jsp");				
			
		} catch(org.hibernate.exception.ConstraintViolationException e) {
			
			// Set RequestDispatcher
			rd = request.getRequestDispatcher("add_airline.jsp");
			
			// Set RequestDispatcher to include PW output
			rd.include(request, response);
			out.print("<span>Record already exists!</span>");
		}		

	}



}

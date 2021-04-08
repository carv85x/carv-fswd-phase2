package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dos.FlightDO;
import com.model.Manager;

@WebServlet("/delete_flight")
public class DeleteFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Manager manager = new Manager();
		
		FlightDO flight = manager.getFlight(Integer.valueOf(request.getParameter("flightId")));
		
		manager.deleteFlight(flight);
		
		response.sendRedirect("view_flights.jsp");		
	}



}

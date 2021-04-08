package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dos.AirportDO;
import com.model.Manager;

@WebServlet("/update_airport")
public class UpdateAirport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Manager manager = new Manager();
		
		AirportDO airport = manager.getAirportById(request.getParameter("airportId"));
		
		airport.setAirportName(request.getParameter("airportName"));
		airport.setCity(request.getParameter("city"));
		airport.setCountry(request.getParameter("country"));
		
		manager.updateAirport(airport);
		
		response.sendRedirect("view_airports.jsp");		
	}



}

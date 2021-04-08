package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dos.AirlineDO;
import com.model.Manager;

@WebServlet("/update_airline")
public class UpdateAirline extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Manager manager = new Manager();
		
		AirlineDO airline = manager.getAirline(request.getParameter("airlineId"));
		
		airline.setAirlineName(request.getParameter("airlineName"));
		airline.setCountry(request.getParameter("country"));
		
		manager.updateAirline(airline);
		
		response.sendRedirect("view_airlines.jsp");		
	}



}

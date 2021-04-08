package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dos.AirlineDO;
import com.model.Manager;

@WebServlet("/delete_airline")
public class DeleteAirline extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Manager manager = new Manager();
		
		AirlineDO airline = manager.getAirline(request.getParameter("airlineId"));
		
		manager.deleteAirline(airline);
		
		response.sendRedirect("view_airlines.jsp");		
	}



}

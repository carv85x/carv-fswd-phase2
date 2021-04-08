package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dos.FlightDO;
import com.model.Manager;

@WebServlet("/add_flight")
public class AddFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd;
		
		PrintWriter out = response.getWriter();
		
		Manager manager = new Manager();
		
		String flightNo = request.getParameter("flightNo");
		String airlineId = request.getParameter("airlineId");
		String departure = request.getParameter("departure");
		Date dateDeparture = Date.valueOf(request.getParameter("dateDeparture"));
		Time timeDeparture;
		String arrival = request.getParameter("arrival");
		Date dateArrival = Date.valueOf(request.getParameter("dateArrival"));
		Time timeArrival;
		int passengerCapacity = Integer.valueOf(request.getParameter("passengerCapacity"));
		double ticketPrice = Double.valueOf(request.getParameter("ticketPrice"));
		
		// Set time in TRY/CATCH case HTML time cuts seconds off
		try {
			timeDeparture = Time.valueOf(request.getParameter("timeDeparture").toString());
		} catch(IllegalArgumentException e) {
			timeDeparture = Time.valueOf(request.getParameter("timeDeparture").toString() + ":00");
		}
		
		try {
			timeArrival = Time.valueOf(request.getParameter("timeArrival").toString());
		} catch(IllegalArgumentException e) {
			timeArrival = Time.valueOf(request.getParameter("timeArrival").toString() + ":00");
		}		
		
		FlightDO flight = new FlightDO();
		flight.setFlightNo(flightNo);
		flight.setAirlineId(airlineId);
		flight.setDeparture(departure);
		flight.setDateDeparture(dateDeparture);
		flight.setTimeDeparture(timeDeparture);
		flight.setArrival(arrival);
		flight.setDateArrival(dateArrival);
		flight.setTimeArrival(timeArrival);
		flight.setPassengerCapacity(passengerCapacity);
		flight.setTicketPrice(ticketPrice);
		
		try {
			manager.addFlight(flight);
			
			response.sendRedirect("view_flights.jsp");				
			
		} catch(org.hibernate.exception.ConstraintViolationException e) {
			
			// Set RequestDispatcher
			rd = request.getRequestDispatcher("add_flight.jsp");
			
			// Set RequestDispatcher to include PW output
			rd.include(request, response);
			out.print("<span>Record already exists!</span>");
		}		

	}



}

package com.servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dos.FlightDO;
import com.model.Manager;

@WebServlet("/update_flight")
public class UpdateFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Manager manager = new Manager();
		
		FlightDO flight = manager.getFlight(Integer.valueOf(request.getParameter("flightId")));
		
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

		flight.setAirlineId(airlineId);
		flight.setDeparture(departure);
		flight.setDateDeparture(dateDeparture);
		flight.setTimeDeparture(timeDeparture);
		flight.setArrival(arrival);
		flight.setDateArrival(dateArrival);
		flight.setTimeArrival(timeArrival);
		flight.setPassengerCapacity(passengerCapacity);
		flight.setTicketPrice(ticketPrice);
		
		manager.updateFlight(flight);
		
		response.sendRedirect("view_flights.jsp");		
	}



}

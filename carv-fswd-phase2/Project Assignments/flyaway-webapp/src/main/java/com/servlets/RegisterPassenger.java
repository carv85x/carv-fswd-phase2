package com.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dos.FlightDO;
import com.dos.PassengerDO;
import com.dos.TripDO;
import com.model.Manager;

@WebServlet("/register_passengers")
public class RegisterPassenger extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Set RequestDispatcher
		RequestDispatcher rd;
		
		// Get reference to HttpSession
		HttpSession session = request.getSession();
		
		// Get reference to previous required parameters
		String flightId = request.getParameter("flightId");
		String numOfPassengers = request.getParameter("numOfPassengers");
		
		// Get today's date
		Date today = new Date(new java.util.Date().getTime());
		
		if(flightId != null 
		&& numOfPassengers != null) {
			
			// Init. manager
			Manager manager = new Manager();
			
			// Get Flight Info
			FlightDO flight = manager.getFlight(Integer.valueOf(flightId));
			
			// Get email for current user
			String email = manager.getUser(session.getAttribute("session_userId").toString()).getEmail();
			
			// Set number of passengers
			int passengers = Integer.valueOf(numOfPassengers);
			
			// Create parameter strings
			String firstName;
			String lastName;
			String address;
			String gender;
			Date dob;
			String passportNo;
			
			// For each passenger, create each PassengerDO object
			for(int i = 0; i < passengers; i++) {
				
				// Set parameters
				firstName = request.getParameter("firstName" + i);
				lastName = request.getParameter("lastName" + i);
				address = request.getParameter("address" + i);
				gender = request.getParameter("gender" + i);
				dob = Date.valueOf(request.getParameter("dob" + i));
				passportNo = request.getParameter("passportNo" + i);
				
				try {
					// Add current passenger to DB
					manager.addPassenger(new PassengerDO(passportNo, address, dob, email, firstName, lastName, gender));
					
				} catch(org.hibernate.exception.ConstraintViolationException e) {
					
					manager.closeSession();
					
					// If it already exists, update passenger
					PassengerDO passenger = manager.getPassenger(passportNo);
					
					passenger.setFirstName(firstName);
					passenger.setLastName(lastName);
					passenger.setAddress(address);
					passenger.setGender(gender);
					passenger.setDateOfBirth(dob);
					passenger.setEmail(email);
					
					manager.updatePassenger(passenger);
					
				}
				
				// Add TripDO Object
				manager.addTrip(new TripDO(today, flight.getFlightNo(), passportNo));
			}			
			
			// Forward to payment page
			rd = request.getRequestDispatcher("payment.jsp");
			rd.include(request, response);
			
			
		} else {
			response.sendRedirect("index.jsp");
		}
		
	}

}

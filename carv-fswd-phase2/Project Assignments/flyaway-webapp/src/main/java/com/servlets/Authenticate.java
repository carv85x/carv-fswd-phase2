package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;

import com.model.Manager;

@WebServlet("/authenticate")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set RequestDispatcher
		RequestDispatcher rd;
		
		// Get login credentials
		String userId = request.getParameter("session_userId");
		String password = request.getParameter("password");
		
		// Get a reference to the PrintWriter
		PrintWriter out = response.getWriter();
		
		// Get a reference to the Session
		HttpSession session = request.getSession();
				
		try {
			
			// Get a Manager instance
			Manager manager = new Manager();
			
			// Validate credentials
			if(manager.validateUser(userId, password)) {
				// Set session attribute
				session.setAttribute("session_userId", userId);
				
				// If coming from passenger details, return to passenger page
				if(session.getAttribute("currentPage") != null) {
					if(session.getAttribute("currentPage").equals("passenger")) {
						
						
						// Redirect to passenger page
						response.sendRedirect("passenger.jsp");
					}
				} else {
					// Redirect to index
					response.sendRedirect("index.jsp");
				}				
				
			} else {
				// Set RequestDispatcher
				rd = request.getRequestDispatcher("login.jsp");
				
				// Set RequestDispatcher to include PW output
				rd.include(request, response);
				out.println("<center><span>Invalid Credentials</span></center>");				
			} 
			
		} catch(HibernateException e) {
			// Set RequestDispatcher
			rd = request.getRequestDispatcher("login.jsp");
			
			// Set RequestDispatcher to include PW output
			rd.include(request, response);
			out.println("<center><span>Cannot connect to the DB</span></center>");				
		}		
	}

}

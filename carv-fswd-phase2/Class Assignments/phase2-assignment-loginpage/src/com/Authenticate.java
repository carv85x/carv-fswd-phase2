package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/authenticate")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Set static Username and Password
		final String USER = "Camilo";
		final String PASS = "pass1234";
		
		// Request Dispatcher
		RequestDispatcher rd = null;
		
		// Set the content type
		response.setContentType("text/html");
		
		// Fetch the values from Request
		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		
		// Get a reference to the ServletContext object
		ServletContext context = getServletContext();
		
		// Get a reference to the session
		HttpSession session = request.getSession();
		
		// Check whether parameters exists in the request
		if(userName != null) {
			
			// Authenticate the userName and password
			if(userName.trim().equals(USER) && userPass.trim().equals(PASS)) {
				
				// Save the userName(coming from the request) into the session
				session.setAttribute("uName", userName);
				
				System.out.println(session.getAttribute("uName"));
				
				// Save the userName
				context.setAttribute("uName", userName);
				
				System.out.println(context.getAttribute("uName"));
				
				// Redirect to welcome page
				rd = request.getRequestDispatcher("welcome.jsp");
				rd.forward(request, response);
				
			} else {
				rd = request.getRequestDispatcher("index.html");
				PrintWriter out = response.getWriter();
				rd.include(request, response);
				out.println("<center><span style='color:red'>Invalid Username and/or Password </span></center>");		
			}
		}		
		
		
		
	}
	

	
	
	
	
}

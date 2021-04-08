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

import com.dos.UserDO;
import com.model.Manager;

@WebServlet("/register_user")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get a reference to the Session
		HttpSession session = request.getSession();
		
		// Get a reference to the PrintWriter
		PrintWriter out = response.getWriter();
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String type = request.getParameter("type");
		
//		out.print(userId + ", " + password + ", " + email + ", " + type);
		
		if(request.getParameter("session_userId") == null) {
					
			if(userId != null
			&& password != null
			&& email != null
			&& type != null) {
				
				// Set RequestDispatcher
				RequestDispatcher rd;
				
				// Init. Manager
				Manager manager = new Manager();
				
				// Create new UserDO object
				UserDO user = new UserDO(userId, email, password,  type);
				
				// Add user to DB
				try {
					manager.addUser(user);
					
					// Set session User ID
					session.setAttribute("session_userId", userId);
					
					// Set RequestDispatcher
					rd = request.getRequestDispatcher("register.jsp");
					
					// Set RequestDispatcher to include PW output
					rd.include(request, response);
					out.println("<p id=\"msg\">User record added successfully</p>");	
					
				} catch(org.hibernate.exception.ConstraintViolationException e) {
					// Set RequestDispatcher
					rd = request.getRequestDispatcher("register.jsp");
					
					// Set RequestDispatcher to include PW output
					rd.include(request, response);
					out.println("<p id=\"err\">UserId/Email already exists!</p>");	
				} 			
				
				
			} else {
				//response.sendRedirect("index.jsp");
			}
			
			
		} else {
			//response.sendRedirect("index.jsp");
		}
		
	}

}

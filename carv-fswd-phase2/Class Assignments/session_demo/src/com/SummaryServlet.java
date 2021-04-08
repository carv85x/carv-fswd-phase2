package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;

/**
 * Servlet implementation class SummaryServlet
 */
public class SummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SummaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = new User();
		// Setting it to "false" tells HttpSession that this is an existing session in {request}
		HttpSession session = request.getSession(false);
		
		// Checks if session exists
		if(session != null) {
			// Assigns existing User object into {u}
			u = (User)session.getAttribute("user");
			u.setCity(request.getParameter("city"));
			u.setContact(Long.parseLong(request.getParameter("contact")));
			
			PrintWriter out = response.getWriter();
			out.print("<h2> Hello " + u.getEmail() + " </h2>");
			out.print("<h3> Details .. " + u + " </h3>");
			out.print("<h4> Session Id is " + session.getId() + " </h4>");
			out.print("<h4> Session created at " + session.getCreationTime() + " </h2>");
			
			// Destroys session object
			session.invalidate();
		}
			
	}

}

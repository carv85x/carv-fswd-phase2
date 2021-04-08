package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.User;

/**
 * Servlet implementation class SecondServlet
 */
public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecondServlet() {
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
			u.setEmail(request.getParameter("email"));
			u.setAge(Integer.parseInt(request.getParameter("age")));
			
			session.setAttribute("user", u);
			
			response.sendRedirect("three.html");
		}
		
	}

}

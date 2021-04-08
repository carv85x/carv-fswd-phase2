package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dos.UserDO;
import com.model.Manager;

@WebServlet("/update_password")
public class UpdatePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd;
		
		PrintWriter out = response.getWriter();
		
		Manager manager = new Manager();
		
		UserDO user = manager.getUser(request.getParameter("userId"));
		
		user.setPassword(request.getParameter("password"));
		
		manager.updateUser(user);
		
		rd = request.getRequestDispatcher("change_password.jsp");
		
		rd.include(request, response);
		out.print("<p> Password updated successfully </p>");		
	}



}

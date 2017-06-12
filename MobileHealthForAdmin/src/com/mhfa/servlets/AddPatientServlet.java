package com.mhfa.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mhfa.service.AdminService;

public class AddPatientServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	AdminService adminService = new AdminService();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("Inside add employee method");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Map<String, String> result = adminService.addPatient(username, password, firstname, lastname);

		if (result.containsValue("success")) {
			RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
			request.setAttribute("message", result.get("message"));
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
			request.setAttribute("error", result.get("message"));
			rd.forward(request, response);
		}
	}
}

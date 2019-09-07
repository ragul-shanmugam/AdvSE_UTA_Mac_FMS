package com.group4.macfms.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group4.macfms.data.LoginDAO;
import com.group4.macfms.model.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void getUserParam(HttpServletRequest request, User user) {
		System.out.println("Inside getuserParam");
		user.setUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("fname"),
				request.getParameter("lname"), request.getParameter("id"), request.getParameter("phone"),
				request.getParameter("email"), request.getParameter("address"), request.getParameter("city"),
				request.getParameter("state"), request.getParameter("zip"), request.getParameter("role"));
		System.out.println("Username...." + request.getParameter("username"));
		System.out.println("First Name...." + request.getParameter("fname"));
		System.out.println("Last Name...." + request.getParameter("lname"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");

		if (action.equalsIgnoreCase("login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			LoginDAO login = new LoginDAO();
			String userRole = login.userCheck(username, password);

			if (userRole.equalsIgnoreCase("user")) {
				response.sendRedirect("/UTA_Mac_FMS/userHome.jsp");
			} else if (userRole.equalsIgnoreCase("manager")) {
				response.sendRedirect("/UTA_Mac_FMS/managerHome.jsp");
			} else if (userRole.equalsIgnoreCase("repairer")) {
				response.sendRedirect("/UTA_Mac_FMS/repairerHome.jsp");
			} else if (userRole.equalsIgnoreCase("admin")) {
				response.sendRedirect("/UTA_Mac_FMS/adminHome.jsp");
			}
			else
			{
				session.setAttribute("nouser", "usererror");
			}
		}

		if (action.equalsIgnoreCase("register")) {
			User user = new User();
			LoginDAO register = new LoginDAO();
			getUserParam(request, user);
			register.insertUser(user);
		}
	}

}

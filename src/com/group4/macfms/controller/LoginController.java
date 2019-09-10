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
		user.setUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("fname"),
				request.getParameter("lname"), request.getParameter("id"), request.getParameter("phone"),
				request.getParameter("email"), request.getParameter("address"), request.getParameter("city"),
				request.getParameter("state"), request.getParameter("zip"), request.getParameter("role"));
		System.out.println("Inside getuserParam...Username...." + request.getParameter("username"));
		System.out.println("Inside getuserParam...First Name...." + request.getParameter("fname"));
		System.out.println("Inside getuserParam...Last Name...." + request.getParameter("lname"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("errorMsg");
		session.removeAttribute("incorrectPassword");
		String action = request.getParameter("action");
		User loginUser = new User();
		User dbUser = new User();
		LoginDAO login = new LoginDAO();

		if (action.equalsIgnoreCase("login")) {
			loginUser.setUsername(request.getParameter("username"));
			loginUser.setPassword(request.getParameter("password"));
			//validate username and password here
			//if(username validation fails) 
			//set error message attribute to index.jsp page here
			//else
			//setattribute("userInfo", loginUser)
			//if(password validation fails)
			//set error message attribute to index.jsp page here
			//else
			
			session.setAttribute("userInfo", loginUser);
			dbUser = login.userCheck(request.getParameter("username"));

			if (dbUser.getRole().equalsIgnoreCase("user")) {
				session.setAttribute("homePage", dbUser);
				response.sendRedirect("/UTA_Mac_FMS/userHome.jsp");
			} else if (dbUser.getRole().equalsIgnoreCase("manager")) {
				session.setAttribute("homePage", dbUser);
				response.sendRedirect("/UTA_Mac_FMS/managerHome.jsp");
			} else if (dbUser.getRole().equalsIgnoreCase("repairer")) {
				session.setAttribute("homePage", dbUser);
				response.sendRedirect("/UTA_Mac_FMS/repairerHome.jsp");
			} else if (dbUser.getRole().equalsIgnoreCase("admin")) {
				session.setAttribute("homePage", dbUser);
				response.sendRedirect("/UTA_Mac_FMS/adminHome.jsp");
			}
		}

		if (action.equalsIgnoreCase("register")) {
			User user = new User();
			LoginDAO register = new LoginDAO();
			String confirmPassword = request.getParameter("confirm");
			//validate user details here
			getUserParam(request, user);
			register.insertUser(user);
			//show registration success message and redirect to login page
		}
	}

}

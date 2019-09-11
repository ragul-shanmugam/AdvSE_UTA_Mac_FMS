package com.group4.macfms.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
			System.err.println("Printing user role..."+dbUser.getRole());

			if (dbUser.getRole().equalsIgnoreCase("student/faculty")) {
				session.setAttribute("user", dbUser);
				response.sendRedirect("/UTA_Mac_FMS/userHome.jsp");
				session.setAttribute("homePage", "userHome.jsp");
			} else if (dbUser.getRole().equalsIgnoreCase("facility manager")) {
				session.setAttribute("user", dbUser);
				response.sendRedirect("/UTA_Mac_FMS/managerHome.jsp");
				session.setAttribute("homePage", "managerHome.jsp");
			} else if (dbUser.getRole().equalsIgnoreCase("repairer")) {
				session.setAttribute("user", dbUser);
				response.sendRedirect("/UTA_Mac_FMS/repairerHome.jsp");
				session.setAttribute("homePage", "repairerHome.jsp");
			} else if (dbUser.getRole().equalsIgnoreCase("admin")) {
				session.setAttribute("user", dbUser);
				response.sendRedirect("/UTA_Mac_FMS/adminHome.jsp");
				session.setAttribute("homePage", "adminHome.jsp");
			}
		}

		if (action.equalsIgnoreCase("register")) {
			User user = new User();
			LoginDAO register = new LoginDAO();
			String confirmPassword = request.getParameter("confirm");
			//validate user details here
			getUserParam(request, user);
			int status = register.insertUser(user);
			//show registration success message and redirect to login page
			if(status == 1)
			{
				PrintWriter out = response.getWriter();
				 String htmlRespone = "<html>";
				 htmlRespone += "<meta charset=\"ISO-8859-1\" name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\r\n" + 
				 		"<link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n" + 
				 		"<script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.min.js\"></script>";
				 htmlRespone += "	<div class=\"alert alert-success alert-dismissible fade show\">\r\n" + 
				 		"    <strong>Registration Successful!</strong> Please login to view your profile!!\r\n" + 
				 		"    <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\r\n" + 
				 		"	</div>";     
			        htmlRespone += "<h2><a id='login_link' class=\"btn btn-primary offset-md-1 \" href = 'index.jsp'> Login</a></h2>";    
			        htmlRespone += "</html>";
			         
			        // return response
			        out.println(htmlRespone);
				/*session.setAttribute("success", "Registration Successful! Please login to view your profile!!");
				response.sendRedirect("register.jsp");*/
			}
		}
	}

}

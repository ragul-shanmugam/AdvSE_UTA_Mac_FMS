package com.group4.macfms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group4.macfms.data.SearchUserDAO;
import com.group4.macfms.model.User;

/**
 * Servlet implementation class MarController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Method is used to get user details from DB based on the user "lastname"
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException

	{
		HttpSession session = request.getSession();
		ArrayList<User> usersInDB = new ArrayList<User>();
		User searchUser = new User();
		SearchUserDAO searchDetails = new SearchUserDAO();
		searchUser.setLastname(request.getParameter("lastname"));
		searchUser.setRole(request.getParameter("role"));

		if (searchUser.getRole().equalsIgnoreCase("All Users")) {
			try {
				usersInDB = searchDetails.searchUserDetails(searchUser);
				session.setAttribute("USERS", usersInDB);
				session.setAttribute("backSearchPage", "searchUser.jsp");
				getServletContext().getRequestDispatcher("/listUsers.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		
		else {
			try {
				usersInDB = searchDetails.searchUserRoleDetails(searchUser);
				session.setAttribute("USERS", usersInDB);
				getServletContext().getRequestDispatcher("/listUsers.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Method is used to change the user role
	// input parameters username and the role
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String userName = request.getParameter("username");
		String role = request.getParameter("role");
		

		if (action.equalsIgnoreCase("updateUserRole")) {

			SearchUserDAO updateRole = new SearchUserDAO();
			int status = 0;
			try {
				status = updateRole.updateUserRole(userName, role);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (status == 1) {
				PrintWriter out = response.getWriter();
				String htmlRespone = "<html>";
				htmlRespone += "<meta charset=\"ISO-8859-1\" name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\r\n"
						+ "<link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
						+ "<script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.min.js\"></script>";
				htmlRespone += "	<div class=\"alert alert-success alert-dismissible fade show\">\r\n"
						+ "    <strong>User Role updated successfully!</strong>\r\n"
						+ "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\r\n"
						+ "	</div>";
				htmlRespone += "<h2><a id='userhome' class=\"btn btn-primary offset-md-1 \" href='adminHome.jsp'>Back to Home Page</a></h2>";
				htmlRespone += "</html>";

				out.println(htmlRespone);
			} else {
				PrintWriter out = response.getWriter();
				String htmlRespone = "<html>";
				htmlRespone += "<meta charset=\"ISO-8859-1\" name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\r\n"
						+ "<link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
						+ "<script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.min.js\"></script>";
				htmlRespone += "	<div class=\"alert alert-danger alert-dismissible fade show\">\r\n"
						+ "    <strong>We are facing some system issues! Please try updating user role again! Sorry for the inconvinience!</strong>\r\n"
						+ "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\r\n"
						+ "	</div>";
				htmlRespone += "<h2><a id='userhome' class=\"btn btn-primary offset-md-1 \" href='searchUserRole.jsp'>Back to Search Page</a></h2>";
				htmlRespone += "</html>";

				out.println(htmlRespone);
			}
		}
	}

}

package com.group4.macfms.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group4.macfms.data.SearchUserDAO;
import com.group4.macfms.model.Mar;
import com.group4.macfms.model.User;

/**
 * Servlet implementation class MarController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Method is used to get user details from DB based on the user "lastname"
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException

	{
		HttpSession session = request.getSession();
		ArrayList<User> usersInDB = new ArrayList<User>();
		User user = new User();
		SearchUserDAO searchDetails = new SearchUserDAO();
		user.setLastname(request.getParameter("lastname"));
		user.setRole(request.getParameter("role"));

		if (user.getRole().equalsIgnoreCase("All Users")) {
			try {
				usersInDB = searchDetails.searchUserDetails(user);
				session.setAttribute("USERS", usersInDB);
				session.setAttribute("backSearchPage", "searchUser.jsp");
				getServletContext().getRequestDispatcher("/listUsers.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				usersInDB = searchDetails.searchUserRoleDetails(user);
				session.setAttribute("USERS", usersInDB);
				getServletContext().getRequestDispatcher("/listUsers.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Method is used to change the user role
	// input parameters username and the role
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		session.removeAttribute("commonErrorMsg");
		session.removeAttribute("errorMessage");
		User user = (User) session.getAttribute("userInfo");
		String username = user.getUsername();
		
		if (action.equalsIgnoreCase("listSpecificUser")) {
		ArrayList<User> userInDB = new ArrayList<User>();
		User selectedUser = new User();
		int selectedUserIndex;
		User userTemp = new User();
		userTemp.setUsername(username);
		SearchUserDAO searchUser = new SearchUserDAO();
		System.err.println("Printing  inside specific user..."+username);

		if (request.getParameter("radioUser") != null) {
			selectedUserIndex = Integer.parseInt(request.getParameter("radioUser")) - 1;
			try {
				System.out.println("Inside try....");
				userInDB = searchUser.searchSpecificUserDetails();
			} catch (SQLException e) {
				System.out.println("Inside catch....");
				e.printStackTrace();
			}
			selectedUser.setUser(userInDB.get(selectedUserIndex).getUsername(),
					userInDB.get(selectedUserIndex).getPassword(), userInDB.get(selectedUserIndex).getConfirmPassword(),
					userInDB.get(selectedUserIndex).getFirstname(), userInDB.get(selectedUserIndex).getLastname(),
					userInDB.get(selectedUserIndex).getId(), userInDB.get(selectedUserIndex).getPhone(),
					userInDB.get(selectedUserIndex).getEmail(), userInDB.get(selectedUserIndex).getAddress(),
					userInDB.get(selectedUserIndex).getCity(),
					userInDB.get(selectedUserIndex).getState(), userInDB.get(selectedUserIndex).getZipcode(), userInDB.get(selectedUserIndex).getRole());
			session.setAttribute("profile", selectedUser);
			session.setAttribute("backListPage", "listUsers.jsp");
			// getServletContext().getRequestDispatcher("/viewUser.jsp").forward(request,
			// response);
			response.sendRedirect("viewUser.jsp");
		}
		}
		if (action.equalsIgnoreCase("updateUserRole")) {
			
			SearchUserDAO updateRole = new SearchUserDAO();

			// String username = request.getParameter("username");
			String update_Role = request.getParameter("role");

			try {

				int res_val = updateRole.updateUserRole(username, update_Role);

				if (res_val == 1) {
					System.out.println("user role updated Successfully");
					response.sendRedirect("success.jsp");
				} else {
					response.sendRedirect("failure.jsp");

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}

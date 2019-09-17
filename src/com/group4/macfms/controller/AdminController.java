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
import com.group4.macfms.model.User;

/**
 * Servlet implementation class MarController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	//Method is used to get user details from DB based on the user "lastname"
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	
	{	
		HttpSession session = request.getSession();	
		ArrayList<User> usersInDB = new ArrayList<User>();
		User user = new User();
		SearchUserDAO searchDetails = new SearchUserDAO();
		user.setLastname(request.getParameter("lastname")); 
		user.setRole(request.getParameter("role"));
	
		if(user.getRole().equalsIgnoreCase("All Users"))
		{
			try {
				usersInDB = searchDetails.searchUserDetails(user);
				session.setAttribute("USERS", usersInDB);
				session.setAttribute("backSearchPage", "searchUser.jsp");
				getServletContext().getRequestDispatcher("/listUsers.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else
		{
			try {
				usersInDB = searchDetails.searchUserRoleDetails(user);
				session.setAttribute("USERS", usersInDB);
				getServletContext().getRequestDispatcher("/listUsers.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	if(null!=user)
	{
		
			session.setAttribute("user", user);
			getServletContext().getRequestDispatcher("/viewSearchUser.jsp").forward(request, response);
	}
		
	}
	
	//Method is used to change the user role 
	// input parameters username and the role
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	
	{
		
		String action = request.getParameter("action");		
		if (action.equalsIgnoreCase("updateUserRole")) 
		{
			SearchUserDAO updateRole = new SearchUserDAO();
			
			String username = request.getParameter("username");
			String update_Role = request.getParameter("role");
			
			try {
				
				int res_val = updateRole.updateUserRole(username, update_Role);
				
				if(res_val == 1)
				{
					System.out.println("user role updated Successfully");
					response.sendRedirect("success.jsp");
				}
				else
				{
					response.sendRedirect("failure.jsp");

				}
				}
			catch (SQLException e) {
						e.printStackTrace();
					}
			
		 }
	}
	
	
	

}

package com.group4.macfms.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group4.macfms.data.ProfileDAO;
import com.group4.macfms.model.User;

/**
 * Servlet implementation class ViewProfileController
 */
@WebServlet("/ProfileController")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User user = new User();
	
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = new User();
		ProfileDAO retriveUser = new ProfileDAO();
		User temp = (User) session.getAttribute("userInfo");
		user = retriveUser.retrieveUserDetails(temp.getUsername());		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		ProfileDAO updateUser = new ProfileDAO();
		getUserParam(request, user);
		if(action.equalsIgnoreCase("updateUserDetails"))
		{
		//validate user here
			int status = updateUser.updateUserDetails(user);	
		}
	}
}

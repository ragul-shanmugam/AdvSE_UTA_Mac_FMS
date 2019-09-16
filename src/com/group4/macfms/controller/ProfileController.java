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
import com.group4.macfms.model.UserErrorMsgs;

/**
 * Servlet implementation class ViewProfileController
 */
@WebServlet("/ProfileController")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User user = new User();
	
	private void getUserParam(HttpServletRequest request, User user) {
		user.setUser(request.getParameter("username"), request.getParameter("password"), request.getParameter("confirm"), request.getParameter("fname"),
				request.getParameter("lname"), request.getParameter("id"), request.getParameter("phone"),
				request.getParameter("email"), request.getParameter("address"), request.getParameter("city"),
				request.getParameter("state"), request.getParameter("zip"), request.getParameter("role"));
	}
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = new User();
		//String action = request.getParameter("action");
		ProfileDAO retriveUser = new ProfileDAO();
		User temp = (User) session.getAttribute("userInfo");
		//System.out.println("Printing useInfo details..."+temp.getUsername());
		user = retriveUser.retrieveUserDetails(temp.getUsername());
		
		session.setAttribute("profile", user);
		response.sendRedirect("profilePage.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		ProfileDAO updateUser = new ProfileDAO();
		getUserParam(request, user);
		UserErrorMsgs errorMsgs = new UserErrorMsgs();
		if(action.equalsIgnoreCase("updateUserDetails"))
		{
			//validate user here
			user.validateUserDetails(user, errorMsgs);
			if (errorMsgs.getCommonerrorMsg() != "" || !errorMsgs.getCommonerrorMsg().isEmpty()) {
				System.out.println("inside register error user  ");
				session.setAttribute("errorMessage", errorMsgs);
				//session.setAttribute("commonErrorMsg", errorMsg.getCommonerrorMsg());
				getServletContext().getRequestDispatcher("/profilePage.jsp").forward(request, response);
			}
			else
			{
				
			int status = updateUser.updateUserDetails(user);
			if(status == 1)
			{
			session.setAttribute("profile", user);
			response.sendRedirect("profilePage.jsp");
			}
			else
			{
				session.setAttribute("dbError", "There is a system issue updating your profile! Please try again in sometime");
				getServletContext().getRequestDispatcher("/profilePage.jsp").forward(request, response);				
			}
			}
		}
	}
}

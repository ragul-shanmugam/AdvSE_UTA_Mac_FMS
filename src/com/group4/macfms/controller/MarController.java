package com.group4.macfms.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group4.macfms.data.MarDAO;
import com.group4.macfms.model.MarDetails;

/**
 * Servlet implementation class MarController
 */
@WebServlet("/MarController")
public class MarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MarDetails viewMar = new MarDetails();
		MarDAO marDetails = new MarDAO();
		String date = request.getParameter("date"); 
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");  
	    String formattedDate= formatter.format(date);  
	    System.out.println(formattedDate);  
		viewMar = marDetails.retrieveMarDetails(formattedDate);
		
		if(viewMar != null)
		{
			session.setAttribute("marDetails", viewMar);
			response.sendRedirect("marDetails.jsp");
		}
		
	}

}

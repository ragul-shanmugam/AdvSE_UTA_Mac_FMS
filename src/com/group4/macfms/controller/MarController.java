package com.group4.macfms.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		String action = request.getParameter("action");
		/*MarDetails viewMar = new MarDetails();
		MarDAO marDetails = new MarDAO();
		String date = request.getParameter("date"); 
		viewMar = marDetails.retrieveMarDetails(date);
		
		if(viewMar != null)
		{
			session.setAttribute("marDetails", viewMar);
			response.sendRedirect("marResults.jsp");
		}*/
		
		if (action.equalsIgnoreCase("viewMars")) {
			ArrayList<MarDetails> marInDB = new ArrayList<MarDetails>();
			marInDB = MarDAO.listMars();
			session.setAttribute("MARS", marInDB);	
			//session.setAttribute("backListPage", "/listMars.jsp");
			getServletContext().getRequestDispatcher("/listMars.jsp").forward(request, response);
		}
		
		if (action.equalsIgnoreCase("viewAssignedMars")) {
			ArrayList<MarDetails> marInDB = new ArrayList<MarDetails>();
			marInDB = MarDAO.listAssignedMars();
			session.setAttribute("MARS", marInDB);				
			getServletContext().getRequestDispatcher("/listAssignedRepairs.jsp").forward(request, response);
		}
		else // redirect all other gets to post
			doPost(request,response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
		if (action.equalsIgnoreCase("listSpecificMar")) {
			ArrayList<MarDetails> marInDB = new ArrayList<MarDetails>();
			MarDetails selectedMar = new MarDetails();
			int selectedMarIndex;
			
			if (request.getParameter("radioMar")!=null) {
				selectedMarIndex = Integer.parseInt(request.getParameter("radioMar")) - 1;
				marInDB = MarDAO.listMars(); 
				selectedMar.setMar(marInDB.get(selectedMarIndex).getMarNumber(), marInDB.get(selectedMarIndex).getFacilityType(), marInDB.get(selectedMarIndex).getReservationId(), 
										marInDB.get(selectedMarIndex).getReportedBy(), marInDB.get(selectedMarIndex).getUrgency(), marInDB.get(selectedMarIndex).getDescription(), 
											marInDB.get(selectedMarIndex).getDateCreated(), marInDB.get(selectedMarIndex).getAssignedTo(), marInDB.get(selectedMarIndex).getAssignedDate(),
											marInDB.get(selectedMarIndex).getEstimatedTime(), marInDB.get(selectedMarIndex).getMarStatus());
				session.setAttribute("mar", selectedMar);
				session.setAttribute("backListPage", "listMars.jsp");
				//getServletContext().getRequestDispatcher("/viewMar.jsp").forward(request, response);			
				response.sendRedirect("viewMar.jsp");
			}
		}
		if (action.equalsIgnoreCase("updatemarDetails"))
		{
			System.out.println("Inside updatemarDetails if....");
		}
		if (action.equalsIgnoreCase("listSpecificRepair")) {
			ArrayList<MarDetails> marInDB = new ArrayList<MarDetails>();
			MarDetails selectedMar = new MarDetails();
			int selectedMarIndex;
			
			if (request.getParameter("radioMar")!=null) {
				selectedMarIndex = Integer.parseInt(request.getParameter("radioMar")) - 1;
				marInDB = MarDAO.listMars(); 
				selectedMar.setMar(marInDB.get(selectedMarIndex).getMarNumber(), marInDB.get(selectedMarIndex).getFacilityType(), marInDB.get(selectedMarIndex).getReservationId(), 
										marInDB.get(selectedMarIndex).getReportedBy(), marInDB.get(selectedMarIndex).getUrgency(), marInDB.get(selectedMarIndex).getDescription(), 
											marInDB.get(selectedMarIndex).getDateCreated(), marInDB.get(selectedMarIndex).getAssignedTo(), marInDB.get(selectedMarIndex).getAssignedDate(),
											marInDB.get(selectedMarIndex).getEstimatedTime(), marInDB.get(selectedMarIndex).getMarStatus());
				session.setAttribute("mar", selectedMar);	
				//getServletContext().getRequestDispatcher("/viewMar.jsp").forward(request, response);		
				session.setAttribute("backListPage", "listAssignedRepairs.jsp");
				response.sendRedirect("viewRepair.jsp");
			}
		}
	}
}

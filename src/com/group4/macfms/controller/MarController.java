package com.group4.macfms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group4.macfms.data.MarDAO;
import com.group4.macfms.model.Mar;
import com.group4.macfms.model.User;

/**
 * Servlet implementation class MarController
 */
@WebServlet("/MarController")
public class MarController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private void getUserParam(HttpServletRequest request, Mar mar) {
		mar.setMar(request.getParameter("marnumber"), request.getParameter("type"), request.getParameter("rid"),
				request.getParameter("reportedby"), request.getParameter("urgency"), request.getParameter("description"),
				request.getParameter("datecreated"), request.getParameter("assignedto"), request.getParameter("assigneddate"),
				request.getParameter("time"), request.getParameter("status"));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		User user = (User) session.getAttribute("userInfo");
		String username = user.getUsername();
				
		if (action.equalsIgnoreCase("viewMars")) {
			ArrayList<Mar> marInDB = new ArrayList<Mar>();
			marInDB = MarDAO.listMars();
			session.setAttribute("MARS", marInDB);	
			//session.setAttribute("backListPage", "/listMars.jsp");
			getServletContext().getRequestDispatcher("/listMars.jsp").forward(request, response);
		}
		
		if (action.equalsIgnoreCase("viewUnassignedMars")) {
			ArrayList<Mar> marInDB = new ArrayList<Mar>();
			marInDB = MarDAO.listUnassignedMars();
			session.setAttribute("MARS", marInDB);	
			//session.setAttribute("backListPage", "/listMars.jsp");
			getServletContext().getRequestDispatcher("/listUnassignedMars.jsp").forward(request, response);
		}
		
		if (action.equalsIgnoreCase("viewAssignedMars")) {
			ArrayList<Mar> marInDB = new ArrayList<Mar>();
			marInDB = MarDAO.listAssignedMars(username);
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
		User user = (User) session.getAttribute("userInfo");
		String username = user.getUsername();
		
		if (action.equalsIgnoreCase("listSpecificMar")) {
			ArrayList<Mar> marInDB = new ArrayList<Mar>();
			Mar selectedMar = new Mar();
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
		if (action.equalsIgnoreCase("updateMarDetails"))
		{
			MarDAO marUpdate = new MarDAO();
			Mar mar = new Mar();
			getUserParam(request, mar);
			System.err.println("Printing mar getassignedto...."+mar.getAssignedTo());
			if(mar.getAssignedTo() != null || !mar.getAssignedTo().isEmpty() || !mar.getAssignedTo().contains(""))
			{
				mar.setMarStatus("Assigned");
			}
			int status = marUpdate.updateMarDetails(mar);
			if(status == 1)
			{
			session.setAttribute("mar", mar);
			response.sendRedirect("viewMar.jsp");
			}
		}
		
		if (action.equalsIgnoreCase("listSpecificUnassignedMar")) {
			ArrayList<Mar> marInDB = new ArrayList<Mar>();
			Mar selectedMar = new Mar();
			int selectedMarIndex;
			
			if (request.getParameter("radioMar")!=null) {
				selectedMarIndex = Integer.parseInt(request.getParameter("radioMar")) - 1;
				marInDB = MarDAO.listUnassignedMars(); 
				selectedMar.setMar(marInDB.get(selectedMarIndex).getMarNumber(), marInDB.get(selectedMarIndex).getFacilityType(), marInDB.get(selectedMarIndex).getReservationId(), 
										marInDB.get(selectedMarIndex).getReportedBy(), marInDB.get(selectedMarIndex).getUrgency(), marInDB.get(selectedMarIndex).getDescription(), 
											marInDB.get(selectedMarIndex).getDateCreated(), marInDB.get(selectedMarIndex).getAssignedTo(), marInDB.get(selectedMarIndex).getAssignedDate(),
											marInDB.get(selectedMarIndex).getEstimatedTime(), marInDB.get(selectedMarIndex).getMarStatus());
				session.setAttribute("mar", selectedMar);
				session.setAttribute("backListPage", "listUnassignedMars.jsp");
				//getServletContext().getRequestDispatcher("/viewMar.jsp").forward(request, response);			
				response.sendRedirect("viewMar.jsp");
			}
		}
		
		if (action.equalsIgnoreCase("listSpecificRepair")) {
			ArrayList<Mar> marInDB = new ArrayList<Mar>();
			Mar selectedMar = new Mar();
			int selectedMarIndex;
			
			if (request.getParameter("radioMar")!=null) {
				selectedMarIndex = Integer.parseInt(request.getParameter("radioMar")) - 1;
				marInDB = MarDAO.listAssignedMars(username); 
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
		if(action.equalsIgnoreCase("reportProblem"))
		{
			MarDAO marInsert = new MarDAO();
			Mar mar = new Mar();
			mar.setFacilityType(request.getParameter("fname"));
			mar.setUrgency(request.getParameter("urgency"));
			mar.setDescription(request.getParameter("description"));
			mar.setDateCreated(request.getParameter("date"));
			mar.setMarStatus("Unassigned");
			User userInfo = (User) session.getAttribute("userInfo");
			String userName = userInfo.getUsername();
			
			System.err.println("Printing username.."+userName);
			
			int status = marInsert.insertMar(mar, userName);
			if(status == 1)
			{
				PrintWriter out = response.getWriter();
				 String htmlRespone = "<html>";
				 htmlRespone += "<meta charset=\"ISO-8859-1\" name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\r\n" + 
				 		"<link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n" + 
				 		"<script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.min.js\"></script>";
				 htmlRespone += "	<div class=\"alert alert-success alert-dismissible fade show\">\r\n" + 
				 		"    <strong>Report submitted successfully! The issue will be addressed by a technician ASAP!!</strong>\r\n" + 
				 		"    <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\r\n" + 
				 		"	</div>";     
			        htmlRespone += "<h2><a id='userhome' class=\"btn btn-primary offset-md-1 \" href='userHome.jsp'>Back to Home Page</a></h2>";    
			        htmlRespone += "</html>";
			       
			        out.println(htmlRespone);
			}
			else
			{
				PrintWriter out = response.getWriter();
				 String htmlRespone = "<html>";
				 htmlRespone += "<meta charset=\"ISO-8859-1\" name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\r\n" + 
				 		"<link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n" + 
				 		"<script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.min.js\"></script>";
				 htmlRespone += "	<div class=\"alert alert-danger alert-dismissible fade show\">\r\n" + 
				 		"    <strong>We are facing some system issues! Please try reporting the problem again! Sorry for the inconvinience!</strong>\r\n" + 
				 		"    <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\r\n" + 
				 		"	</div>";     
			        htmlRespone += "<h2><a id='userhome' class=\"btn btn-primary offset-md-1 \" href='userHome.jsp'>Back to Home Page</a></h2>";    
			        htmlRespone += "</html>";
			       
			        out.println(htmlRespone);
			}
		}
	}
}

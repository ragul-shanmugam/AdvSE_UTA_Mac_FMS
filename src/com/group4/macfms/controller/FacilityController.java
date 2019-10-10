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

import com.group4.macfms.data.FacilityDAO;
import com.group4.macfms.model.Facility;
import com.group4.macfms.model.UserErrorMsgs;

/**
 * Servlet implementation class FacilityController
 */
@WebServlet("/FacilityController")
public class FacilityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private void getUserParam(HttpServletRequest request, Facility facility) {
		facility.setFacility(request.getParameter("facility"), request.getParameter("fname"), request.getParameter("interval"),
				request.getParameter("duration"), request.getParameter("type"), request.getParameter("availability"));
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
		if(action.equalsIgnoreCase("listFacilities"))
		{
			ArrayList<Facility> facilityInDB = new ArrayList<Facility>();
			facilityInDB = FacilityDAO.listFacilities();
			session.setAttribute("FACILITIES", facilityInDB);
			response.sendRedirect("listFacilities.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		session.removeAttribute("errorMessage");
		session.removeAttribute("facilityNameError");
		
		/*if(action.equalsIgnoreCase("listFacilities"))
		{
			ArrayList<Facility> facilityInDB = new ArrayList<Facility>();
			facilityInDB = FacilityDAO.listFacilities();
			session.setAttribute("FACILITIES", facilityInDB);
			response.sendRedirect("listFacilities.jsp");
			
			String facility = request.getParameter("ftype");
			String facilityName = request.getParameter("fname");
			if(facility.equalsIgnoreCase("selectfacility") && facilityName.equalsIgnoreCase("selectname"))
			{
				facilityInDB = FacilityDAO.listFacilities();
				session.setAttribute("FACILITIES", facilityInDB);	
				session.setAttribute("backSearchFacilityPage", "searchFacility.jsp");
				//getServletContext().getRequestDispatcher("/listFacilities.jsp").forward(request, response);
				response.sendRedirect("listFacilities.jsp");
			}
			else if(facilityName.equalsIgnoreCase("selectname"))
			{
				facilityInDB = FacilityDAO.listFacilitiesWithType(facility);
				session.setAttribute("FACILITIES", facilityInDB);	
				session.setAttribute("backSearchFacilityPage", "searchFacility.jsp");
				//getServletContext().getRequestDispatcher("/listFacilities.jsp").forward(request, response);
				response.sendRedirect("listFacilities.jsp");
			}
			else if(facility.equalsIgnoreCase("selectfacility"))
			{
				facilityInDB = FacilityDAO.listFacilitiesWithName(facilityName);
				session.setAttribute("FACILITIES", facilityInDB);	
				session.setAttribute("backSearchFacilityPage", "searchFacility.jsp");
				//getServletContext().getRequestDispatcher("/listFacilities.jsp").forward(request, response);
				response.sendRedirect("listFacilities.jsp");
			}
			else if(facility!= null && facilityName != null)
			{
				facilityInDB = FacilityDAO.listSpecificFacility(facility, facilityName);
				if(facilityInDB != null)
				{
				session.setAttribute("FACILITIES", facilityInDB);	
				session.setAttribute("backSearchFacilityPage", "searchFacility.jsp");
				//getServletContext().getRequestDispatcher("/listFacilities.jsp").forward(request, response);
				response.sendRedirect("listFacilities.jsp");
				}
				else
				{
					//session.setAttribute("backSearchFacilityPage", "searchFacility.jsp"); - set this a error message
					//getServletContext().getRequestDispatcher("/searchFacility.jsp").forward(request, response);
					response.sendRedirect("searchFacility.jsp");
				}
			}
		}*/
		
		if(action.equalsIgnoreCase("listFacilitiesbyType"))
		{
			ArrayList<Facility> facilityTypeInDB = new ArrayList<Facility>();
			facilityTypeInDB = FacilityDAO.listFacilitiesWithType(request.getParameter("ftype"));
			session.setAttribute("FACILITIES", facilityTypeInDB);
			session.setAttribute("backFacilityListPage", "listFacilities.jsp");
			session.setAttribute("backFacilitySearchPage", "searchFacility.jsp");
			response.sendRedirect("listFacilities.jsp");
		}

		if (action.equalsIgnoreCase("listSpecificFacility")) {
			ArrayList<Facility> facilityInDB = new ArrayList<Facility>();
			Facility selectedFacility = new Facility();
			int selectedFacilityIndex;
			
			if (request.getParameter("radioFacility")!=null) {
				selectedFacilityIndex = Integer.parseInt(request.getParameter("radioFacility")) - 1;
				facilityInDB = FacilityDAO.listFacilities(); 
				selectedFacility.setFacility(facilityInDB.get(selectedFacilityIndex).getFacility(), facilityInDB.get(selectedFacilityIndex).getFacilityName(), facilityInDB.get(selectedFacilityIndex).getMaxInterval(), 
						facilityInDB.get(selectedFacilityIndex).getDuration(), facilityInDB.get(selectedFacilityIndex).getType(), facilityInDB.get(selectedFacilityIndex).getAvailability());
				session.setAttribute("facility", selectedFacility);
				session.setAttribute("backFacilityListPage", "listFacilities.jsp");
				//getServletContext().getRequestDispatcher("/viewMar.jsp").forward(request, response);			
				response.sendRedirect("viewFacility.jsp");
			}
		}
		if (action.equalsIgnoreCase("updateFacility")) {
		FacilityDAO facilityUpdate = new FacilityDAO();
		Facility facility = new Facility();
		getUserParam(request, facility);
		int status = facilityUpdate.updateFacilityDetails(facility);
		if(status == 1)
		{
		session.setAttribute("facility", facility);
		session.setAttribute("backFacilityListPage", "listFacilities.jsp");
		response.sendRedirect("viewFacility.jsp");
		}
		}
		if (action.equalsIgnoreCase("addFacility")) {
			FacilityDAO facilityadd = new FacilityDAO();
			Facility facility = new Facility();
			getUserParam(request, facility);
			UserErrorMsgs errorMsg = new UserErrorMsgs();
			System.out.println("Pringitng name..."+facility.getFacilityName());
			
			String res = facility.validatefacilityName(facility, errorMsg);
			
			System.out.println("Printing error message...." +errorMsg.getFacilityName());
			
			if(res != null)
			{
			System.err.println("Inisde error");
			session.setAttribute("facilityNameError", errorMsg.getFacilityName() );
			getServletContext().getRequestDispatcher("/addFacility.jsp").forward(request, response);
			}
		
	else {		
			int status = facilityadd.addFacilityDetails(facility);
			if(status == 1)
			{
				if (status == 1) {
					PrintWriter out = response.getWriter();
					String htmlRespone = "<html>";
					htmlRespone += "<meta charset=\"ISO-8859-1\" name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\r\n"
							+ "<link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
							+ "<script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.min.js\"></script>";
					htmlRespone += "	<div class=\"alert alert-success alert-dismissible fade show\">\r\n"
							+ "    <strong>Facility added successfully!</strong>\r\n"
							+ "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\r\n"
							+ "	</div>";
					htmlRespone += "<h2><a id='managerhome' class=\"btn btn-primary offset-md-1 \" href='managerHome.jsp'>Back to Home Page</a></h2>";
					htmlRespone += "</html>";

					out.println(htmlRespone);
				} else {
					PrintWriter out = response.getWriter();
					String htmlRespone = "<html>";
					htmlRespone += "<meta charset=\"ISO-8859-1\" name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\r\n"
							+ "<link href=\"bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n"
							+ "<script type=\"text/javascript\" src=\"bootstrap/js/bootstrap.min.js\"></script>";
					htmlRespone += "	<div class=\"alert alert-danger alert-dismissible fade show\">\r\n"
							+ "    <strong>We are facing some system issues! Please try adding the facility again! Sorry for the inconvinience!</strong>\r\n"
							+ "    <button type=\"button\" class=\"close\" data-dismiss=\"alert\">&times;</button>\r\n"
							+ "	</div>";
					htmlRespone += "<h2><a id='managerhome' class=\"btn btn-primary offset-md-1 \" href='managerHome.jsp'>Back to Home Page</a></h2>";
					htmlRespone += "</html>";

					out.println(htmlRespone);
				}
			}
			
		}
	}			
	}
}

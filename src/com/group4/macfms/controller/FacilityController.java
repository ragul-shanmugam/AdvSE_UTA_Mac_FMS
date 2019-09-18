package com.group4.macfms.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.group4.macfms.data.FacilityDAO;
import com.group4.macfms.model.Facility;

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
		response.sendRedirect("viewFacility.jsp");
		}
		}
	}
}

package com.group4.macfms.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.group4.macfms.model.Facility;
import com.group4.macfms.util.SQLConnection;

public class FacilityDAO {
	
	private static ArrayList<Facility> ReturnMatchingFacilitiesList(String queryString) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		ArrayList<Facility> facilitiesListInDB = new ArrayList<Facility>();
		System.out.println("Printing facility query..."+queryString);
		try {
			stmt = conn.createStatement();
			ResultSet facilityList = stmt.executeQuery(queryString);
			while (facilityList.next()) {
				Facility facility = new Facility();
				facility.setFacility(facilityList.getString("Facility"));
				facility.setFacilityName(facilityList.getString("FacilityName"));
				facility.setMaxInterval(facilityList.getString("MaxInterval"));
				facility.setDuration(facilityList.getString("Duration"));
				facility.setType(facilityList.getString("Type"));
				facility.setAvailability(facilityList.getString("Availability"));
				
				facilitiesListInDB.add(facility);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		return facilitiesListInDB;
	}
	
	public int updateFacilityDetails(Facility facility) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
			int status = 0;
				String queryString = "UPDATE `mac_fms`.`facility` SET `Facility` = '"+facility.getFacility()+"', `FacilityName` = '"+facility.getFacilityName()
						+"', `MaxInterval` = '"+facility.getMaxInterval()+"', `Duration` = '"+facility.getDuration()+"', `Type` = '"+facility.getType()
						+"', `Availability` = '"+facility.getAvailability()
						+"' WHERE `FacilityName` = '"+facility.getFacilityName()+"';";
			System.out.println("Printing facility query..."+queryString);
			try {
				stmt = conn.createStatement();
				status = stmt.executeUpdate(queryString);
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			System.err.println("Printing status..."+status);
			return status;
	}

	public static ArrayList<Facility> listFacilities() {
		return ReturnMatchingFacilitiesList(" SELECT * from mac_fms.facility order by FacilityName;");
	}


	public static ArrayList<Facility> listFacilitiesWithType(String facility) {
		return ReturnMatchingFacilitiesList("SELECT * from mac_fms.facility where Facility ='"+facility+"';");
	}


	public static ArrayList<Facility> listFacilitiesWithName(String facilityName) {
		return ReturnMatchingFacilitiesList("SELECT * from mac_fms.facility where FacilityName ='"+facilityName+"';");
	}


	public static ArrayList<Facility> listSpecificFacility(String facility, String facilityName) {
		return ReturnMatchingFacilitiesList("SELECT * from mac_fms.facility where Facility = '"+facility+"'and FacilityName ='"+facilityName+"';");
	}

}

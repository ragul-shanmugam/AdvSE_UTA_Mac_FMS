package com.group4.macfms.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.group4.macfms.model.MarDetails;
import com.group4.macfms.util.SQLConnection;

public class MarDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();
	Statement stmt = null;
	Connection conn = SQLConnection.getDBConnection();

	public MarDetails retrieveMarDetails(String date) {
		MarDetails mar = new MarDetails();
		String queryString = "SELECT * from mac_fms.mar where assigned_date = '"+date+"';";
		System.out.println("Printing mar query..."+queryString);
		try {
			stmt = conn.createStatement();
			ResultSet marList = stmt.executeQuery(queryString);
			while (marList.next()) {
				mar.setMarNumber(marList.getString("mar_number"));
				mar.setFacilityType(marList.getString("facility_type"));
				mar.setReservationId(marList.getString("reservation_id_fk"));
				mar.setReportedBy(marList.getString("reported_by_fk"));
				mar.setUrgency(marList.getString("urgency"));
				mar.setDescription(marList.getString("description"));
				mar.setDateCreated(marList.getString("date_created"));
				mar.setAssignedTo(marList.getString("assigned_to"));
				mar.setAssignedDate(marList.getString("assigned_date"));
				mar.setEstimatedTime(marList.getString("estimated_time"));
				mar.setMarStatus(marList.getString("mar_status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		return mar;
	}

	public ArrayList<MarDetails> listMars() {
		
		MarDetails mar = new MarDetails();
		ArrayList<MarDetails> marsListInDB = new ArrayList<MarDetails>();
		String queryString = "SELECT * from mac_fms.mar order by mar_number;";
		System.out.println("Printing mar query..."+queryString);
		try {
			stmt = conn.createStatement();
			ResultSet marList = stmt.executeQuery(queryString);
			while (marList.next()) {
				mar.setMarNumber(marList.getString("mar_number"));
				mar.setFacilityType(marList.getString("facility_type"));
				mar.setReservationId(marList.getString("reservation_id"));
				mar.setReportedBy(marList.getString("reported_by"));
				mar.setUrgency(marList.getString("urgency"));
				mar.setDescription(marList.getString("description"));
				mar.setDateCreated(marList.getString("date_created"));
				mar.setAssignedTo(marList.getString("assigned_to"));
				mar.setAssignedDate(marList.getString("assigned_date"));
				mar.setEstimatedTime(marList.getString("estimated_time"));
				mar.setMarStatus(marList.getString("mar_status"));
				
				marsListInDB.add(mar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		return marsListInDB;
	}

}

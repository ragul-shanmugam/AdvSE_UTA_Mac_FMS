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

	public static ArrayList<MarDetails> ReturnMatchingMarsList(String queryString) {
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		ArrayList<MarDetails> marsListInDB = new ArrayList<MarDetails>();
		//String queryString = "SELECT * from mac_fms.mar order by mar_number;";
		System.out.println("Printing mar query..."+queryString);
		try {
			stmt = conn.createStatement();
			ResultSet marList = stmt.executeQuery(queryString);
			while (marList.next()) {
				MarDetails mar = new MarDetails();
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
	
	public int updateMarDetails(MarDetails mar) {
		
	Statement stmt = null;
	Connection conn = SQLConnection.getDBConnection();
		int status = 0;
			String queryString = "UPDATE `mac_fms`.`mar` SET `mar_number` = '"+mar.getMarNumber()+"', `facility_type` = '"+mar.getFacilityType()
					+"', `reservation_id` = '"+mar.getReservationId()+"', `reported_by` = '"+mar.getReportedBy()+"', `urgency` = '"+mar.getUrgency()
					+"', `description` = '"+mar.getDescription()+"', `date_created` = '"+mar.getDateCreated()+"', `assigned_to` = '"+mar.getAssignedTo()
					+"', `assigned_date` = '"+mar.getAssignedDate()+"', `estimated_time` = '"+mar.getEstimatedTime()+"', `mar_status` = '"+mar.getMarStatus()
					+"' WHERE `mar_number` = '"+mar.getMarNumber()+"';";
		System.out.println("Printing mar query..."+queryString);
		try {
			stmt = conn.createStatement();
			status = stmt.executeUpdate(queryString);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		System.err.println("Printing status..."+status);
		return status;
	}

	public static ArrayList<MarDetails>  listMars() {  
	return ReturnMatchingMarsList(" SELECT * from mac_fms.mar ORDER BY mar_number;");
	}
	
	public static ArrayList<MarDetails>  listUnassignedMars() {  
		return ReturnMatchingMarsList(" SELECT * from mac_fms.mar where assigned_to ='"+null+"';");
		}
	
	public static ArrayList<MarDetails>  listAssignedMars(String username) {  
		return ReturnMatchingMarsList(" SELECT * from mac_fms.mar where assigned_to ='"+username+"';");
		}
}

package com.group4.macfms.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import com.group4.macfms.model.Mar;
import com.group4.macfms.util.SQLConnection;

public class MarDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();

	public static ArrayList<Mar> ReturnMatchingMarsList(String queryString) {

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		ArrayList<Mar> marsListInDB = new ArrayList<Mar>();
		
		System.out.println("Printing mar query..."+queryString);
		try {
			stmt = conn.createStatement();
			ResultSet marList = stmt.executeQuery(queryString);
			while (marList.next()) {
				Mar mar = new Mar();
				mar.setMarNumber(marList.getString("MarNumber"));
				mar.setFacilityType(marList.getString("FacilityName"));
				mar.setReservationId(marList.getString("ReservationId"));
				mar.setReportedBy(marList.getString("ReportedBy"));
				mar.setUrgency(marList.getString("Urgency"));
				mar.setDescription(marList.getString("Description"));
				mar.setDateCreated(marList.getString("DateCreated"));
				mar.setAssignedTo(marList.getString("AssignedTo"));
				mar.setAssignedDate(marList.getString("AssignedDate"));
				mar.setEstimatedTime(marList.getString("EstimateRepair"));
				mar.setMarStatus(marList.getString("MarStatus"));

				marsListInDB.add(mar);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return marsListInDB;
	}

	public int updateMarDetails(Mar mar) {

		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		int status = 0;
		String queryString = "UPDATE `uta_mac_fms`.`mardetails` SET `MarNumber` = '" + mar.getMarNumber()
				+ "', `FacilityName` = '" + mar.getFacilityType() + "', `ReservationId` = '" + mar.getReservationId()
				+ "', `ReportedBy` = '" + mar.getReportedBy() + "', `Urgency` = '" + mar.getUrgency()
				+ "', `Description` = '" + mar.getDescription() + "', `DateCreated` = '" + mar.getDateCreated()
				+ "', `AssignedTo` = '" + mar.getAssignedTo() + "', `AssignedDate` = '" + mar.getAssignedDate()
				+ "', `EstimateRepair` = '" + mar.getEstimatedTime() + "', `MarStatus` = '" + mar.getMarStatus()
				+ "' WHERE `MarNumber` = '" + mar.getMarNumber() + "';";
		System.out.println("Printing mar query..."+queryString);
		try {
			stmt = conn.createStatement();
			status = stmt.executeUpdate(queryString);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	public static ArrayList<Mar> listMars() {
		return ReturnMatchingMarsList(" SELECT * from uta_mac_fms.mardetails ORDER BY MarNumber;");
	}

	public static ArrayList<Mar> listUnassignedMars() {
		return ReturnMatchingMarsList(
				" SELECT * from uta_mac_fms.mardetails where TRIM(AssignedTo) IS NULL or AssignedTo = '' or AssignedTo ='"
						+ null + "';");
	}

	public static ArrayList<Mar> listAssignedMars(String username) {
		return ReturnMatchingMarsList(" SELECT * from uta_mac_fms.mardetails where AssignedTo ='" + username + "';");
	}

	public int insertMar(Mar mar, String userName) {
		int status = 0;
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		Random random = new Random();
		int x = random.nextInt(900) + 100;
		String marNumber = "MAR " + x;
		String reservationId = "R" + x;

		String querystring = "INSERT INTO uta_mac_fms.mardetails (`MarNumber`, `FacilityName`, `ReservationId`, `ReportedBy`, `Urgency`,`Description`,`DateCreated`, `MarStatus`) VALUES "
				+ "('" + marNumber + "','" + mar.getFacilityType() + "','" + reservationId + "', '" + userName + "', '"
				+ mar.getUrgency() + "','" + mar.getDescription() + "','" + mar.getDateCreated() + "', '"
				+ mar.getMarStatus() + "');";
		// System.out.println("Printing query...."+querystring);
		try {
			stmt = conn.createStatement();
			status = stmt.executeUpdate(querystring);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
}

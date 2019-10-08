package com.group4.macfms.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
		SimpleDateFormat simpleDateformat1 = new SimpleDateFormat("MM/dd/YYYY"); // the day of the week spelled out completely
        
		
		
		Date now = new Date();
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        String day = simpleDateformat.format(now);
        System.out.println("Todays day..."+day);
		
        // validating if user exists and is available for that current day.
        boolean userAvailable = checkUniqueUsername(mar.getAssignedTo(), day);
		if (userAvailable) {
			Date startDate = getWeekStartDate();
			String startOfWeek = simpleDateformat1.format(startDate);
			
			Date endDate = getWeekEndDate();
//			SimpleDateFormat simpleDateformat2 = new SimpleDateFormat("MM/dd/YYYY"); // the day of the week spelled out completely
	        String endOfWeek = simpleDateformat1.format(endDate);
			
	        Date today = new Date();
	        String todaysDate = simpleDateformat1.format(today);
	        
			System.out.println("Start Date of week..."+startOfWeek);
			System.out.println("End Date of week..."+endOfWeek);

			// validation for the rule check on TotalMars < 10 (per week) and < 5 (per day)
			String query = "SELECT AssignedTo,\r\n" + 
					"(SELECT COUNT(MarNumber)  from mardetails where AssignedDate between '"+startOfWeek+"' and '"+endOfWeek+"') as TotalMarsCountPerWeek,\r\n" + 
					"(SELECT COUNT(MarNumber)  from mardetails where AssignedDate = '"+todaysDate+"') as TotalMarsCountPerDay\r\n" + 
					"FROM mardetails where AssignedTo = 'fmsrepairer' GROUP BY AssignedTo;";
			System.out.println("Printing mar query..."+query);
		try {
				stmt = conn.createStatement();
				ResultSet result = stmt.executeQuery(query);
				int TotalMarsCountPerWeek = 0;
				int TotalMarsCountPerDay = 0;
				while(result.next()) {
					 TotalMarsCountPerWeek = Integer.parseInt(result.getString("TotalMarsCountPerWeek"));
					 TotalMarsCountPerDay = Integer.parseInt(result.getString("TotalMarsCountPerDay"));
				}
				System.out.println("TotalMarsCountPerWeek..."+TotalMarsCountPerWeek);
				System.out.println("TotalMarsCountPerDay..."+TotalMarsCountPerDay);
				
				if(TotalMarsCountPerDay < 5 && TotalMarsCountPerWeek < 10) {
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
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}else {
					System.out.println("user not allowed to assign MAR");
	
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		return status;
	}
	
	public static boolean checkUniqueUsername(String username, String day) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		boolean userNameExists = false;

		String sql = "SELECT * from uta_mac_fms.schedule where Username = '" + username + "' and "+day+"='Yes';";
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(sql);
			if (userList.next()) {
				userNameExists = true;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userNameExists;
	}

	public static Date getWeekStartDate() {
	    Calendar calendar = Calendar.getInstance();
	    while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
	        calendar.add(Calendar.DATE, -1);
	    }
	    return calendar.getTime();
	}

	public static Date getWeekEndDate() {
	    Calendar calendar = Calendar.getInstance();
	    while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
	        calendar.add(Calendar.DATE, 1);
	    }
	    calendar.add(Calendar.DATE, -1);
	    return calendar.getTime();
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

	public static ArrayList<Mar> listReportedMars(String username) {
		return ReturnMatchingMarsList(" SELECT * from uta_mac_fms.mardetails where ReportedBy ='" + username + "';");
	}
	
	public static ArrayList<Mar> listReportedMars1(String username) {
		return ReturnMatchingMarsList(" SELECT * from uta_mac_fms.mardetails where assignedDate ='"+username+"';");
	}
	
	public int insertMar(Mar mar, String userName) {
		int status = 0;
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		Random random = new Random();
		int x = random.nextInt(900) + 100;
		String marNumber = "MAR " + x;
		String reservationId = "R" + x;

		String querystring = "INSERT INTO uta_mac_fms.mardetails (`MarNumber`, `FacilityName`, `ReservationId`, `ReportedBy`,`Description`,`DateCreated`, `MarStatus`) VALUES "
				+ "('" + marNumber + "','" + mar.getFacilityType() + "','" + reservationId + "', '" + userName + "', '"
				+ mar.getDescription() + "','" + mar.getDateCreated() + "', '"
				+ mar.getMarStatus() + "');";
		System.out.println("Printing query...."+querystring);
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

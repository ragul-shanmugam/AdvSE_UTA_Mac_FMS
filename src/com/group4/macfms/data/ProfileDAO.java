package com.group4.macfms.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.group4.macfms.model.User;
import com.group4.macfms.util.SQLConnection;

public class ProfileDAO {
	static SQLConnection DBMgr = SQLConnection.getInstance();
	Statement stmt = null;
	Connection conn = SQLConnection.getDBConnection();

	public User retrieveUserDetails(String username) {
		User user = new User();
		String queryString = "SELECT * from mac_fms.users where username = '"+username+"';";
		
		System.out.println("Printing user query..."+queryString);
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(queryString);
			while (userList.next()) {
				user.setUsername(userList.getString("username"));
				user.setPassword(userList.getString("password"));
				user.setFirstname(userList.getString("firstname"));
				user.setLastname(userList.getString("lastname"));
				user.setId(userList.getString("utaid"));
				user.setRole(userList.getString("role"));
				user.setPhone(userList.getString("phone"));
				user.setEmail(userList.getString("email"));
				user.setAddress(userList.getString("address"));
				user.setCity(userList.getString("city"));
				user.setState(userList.getString("state"));
				user.setZipcode(userList.getString("zipcode"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return user;
	}

	public int updateUserDetails(User updateDetails) {
		int status = 0;
		String queryString = "UPDATE `mac_fms`.`users` SET `password` = '"+updateDetails.getPassword()+"', "
				+ "`firstname` = '"+updateDetails.getFirstname()+"', `lastname` = '"+updateDetails.getLastname()+"', `utaid` = '"+updateDetails.getId()+"', `phone` = '"+updateDetails.getPhone()+"', "
						+ "`email` = '"+updateDetails.getEmail()+"', `address` = '"+updateDetails.getAddress()+"', `city` = '"+updateDetails.getCity()+"', `state` = '"+updateDetails.getState()+"', "
								+ "`zipcode` = '"+updateDetails.getZipcode()+"' WHERE `username` = '"+updateDetails.getUsername()+"';";
		System.out.println("Printing query...."+queryString);
		try {
			stmt = conn.createStatement();
			status = stmt.executeUpdate(queryString);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;		
	}

}

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
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(queryString);
			while (userList.next()) {
				user.setUsername(userList.getString("username"));
				user.setPassword(userList.getString("password"));
				user.setFirstname(userList.getString("firstname"));
				user.setLastname(userList.getString("lastname"));
				user.setLastname(userList.getString("id"));
				user.setRole(userList.getString("role"));
				user.setLastname(userList.getString("phone"));
				user.setLastname(userList.getString("email"));
				user.setLastname(userList.getString("address"));
				user.setLastname(userList.getString("city"));
				user.setLastname(userList.getString("state"));
				user.setLastname(userList.getString("zipcode"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return user;
	}

	public int updateUserDetails(User updateDetails) {
		int status = 0;
		//TODO:Need to modify this Query
		String queryString = "UPDATE mac_fms.users SET (`username`,`password`,`firstname`,`lastname`,`utaid`,`phone`,`email`,`address`,`city`,`state`,`zipcode`,`role`) VALUES "
				+ "('"+updateDetails.getUsername()+"','"+updateDetails.getPassword()+"','"+updateDetails.getFirstname()+"','"+updateDetails.getLastname()+"','"+updateDetails.getId()+"','"+updateDetails.getPhone()+"','"+updateDetails.getEmail()+"','"
				+updateDetails.getAddress()+"','"+updateDetails.getCity()+"','"+updateDetails.getState()+"','"+updateDetails.getZipcode()+"','"+updateDetails.getRole()+"');";
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

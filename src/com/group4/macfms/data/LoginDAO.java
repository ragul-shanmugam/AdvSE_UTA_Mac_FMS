package com.group4.macfms.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.group4.macfms.model.User;
import com.group4.macfms.util.SQLConnection;

public class LoginDAO {
	
	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	public boolean userCheck (String username, String password) {
		boolean userExists = false;
		String queryString = "SELECT * from mac_fms.login where username = '"+username+"' && password = '"+password+"';";
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(queryString);
			while (userList.next()) {
				userExists = true;	
			}
		} catch (SQLException e) {}
		
		if(!userExists)
		{
			System.out.println("User not exists in the DB");
		}
		
		return userExists;
	}	
}

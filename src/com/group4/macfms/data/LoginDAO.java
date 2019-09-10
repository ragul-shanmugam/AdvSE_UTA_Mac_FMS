package com.group4.macfms.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.group4.macfms.model.User;
import com.group4.macfms.util.SQLConnection;

public class LoginDAO {
	
	static SQLConnection DBMgr = SQLConnection.getInstance();
	Statement stmt = null;
	Connection conn = SQLConnection.getDBConnection();
	
	public User userCheck (String username) {
		String queryString = "SELECT * from mac_fms.users where username = '"+username+"';";
		System.out.println("Printing login query..."+queryString);
		User user = new User();
		  
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(queryString);
			while (userList.next()) {
				user.setFirstname(userList.getString("firstname"));
				user.setLastname(userList.getString("lastname"));
				user.setPassword(userList.getString("password"));
				user.setRole(userList.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return user;
	}
	public int insertUser(User register)
	{
		System.out.println("Inside insertuser....");
		int status = 0;
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();
		if(register.getUsername() != null)
		{
			//System.out.println("Printing user name inside insert user..."+register.getUsername());			  
			try {
					String queryString = "INSERT INTO mac_fms.users (`username`,`password`,`firstname`,`lastname`,`utaid`,`phone`,`email`,`address`,`city`,`state`,`zipcode`,`role`) VALUES "
							+ "('"+register.getUsername()+"','"+register.getPassword()+"','"+register.getFirstname()+"','"+register.getLastname()+"','"+register.getId()+"','"+register.getPhone()+"','"+register.getEmail()+"','"
							+register.getAddress()+"','"+register.getCity()+"','"+register.getState()+"','"+register.getZipcode()+"','"+register.getRole()+"');";
					System.out.println("Printing query...."+queryString);
					
						stmt = conn.createStatement();
						status = stmt.executeUpdate(queryString);
						conn.commit(); 
						
						System.out.println("Printing status...."+status);
						System.err.println("User Inserted Succesfully.....");
						
					} catch (SQLException e) {
						e.printStackTrace();
					}		
			}
		return status;
		}
	}

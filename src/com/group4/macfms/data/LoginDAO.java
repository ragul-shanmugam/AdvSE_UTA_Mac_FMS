package com.group4.macfms.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.group4.macfms.model.User;
import com.group4.macfms.util.SQLConnection;

public class LoginDAO {
	
	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	public String userCheck (String username, String password) {
		boolean userExists = false;
		String role = null;
		String queryString = "SELECT password, role from mac_fms.users where username = '"+username+"';";
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(queryString);
			while (userList.next()) {
				userExists = true;
				password = userList.getString(1);
				role = userList.getString(2);
			}
		} catch (SQLException e) {}
		
		if(userExists)
		{
			System.out.println("User not exists in the DB");
			//check for password match here and return role
			System.out.println("Printing the role..."+role);
		}
		
		return role;
	}
	public void insertUser(User register)
	{
		System.out.println("Inside insertuser....");
		//User register = new User();
		if(register.getUsername() != null)
		{
			System.out.println("Printing user name inside insert user..."+register.getUsername());
			String userSql = "Select * from mac_fms.users where username = '"+register.getUsername()+"';";
			
			Statement stmt = null;
			Connection conn = SQLConnection.getDBConnection();  
			try {
				stmt = conn.createStatement();
				ResultSet userStatus = stmt.executeQuery(userSql);
				System.out.println("Printing User status...."+userStatus);
				if (userStatus.next()) {
					//add validation error message here
					System.err.println("User already exists in DB");
				}
				else 
				{
					String queryString = "INSERT INTO mac_fms.users (`username`,`password`,`firstname`,`lastname`,`utaid`,`phone`,`email`,`address`,`city`,`state`,`zipcode`,`role`) VALUES "
							+ "('"+register.getUsername()+"','"+register.getPassword()+"','"+register.getFirstname()+"','"+register.getLastname()+"','"+register.getId()+"','"+register.getPhone()+"','"+register.getEmail()+"','"
							+register.getAddress()+"','"+register.getCity()+"','"+register.getState()+"','"+register.getZipcode()+"','"+register.getRole()+"');";
					System.out.println("Printing query...."+queryString);
					
					try {
						stmt = conn.createStatement();
						int status = stmt.executeUpdate(queryString);
						conn.commit(); 
						
						System.out.println("Printing status...."+status);
						System.err.println("User Inserted Succesfully.....");
						
						if(status == 1)
						{
							//redirect to respective home page
						}
						conn.commit(); 
						
					} catch (SQLException e) {}
					
				}
			} catch (SQLException e) {}
		}
	}
	
}

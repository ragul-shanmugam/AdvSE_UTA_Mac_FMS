package com.group4.macfms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
	private static String DB_DRIVER;
	private static String DB_CONNECTION;
	private static String DB_USER;
	private static String DB_PASSWORD; 
	private static SQLConnection single_instance = null;
	private SQLConnection() {
		DB_DRIVER = "com.mysql.jdbc.Driver";
		DB_CONNECTION  = "jdbc:mysql://localhost:3306/uta_mac_fms?autoReconnect=true&useSSL=false";
		DB_USER  = "root";
		DB_PASSWORD = "MyNewPass";
	}
	public static synchronized SQLConnection getInstance() {
        if (single_instance == null)
        	single_instance = new SQLConnection();
        return single_instance;
	}

	public static Connection getDBConnection() {	
		Connection dbConnection = null;	 

		try {	 
			Class.forName(DB_DRIVER);	 
		} catch (ClassNotFoundException e) {	 
		}

		try {	 
			System.out.println("abc");
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
			System.out.println("abc1");
			dbConnection.setAutoCommit(false);
		} catch (SQLException e) {	    
		}	 
		return dbConnection;	 
	}
}

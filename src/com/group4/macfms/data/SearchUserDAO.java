package com.group4.macfms.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.group4.macfms.model.User;
import com.group4.macfms.util.SQLConnection;

public class SearchUserDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	Statement stmt = null;
	Connection conn = SQLConnection.getDBConnection();

	public ArrayList<User> RetrunMatchingUsersList(String queryString) throws SQLException {

		ArrayList<User> usersInDB = new ArrayList<User>();
		System.out.println("User Search Query ..." + queryString);
		try {
			stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(queryString);

			while (result.next()) {
				User user = new User();
				user.setUsername(result.getString("username"));
				user.setFirstname(result.getString("firstname"));
				user.setLastname(result.getString("lastname"));
				user.setRole(result.getString("role"));

				usersInDB.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			stmt.close();
			conn.close();
		}
		return usersInDB;
	}

	public int updateUserRole(String username, String roleUpdate) throws SQLException {

		int status = 0;

		String query = "UPDATE mac_fms.users set role = '" + roleUpdate + "' WHERE username = '" + username + "';";
		System.out.println("User Search Query ..." + query);
		try {
			stmt = conn.createStatement();
			status = stmt.executeUpdate(query);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
		}
		return status;
	}

	public ArrayList<User> searchAllUserDetails(User user) throws SQLException {
		return RetrunMatchingUsersList(
				"select username, firstname, lastname, role from mac_fms.users order by username;");
	}
	
	public ArrayList<User> searchUserDetails(User user) throws SQLException {
		return RetrunMatchingUsersList(
				"select username, firstname, lastname, role from mac_fms.users where lastname = '" + user.getLastname()
						+ "';");
	}

	public ArrayList<User> searchUserRoleDetails(User user) throws SQLException {
		return RetrunMatchingUsersList(
				"select username, firstname, lastname, role from mac_fms.users where lastname = '" + user.getLastname()
						+ "' and role = '" + user.getRole() + "';");
	}

	public User searchSpecificUserDetails(String username) throws SQLException {

		String queryString = "select * from mac_fms.users where username = '" + username + "';";
		System.out.println("User Search Query ..." + queryString);
		User user = new User();

		try {
			stmt = conn.createStatement();
			ResultSet userList = stmt.executeQuery(queryString);
			while (userList.next()) {

				user.setUsername(userList.getString("username"));
				user.setPassword(userList.getString("password"));
				user.setConfirmPassword(userList.getString("password"));
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

				// usersInDB.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}

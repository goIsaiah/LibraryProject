package Databases;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import GUI.LibraryUI;
public class DBUser {
	
	static String url = "jdbc:mysql://localhost:3306/myDB";
	static String urlRoot = "jdbc:mysql://localhost:3306";
	static String user = "root";
	static String password = LibraryUI.sqlpassword; //Change this
	
	public DBUser() {
	}
	
	public static boolean checkUserExists(String username, String email) throws SQLException {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    boolean userExists = false;
	    
	    try {
	        conn = DriverManager.getConnection(url, user, password);
	        String sql = "SELECT * FROM usertable WHERE username = ? OR email = ?";
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, username);
	        stmt.setString(2, email);
	        rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            userExists = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    return userExists;
	}

	public boolean checkLogin(String username, String passwordLog) throws SQLException{
		boolean loginSuccess = false;
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    
		try {
	        conn = DriverManager.getConnection(url, user, password);
	        String sql = "SELECT * FROM usertable WHERE username = ? AND password = ?";
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, username);
	        stmt.setString(2, passwordLog);
	        rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	        	loginSuccess = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
		return loginSuccess;
	}

	public String getEmail(String username) throws SQLException {
		Connection conn = DriverManager.getConnection(url, user, password);
		Statement stmt = conn.createStatement();
		String sql = "SELECT email FROM usertable WHERE username = '" + username + "'";
		ResultSet rs = stmt.executeQuery(sql);
		 while (rs.next()) {
			    String email = rs.getString("email");
			    return email;
		 }
		 return "";
		
	}

	public void addUser(String username, String email, String passwordLog) throws SQLException {
		Connection conn = DriverManager.getConnection(url, user, password);
		String sql = "INSERT INTO usertable (username, password, email) VALUES (?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		statement.setString(2, passwordLog);
		statement.setString(3, email);
		statement.executeUpdate();
	}



}

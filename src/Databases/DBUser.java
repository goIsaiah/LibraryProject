package Databases;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import GUI.LibraryUI;
public class DBUser {
	
//	static String url = "jdbc:mysql://localhost:3306/myDB";
//	static String urlRoot = "jdbc:mysql://localhost:3306";
//	static String user = "root";
//	static String password = LibraryUI.sqlpassword; //Change this
	private static  Connection conn; 
	
	public DBUser() throws SQLException {
//		conn = DBUtil.getConnection(DBType_enum.ONLINE);
		conn = LibraryUI.conn; 
	}
	
	public static boolean checkUserExists(String username, String email) throws SQLException {
	 
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    boolean userExists = false;
	    
	    try {
	    
	        String sql = "SELECT * FROM USERTABLE WHERE username = ? OR email = ?";
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
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    
		try {
	        String sql = "SELECT * FROM USERTABLE WHERE username = ? AND password = ?";
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
		Statement stmt = conn.createStatement();
		String sql = "SELECT email FROM USERTABLE WHERE username = '" + username + "'";
		ResultSet rs = stmt.executeQuery(sql);
		 while (rs.next()) {
			    String email = rs.getString("email");
			    return email;
		 }
		 return "";
		
	}

	public void addUser(String username, String email, String passwordLog) throws SQLException {
		String sql = "INSERT INTO USERTABLE (username, password, email) VALUES (?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, username);
		statement.setString(2, email);
		statement.setString(3, passwordLog);
		statement.executeUpdate();
	}
	
	public ArrayList<String> getUserInfo() throws SQLException{
		Connection conn = DBUtil.getConnection(DBType_enum.ONLINE); 
		String query = "Select USERNAME, EMAIL from USERTABLE;"; 
		Statement statement= conn.createStatement(); 
		ResultSet set = statement.executeQuery(query); 
		ArrayList<String> list = new ArrayList<>(); 
		
		while(set.next()) {
			list.add((set.getString(1) + "\n" + set.getString(2) + "\n")); 
		}
		conn.close();
		return list; 
	}
	
	




}

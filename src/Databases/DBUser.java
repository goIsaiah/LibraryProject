package Databases;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

import DomainObjects.User;
import GUI.LibraryUI;
import Logic.AlreadyAFriendException;
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
	
	public Hashtable<Integer, String> getUserInfo() throws SQLException{
		String query = "Select USERNAME, EMAIL, user_id from USERTABLE;"; 
		Statement statement= conn.createStatement(); 
		ResultSet set = statement.executeQuery(query); 
		Hashtable<Integer, String> list = new Hashtable<>(); 
		
		while(set.next()) {
			list.put(set.getInt(3), set.getString(1) + "\n" + set.getString(2) + "\n" );
		}
		return list; 
	}
	
	public User getUser(int key) {
		User user = null; 
		ResultSet result = null; 
		String query = "select * from USERTABLE INNER JOIN USERINFO on USERTABLE.user_id = USERINFO.user_id "
				+ "where USERTABLE.user_id = " + Integer.toString(key) + ";"; 
		try {
			Statement statement = conn.createStatement();
			result = statement.executeQuery(query);
			if(result.next()) {
				user = new User(result.getString(2)); 
				user.setEmail(result.getString(4));
				user.setURL(result.getString(7)); 
				user.setBio(result.getString(8));
				user.setFirstName(result.getString(9));
				user.setLastName(result.getString(10)); 
				//TODO set followerList 
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user; 
	}
	
	public void addFollower(int user_id , int friend_id) {
		if(user_id == friend_id) {
			
		}
			else {			
			String query ="INSERT INTO FOLLOWERS(user_id, friend_id) values " 
			+ "(" + Integer.toString(user_id) +","+Integer.toString(friend_id) + ")" ;  
			try {
				Statement statement = conn.createStatement();
				statement.executeUpdate(query);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void removeFollower(int user_id,  int friend_id) {
		if(user_id == friend_id) {
		}else {
			String query = "REMOVE FROM FOLLOWERS WHERE user_id = ? AND friend_id = ?;"; 
			PreparedStatement statement;
			try {
				statement = conn.prepareStatement(query);
				statement.setInt(user_id, 1);
				statement.setInt(friend_id, 2);
				statement.executeUpdate(); 
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
	}
	




}

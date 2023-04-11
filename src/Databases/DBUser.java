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
	
	private static  Connection conn; 
	
	public DBUser() throws SQLException {
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
		String query = "select * from USERTABLE LEFT JOIN USERINFO on USERTABLE.user_id = USERINFO.user_id "
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
				user.setUsername(result.getString(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user; 
	}
	
	public int getUserId(User u) throws SQLException {
	
		String query = "Select USERNAME, user_id  from USERTABLE  ;"; 
		Statement statement;
		int id = 1; 
		boolean flag = true; 
			statement = conn.createStatement();
			ResultSet  set = statement.executeQuery(query); 
			while(set.next() && flag ) {
				if(set.getString(1).equals(u.getUsername())) {
					id=set.getInt(2); 
					System.out.printf("id got is %d\n", id);
					flag = true; 
				}
			}
			System.out.printf("id after while loop is %d\n", id);
		int y  = id ; 
		return y; 
		
	}
	
	public void beFriendUser(int user_id , int friend_id) {
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
			}
			
		}
	}
	
	public void unFriendUser(int user_id,  int friend_id) {
		if(user_id == friend_id) {
		}else {
			String query = "delete FROM FOLLOWERS WHERE user_id = ? AND friend_id = ?;"; 
			PreparedStatement statement;
			try {
				statement = conn.prepareStatement(query);
				statement.setInt(1, user_id);
				statement.setInt(2, friend_id);
				statement.executeUpdate(); 
			} catch (SQLException e) {
			} 
		}
		
	}
	

	public boolean isFriend(User loggedIn_user , User other) {
		boolean status = false ; 
		try {
			Statement statement = LibraryUI.conn.createStatement(); 
			int loggedIn_id = getUserId(loggedIn_user);
			int other_id = getUserId(other); 
			System.out.println(loggedIn_id);
			String query = "Select friend_id from FOLLOWERS where user_id = "+ Integer.toString(loggedIn_id) + ";"; 

			ResultSet set = statement.executeQuery(query);
			while(set.next())
			{
				if( set.getInt(1) == other_id) {
					status = true; 
					System.out.println(status); 
				}
			}
		} catch (SQLException e) {
			System.out.println("not a friend"); 
		}
		return status; 
	}
	




}

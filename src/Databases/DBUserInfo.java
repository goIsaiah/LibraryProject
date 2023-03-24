package Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DomainObjects.User;
import GUI.LibraryUI;

public class DBUserInfo {
	static String url = "jdbc:mysql://localhost:3306/myDB";
	static String urlRoot = "jdbc:mysql://localhost:3306";
	static String user = "root";
	static String password = LibraryUI.sqlpassword; 
	private Connection conn; 
	
	public DBUserInfo() throws SQLException {
		conn = DriverManager.getConnection(url, user, password);
	}
	
	public void addUserInfo(User user) throws SQLException {
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String url = user.getURL();
		String bio = user.getBio();
		String email = user.getEmail();
		int id = getUserId(email);
		
		if (id != -1) {
		    String insertQuery = "INSERT INTO USERINFO (user_id, URL, TEXTFIELD, FIRSTNAME, LASTNAME) VALUES (?, ?, ?, ?, ?)";
		    try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
		        insertStmt.setInt(1, id);
		        insertStmt.setString(2, url);
		        insertStmt.setString(3, bio);
		        insertStmt.setString(4, firstName);
		        insertStmt.setString(5, lastName);
		        insertStmt.executeUpdate();
		    }
		}
	}

	private int getUserId(String email) throws SQLException {
		int userId = -1;
		String selectQuery = "SELECT user_id FROM USERTABLE WHERE EMAIL=?";
		try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
		    selectStmt.setString(1, email);
		    try (ResultSet rs = selectStmt.executeQuery()) {
		        if (rs.next()) {
		            userId = rs.getInt("user_id");
		        }
		    }
		}
		return userId;
	}

	private int getInfoId(int userid) throws SQLException {
		int id = 0;
		String selectQuery = "SELECT * FROM userinfo WHERE user_id=?";
		try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
		    selectStmt.setInt(1, userid);
		    try (ResultSet rs = selectStmt.executeQuery()) {
		        while (rs.next()) {
		            id = rs.getInt("info_id");
		        }
		    }
		}
		return id;
	}

	public User getUserInfo(User currUser) {
	    try {
	        int userId = getUserId(currUser.getEmail());
	        int infoId = getInfoId(userId);

	        String firstName = "";
	        String lastName = "";
	        String bio = "";
	        String userUrl = "";

	        // Retrieve the user info from the database
	        Statement stmt = conn.createStatement();
	        String query = "SELECT * FROM USERINFO WHERE info_id = " + infoId;
	        ResultSet rs = stmt.executeQuery(query);

	        if (rs.next()) {
	            firstName = rs.getString("FIRSTNAME");
	            lastName = rs.getString("LASTNAME");
	            bio = rs.getString("TEXTFIELD");
	            userUrl = rs.getString("URL");
	        }

	        // Assign the retrieved user info to the User object
	        currUser.setFirstName(firstName);
	        currUser.setLastName(lastName);
	        currUser.setBio(bio);
	        currUser.setURL(userUrl);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return currUser;
	}

}

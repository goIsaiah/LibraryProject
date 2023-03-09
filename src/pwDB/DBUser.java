package pwDB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUser {
	
	static String url = "jdbc:mysql://localhost:3306/myDB";
	static String urlRoot = "jdbc:mysql://localhost:3306";
	static String user = "root";
	static String password = "1977"; //Change this
	
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



}

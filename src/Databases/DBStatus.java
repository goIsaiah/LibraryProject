package Databases;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import GUI.LibraryUI;
import java.sql.*;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import com.mysql.cj.util.StringUtils;
import DomainObjects.Book;
import GUI.LibraryUI;
import Logic.GoogleJSON;
import java.time.LocalDate;

// Checks if book is checked out or not
public class DBStatus {
//	static String url = "jdbc:mysql://localhost:3306/myDB";
//	static String urlRoot = "jdbc:mysql://localhost:3306";
//	static String user = "root";
//	static String password = LibraryUI.sqlpassword;

	
	private void checkOut(Book book, LocalDate date) {
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    boolean userExists = false;
	    String title = book.getTitle();
	    
	    
	    try {
//	        conn = DriverManager.getConnection(url, user, password);
	    	conn = DBUtil.getConnection(DBType_enum.ONLINE);
	    	
	        String queryCheck = "SELECT * FROM statustable WHERE TITLE = ? AND YEAR = ? AND MONTH = ? AND DAY = ?";
	        PreparedStatement pstmtCheck = conn.prepareStatement(queryCheck);
	        String sql = "INSERT INTO statustable (TITLE, YEAR, MONTH, DAY) VALUES (?, ?, ?, ?)";
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, title);
	        //stmt.setString(2, );
	        rs = stmt.executeQuery();
	        
	        if (rs.next()) {
	            userExists = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	}
	
	
}
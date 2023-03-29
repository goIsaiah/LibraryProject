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
import DomainObjects.User;
import GUI.LibraryUI;
import Logic.GoogleJSON;
import java.time.LocalDate;

// Checks if book is checked out or not
public class DBStatus {
	static String url = "jdbc:mysql://localhost:3306/myDB";
	static String urlRoot = "jdbc:mysql://localhost:3306";
	static String user = "root";
	static String password = LibraryUI.sqlpassword;
	private Connection conn;
	
	public DBStatus() throws SQLException {
		conn = DriverManager.getConnection(url, user, password);
	}
	
	// Use this if the book's entry does not exist yet
	public void addBookInfo(Book book) throws SQLException {
		String bookTitle = book.getTitle();
		String insertQuery = "INSERT INTO STATUSTABLE (user_id, URL, TEXTFIELD, FIRSTNAME, LASTNAME) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
			insertStmt.setString(1, bookTitle);
			insertStmt.setString(2, null);
			insertStmt.setInt(3, 0);
			insertStmt.setInt(4, 0);
			insertStmt.setInt(5, 0);
			insertStmt.executeUpdate();
		}
	}
	
	private void checkOut(Book book, LocalDate date) {
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    boolean userExists = false;
	    String title = book.getTitle();
	    
	    try {
	        conn = DriverManager.getConnection(url, user, password);
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
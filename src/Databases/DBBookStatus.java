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
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;
import com.mysql.cj.util.StringUtils;
import DomainObjects.Book;
import DomainObjects.User;
import GUI.LibraryUI;
import Logic.GoogleJSON;
import java.time.LocalDate;
import java.util.Date;

// Checks if book is checked out or not
public class DBBookStatus {
//	static String url = "jdbc:mysql://localhost:3306/myDB";
//	static String urlRoot = "jdbc:mysql://localhost:3306";
//	static String user = "root";
//	static String password = LibraryUI.sqlpassword;

	
	public void checkOut(Book book, User user) {
		bookExists(book);
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    String title = book.getTitle();
	    String username = user.getUsername();
	    
	    java.util.Date date= new Date();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH);
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    boolean isAvailable = isBookAvailable(book);
	    
	    if (isAvailable == true) {
		    try {
		        // conn = DriverManager.getConnection(url, user, password);
		    	conn = DBUtil.getConnection(DBType_enum.ONLINE);
		    	
		        String queryCheck = "UPDATE STATUSTABLE SET USER=?, MONTH=?, DAY=?, YEAR=? where TITLE=?";
		        stmt = conn.prepareStatement(queryCheck);
		        stmt.setString(1, username);
		        stmt.setInt(2, month);
		        stmt.setInt(3, day);
		        stmt.setInt(4, year);
		        stmt.setString(5, title);
		        rs = stmt.executeQuery();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    System.out.println(title + " checked out by: " + username);
	    }
	    else {
	    	System.out.println("Book is unavailable.");
	    }
	}
	
	private void bookExists(Book book) {
		String title = book.getTitle();
		try {
			Connection con = DBUtil.getConnection(DBType_enum.ONLINE);
		    String queryCheck = "SELECT * FROM STATUSTABLE WHERE TITLE = ?";
		    PreparedStatement pstmtCheck = con.prepareStatement(queryCheck);
		    pstmtCheck.setString(1, title);
		    ResultSet resultSet = pstmtCheck.executeQuery();
		    
		    if (!resultSet.next()) { // If no record exists, insert a new one
		        String sql = "INSERT INTO LIBRARY (TITLE, USER, MONTH, DAY, YEAR) VALUES (?, ?, ?, ?, ?)";
		        PreparedStatement pstmt = con.prepareStatement(sql);
		        pstmt.setString(1, title);
		        pstmt.setString(2, null);
		        pstmt.setInt(3, 0);
		        pstmt.setInt(4, 0);
		        pstmt.setInt(5, 0);
		        pstmt.executeUpdate();
		    } 
		    resultSet.close();
		    pstmtCheck.close();
		    con.close();
		} catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void returnBook(Book book) {
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    String title = book.getTitle();
	    String username = null;
	    
	    java.util.Date date= new Date();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    int year = 0;
	    int month = 0;
	    int day = 0;
	    
	    try {
//	        conn = DriverManager.getConnection(url, user, password);
	    	conn = DBUtil.getConnection(DBType_enum.ONLINE);
	    	
	        String queryCheck = "UPDATE STATUSTABLE SET USER=?, MONTH=?, DAY=?, YEAR=? where TITLE=?";
	        //String sql = "INSERT INTO statustable (TITLE, YEAR, MONTH, DAY) VALUES (?, ?, ?, ?)";
	        stmt = conn.prepareStatement(queryCheck);
	        stmt.setString(1, title);
	        stmt.setString(2, null);
	        stmt.setInt(3, 0);
	        stmt.setInt(4, 0);
	        stmt.setInt(5, 0);
	        rs = stmt.executeQuery();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public boolean isBookAvailable(Book book) {
		String title = book.getTitle();
		boolean available = true;
		try {
			Connection con = DBUtil.getConnection(DBType_enum.ONLINE);
		    String queryCheck = "SELECT * FROM STATUSTABLE WHERE TITLE=?";
		    PreparedStatement pstmtCheck = con.prepareStatement(queryCheck);
		    pstmtCheck.setString(1, title);
		    ResultSet resultSet = pstmtCheck.executeQuery();
		    
		    if (resultSet.getString("USER") != null) { // If book is checked out, it is unavailable
		        available = false;
		    } 
		    
		    resultSet.close();
		    pstmtCheck.close();
		    con.close();
		    //return available;
		} catch (SQLException e) {
	        e.printStackTrace();
	    }
		return available;
	}
}
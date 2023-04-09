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
import java.util.Calendar;

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
	    
	    // java.util.Date date= new Date();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());
	    cal.add(Calendar.DAY_OF_MONTH, 7);
	    int year = cal.get(Calendar.YEAR);
	    int month = (cal.get(Calendar.MONTH))+1;
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
		        //stmt.executeQuery();
		        stmt.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    System.out.println(title + " checked out by: " + username);
	    }
	    else {
	    	System.out.println("Book is unavailable.");
	    }
	}
	
	public void bookExists(Book book) {
		String title = book.getTitle();
		try {
			Connection con = DBUtil.getConnection(DBType_enum.ONLINE);
		    String queryCheck = "SELECT * FROM STATUSTABLE WHERE TITLE = ?";
		    PreparedStatement pstmtCheck = con.prepareStatement(queryCheck);
		    pstmtCheck.setString(1, title);
		    ResultSet resultSet = pstmtCheck.executeQuery();
		    
		    boolean isThere = false;
		    while (resultSet.next()) {
		    	if (resultSet.getString(1).equals(title)) {
		    		isThere = true;
		    		break;
		    	}
		    }
		    
		    if (isThere == false) { // If no record exists, insert a new one
		        String sql = "INSERT INTO STATUSTABLE (TITLE, USER, MONTH, DAY, YEAR) VALUES (?, ?, ?, ?, ?)";
		        PreparedStatement pstmt = con.prepareStatement(sql);
		        pstmt.setString(1, title);
		        pstmt.setString(2, null);
		        pstmt.setInt(3, 0);
		        pstmt.setInt(4, 0);
		        pstmt.setInt(5, 0);
		        pstmt.executeUpdate();
		        //pstmt.executeQuery();
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
	        stmt.setString(1, null);
	        stmt.setInt(2, year);
	        stmt.setInt(3, month);
	        stmt.setInt(4, day);
	        stmt.setString(5, title);
	        stmt.executeUpdate();
	        System.out.println(title + " was returned.");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public ArrayList<Book> searchLibrary(String username) throws SQLException {
		Connection con = LibraryUI.conn; 
		String sql = "SELECT title, user FROM STATUSTABLE WHERE user = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        ArrayList<Book> bookList= getBookList(rs);
		return bookList;
	}
	
	private ArrayList<Book> getBookList(ResultSet result) throws SQLException {
		ArrayList<Book> bookList = new ArrayList<Book>();
		DBMain db = new DBMain();
		while (result.next()) {
	        String title = result.getString("title");
	        bookList.addAll(db.searchLibrary(title));
		}
		return bookList;
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
		    if (resultSet.next()) {
		    	if (resultSet.getString(2) != null) { // If book is checked out, it is unavailable
			        available = false;
				}
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
	
	public boolean userHasBook (Book book, User user) {
		ArrayList<String> bookList = userBooks(user);
		
		String title = book.getTitle();
		for (int i = 0; i < bookList.size(); i++) {
			if (bookList.get(i).equals(title)) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<String> userBooks(User user) {
	    ArrayList<String> bookList = new ArrayList<String>();
	    String username = user.getUsername();
	    try (Connection con = DBUtil.getConnection(DBType_enum.ONLINE);
	         PreparedStatement pstmtCheck = con.prepareStatement("SELECT * FROM STATUSTABLE WHERE USER = ?")) {
	        pstmtCheck.setString(1, username);
	        try (ResultSet resultSet = pstmtCheck.executeQuery()) {
	            while (resultSet.next()) {
	                if (resultSet.getString(2).equals(username)) {
	                    bookList.add(resultSet.getString(1));
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return bookList;
	}
	
	public String getDueDate(Book book) {
		String date = "";
		int month, day, year;
		String title = book.getTitle();
		try {
			Connection con = DBUtil.getConnection(DBType_enum.ONLINE);
		    String queryCheck = "SELECT * FROM STATUSTABLE WHERE TITLE=?";
		    PreparedStatement pstmtCheck = con.prepareStatement(queryCheck);
		    pstmtCheck.setString(1, title);
		    ResultSet resultSet = pstmtCheck.executeQuery();
		    if (resultSet.next()) {
		    	if (resultSet.getString(1).equals(title)) { // If book is checked out, it is unavailable
			        /*
		    		month = resultSet.getInt(3);
			        day = resultSet.getInt(4);
			        year = resultSet.getInt(5);
			        */
		    		month = resultSet.getInt(4);
			        day = resultSet.getInt(5);
			        year = resultSet.getInt(3);
			        date = "Due: " + month + "/" + day + "/" + year;
			        return date;
				}
		    }	
		    resultSet.close();
		    pstmtCheck.close();
		    con.close();
		} catch (SQLException e) {
	        e.printStackTrace();
	    }
		return date;
	}

}
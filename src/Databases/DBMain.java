package Databases;

import java.sql.*;
import java.util.ArrayList;

import com.mysql.cj.util.StringUtils;

import DomainObjects.Book;


public class DBMain { 
	static String url = "jdbc:mysql://localhost:3306/myDB";
	static String urlRoot = "jdbc:mysql://localhost:3306";
	static String user = "root";
	static String password = "1977";
	
	public DBMain() throws SQLException {
	}

	public ArrayList<Book> searchLibrary(String search) throws SQLException {
		Connection con = DriverManager.getConnection(url, user, password);
		String selectString = "SELECT * FROM LIBRARY WHERE LIB_TITLE LIKE ?";
		PreparedStatement statement = con.prepareStatement(selectString);
	    statement.setString(1, "%" + search + "%");
	    ResultSet resultSet = statement.executeQuery();
	    ArrayList<Book> bookList  = getBookList(resultSet);
		return bookList;
	}
	
	private ArrayList<Book> getBookList(ResultSet result) throws SQLException {
		ArrayList<Book> bookList = new ArrayList<Book>();
		while (result.next()) {
	        String title = result.getString("LIB_TITLE");
	        String author = result.getString("LIB_AUTHOR");
	        String isbn = result.getString("LIB_ISBN");
	        int year = result.getInt("LIB_YEAR");
	        Book book = new Book(title, author, year, isbn);
	        bookList.add(book);
		}
		return bookList;
	}

	private static void printDB(ResultSet result) throws SQLException {
		while (result.next()) {
	        int bookId = result.getInt("LIB_ID");
	        String title = result.getString("LIB_TITLE");
	        String author = result.getString("LIB_AUTHOR");
	        String isbn = result.getString("LIB_ISBN");
	        int year = result.getInt("LIB_YEAR");
	        System.out.printf("%d %s %s %s %d%n", bookId, title, author, isbn, year);
		}
	}
	

}

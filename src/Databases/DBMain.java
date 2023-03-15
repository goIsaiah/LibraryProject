package Databases;

import java.sql.*;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mysql.cj.util.StringUtils;

import DomainObjects.Book;
import GUI.LibraryUI;
import Logic.GoogleJSON;


public class DBMain { 
	static String url = "jdbc:mysql://localhost:3306/myDB";
	static String urlRoot = "jdbc:mysql://localhost:3306";
	static String user = "root";
	static String password = LibraryUI.sqlpassword;
	
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

	public ArrayList<Book> getAPILibrary(String query) throws SQLException {
		ArrayList<Book> apibookList = new ArrayList<>();
        GoogleJSON gJSON = new GoogleJSON();
        JSONObject jobj = gJSON.getSearch(query);
        JSONObject titleobj = gJSON.getSearchIndex(jobj, 1);
        String title = gJSON.getSearchName(titleobj);
        String publisher = gJSON.getSearchPublisher(titleobj);
        JSONArray Authors = gJSON.getSearchAuthor(titleobj);
        Integer Year = Integer.valueOf(gJSON.getSearchYear(titleobj));
        String ISBN = gJSON.getSearchISBN13(titleobj);
//        String Genre = gJSON.getSearchGenre(titleobj);
        Book e = new Book(title, Authors.toString(), Year, ISBN);
        DBBookfromAPI(title, Authors.toString(), Year, ISBN);
        apibookList.add(e);
        return apibookList;
	}
	
	public static void DBBookfromAPI(String title, String author, int year, String ISBN) throws SQLException {
		Connection con = DriverManager.getConnection(url, user, password);
        String sql = "INSERT INTO LIBRARY (LIB_TITLE, LIB_AUTHOR, LIB_ISBN, LIB_YEAR) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,title);
        pstmt.setString(2, author);
        pstmt.setString(3, ISBN);
        pstmt.setInt(4, year);
        pstmt.executeUpdate();

    }

}

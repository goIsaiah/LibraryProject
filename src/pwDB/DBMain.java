package pwDB;

import java.sql.*;
import java.util.ArrayList;

import DomainObjects.Book;

public class DBMain {
	static String url = "jdbc:mysql://localhost:3306/myDB";
	static String urlRoot = "jdbc:mysql://localhost:3306";
	static String user = "root";
	static String password = "1977";
	static String create_LIBTABLE =
					"create table LIBRARY " 
					+ "(LIB_ID integer NOT NULL, "
					+ "LIB_TITLE varchar(40) NOT NULL, "
					+ "LIB_AUTHOR varchar(40) NOT NULL, "
					+ "LIB_YEAR integer NOT NULL, "
					+ "LIB_ISBN varchar(40) NOT NULL, "
					+ "PRIMARY KEY (LIB_ID))";
	
	public DBMain() throws SQLException {
		batchInsert();
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
	
	private static boolean tableExists(Connection conn, String tableName) throws SQLException {
	    DatabaseMetaData meta = conn.getMetaData();
	    ResultSet resultSet = meta.getTables(null, null, tableName, new String[] {"TABLE"});
	    return resultSet.next();
	}

	private static void batchInsert() {
		try {
			Connection con = DriverManager.getConnection(urlRoot, user, password);
			boolean exists = tableExists(con, "LIBRARY");
			if (exists) {
//				printDB(con);
				System.out.println("Database exists!");
		    }
			else {
				
				Statement statement = con.createStatement();
	            statement.executeUpdate("CREATE SCHEMA mydb");
	            con = DriverManager.getConnection(url, user, password);
	            statement = con.createStatement();
				statement.executeUpdate(create_LIBTABLE);
				DBBooks(con);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	private static void DBBooks(Connection con) throws SQLException{
		String insertString = "INSERT INTO LIBRARY (LIB_ID, LIB_TITLE, LIB_AUTHOR, LIB_ISBN, LIB_YEAR) VALUES (?, ?, ?, ?, ?)";
	    PreparedStatement statement = con.prepareStatement(insertString);
	    
	    statement.setInt(1, 1);
	    statement.setString(2, "To Kill a Mockingbird");
	    statement.setString(3, "Harper Lee");
	    statement.setString(4, "0446310789");
	    statement.setInt(5, 1960);
	    statement.executeUpdate();

	    statement.setInt(1, 2);
	    statement.setString(2, "1984");
	    statement.setString(3, "George Orwell");
	    statement.setString(4, "0446310789");
	    statement.setInt(5, 1949);
	    statement.executeUpdate();

	    statement.setInt(1, 3);
	    statement.setString(2, "Pride and Prejudice");
	    statement.setString(3, "Jane Austen");
	    statement.setString(4, "0446310789");
	    statement.setInt(5, 1813);
	    statement.executeUpdate();
	    
	    statement.setInt(1, 4);
	    statement.setString(2, "Harry Potter and the Sorcerer's Stone");
	    statement.setString(3, "J. K. Rowling");
	    statement.setString(4, "1338596705");
	    statement.setInt(5, 2020);
	    statement.executeUpdate();
	    
	    statement.setInt(1, 5);
	    statement.setString(2, "Diary of a Wimpy Kid");
	    statement.setString(3, "Jeff Kinney");
	    statement.setString(4, "0141324902");
	    statement.setInt(5, 2007);
	    statement.executeUpdate();
	    
	    statement.setInt(1, 6);
	    statement.setString(2, "The Great Gatsby");
	    statement.setString(3, "F. Scott Fitzgerald");
	    statement.setString(4, "9780743273565");
	    statement.setInt(5, 1925);
	    statement.executeUpdate();
	    
	    statement.setInt(1, 7);
	    statement.setString(2, "Fahrenheit 451");
	    statement.setString(3, "Ray Bradbury");
	    statement.setString(4, "9781451673319");
	    statement.setInt(5, 2011);
	    statement.executeUpdate();
	    
	    statement.setInt(1, 8);
	    statement.setString(2, "Murder on the Orient Express");
	    statement.setString(3, "Agatha Christie");
	    statement.setString(4, "9780007119318");
	    statement.setInt(5, 2007);
	    statement.executeUpdate();
	}
}

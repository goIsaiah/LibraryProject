package Databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DomainObjects.Book;
import DomainObjects.Comment;
import DomainObjects.User;
import GUI.LibraryUI;



public class DBForum {
	
	// databse 
	private  String user = "root";
	private String url = "jdbc:mysql://localhost:3306/myDB"; 
	public static String password = LibraryUI.sqlpassword; 
	private String query; 
	private Connection conn; 
	private Statement stmt; 
	
	public DBForum() {

		try {
			conn = DriverManager.getConnection(url,user, password);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

	
	
	
	public  void addComment(Comment comment) throws SQLException {
		query = "INSERT INTO COMMENTS(usrname, book_id, book_title, comment) VALUES (?, ?, ? ,?)";
		
		
			if(!comment.getMessage().equals("")) {
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setString(1, comment.getUserName() );
				statement.setInt(2,  comment.getBook_Id());
				statement.setString(3, comment.getBook_Title() );
				statement.setString(4, comment.getMessage() );

				statement.executeUpdate();
			}else {
				System.out.println("Message is empty!");
			}

	}
	

	
	public ArrayList<String> getComments(int id) throws SQLException {
		query = "SELECT comment FROM COMMENTS WHERE book_id = '" + id +"';";
		ResultSet rs = null; 
		
		ArrayList<String> list = new ArrayList<>(); 
	
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				list.add(rs.getString(1) + "\n");
			}
			
		return list;
		
	}
	
	public ArrayList<Comment> getCommentList(int id) throws SQLException {
		ArrayList<Comment> list = new ArrayList<>();
		query = "SELECT book_title, comment, usrname, book_id FROM COMMENTS WHERE book_id = '" + id + "';";
		ResultSet result = stmt.executeQuery(query);
		while (result.next()) {
	        String title = result.getString("book_title");
	        String commentString = result.getString("comment");
	        String usrname = result.getString("usrname");
	        int bookid = result.getInt("book_id");
	        User user = new User(usrname);
	        Comment comment = new Comment(user, commentString, title, bookid);
	        list.add(comment);
		}
		return list;
	}
	
	


}
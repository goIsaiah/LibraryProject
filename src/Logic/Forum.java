package Logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DomainObjects.Book;
import DomainObjects.User;


public class Forum {
	private Book book;
	private ArrayList<Comment> comments;
	
	// databse 
	private  String user = "root";
	private  String url = ""; 
	private  String password = "Fade2black"; 
	
	/*
	 * Assumees that table Commeents(id int , comment text, user varchar(255), book_title varchar(255)) 
	 * is in the database
	 */
	
	public  void addComment(Comment comment) {
		Connection conn = null;
		Statement stmt = null;
		url = "jdbc:mysql://localhost:3306/bookmateD";
		String bookTitle= "Harry Poter"; 
		String query = "INSERT INTO Comments(id, comment, user, book_title)"
				+ "VALUES" + "("+"\'3\'"+"," 
				+"\'"+ comment.getMessage() +"\'"+"," + "\'"+comment.getUser()+"\'" +"," +"\'"+ bookTitle+"\'"+  ")";
		
		try {
			conn = DriverManager.getConnection(url,user, password);
			stmt = conn.createStatement();
			
			
			//TODO remove duplicates 
			
			
			int res = stmt.executeUpdate(query);
			System.out.println(res);
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
		} 
	}
	
	public void removeComment(int id) {

		for(int i = 0 ; i<comments.size(); i++) {
			if(comments.get(i).getId() == id )
				comments.remove(i); 
		}
		
	}
	

	

	
	
	
}

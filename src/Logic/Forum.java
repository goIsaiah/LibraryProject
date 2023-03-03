package Logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class Forum {
	
	// databse 
	private  String user = "root";
	private  String url = ""; 
	private  String password = "Fade2black"; 
	private String query; 
	private Connection conn; 
	private Statement stmt; 
	
	
	/*
	 * Assumees that table Commeents(id int , comment text, user varchar(255), book_title varchar(255)) 
	 * is in the database
	 */
	
	public Forum() {
		url = "jdbc:mysql://localhost:3306/bookmateD";
		try {
			conn = DriverManager.getConnection(url,user, password);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public  void addComment(Comment comment) {

		String bookTitle= "Harry Poter"; 
		query = String.format("INSERT INTO Comments(id, comment, user, book_title) VALUES (\'%d\', \'%s\', \'%s\', \'%s\'  );"
					, comment.getId(), comment.getMessage(), comment.getUser(), bookTitle);
				
		try {

			
			if(!comment.getMessage().equals("")) {
				int res = stmt.executeUpdate(query);
				System.out.println(res);
			}else {
				System.out.println("message is empty");
			}
			
			
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
		} 	
	}
	
	public void removeComment(int id) {
		query = "Delete from Comments where " + Integer.toString(id) + " ;";
		
		try {
			
			int res = stmt.executeUpdate(query);
			System.out.println(res);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	
	public ArrayList<String> getComments() {
		query = "SELECT comment from Comments;"; 
		ResultSet rs = null; 
		
		ArrayList<String> list = new ArrayList<>(); 
		
		try {
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				list.add(rs.getString(1) + "\n");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	

}

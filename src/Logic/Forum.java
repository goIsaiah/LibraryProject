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
	private String url = "jdbc:mysql://localhost:3306/myDB"; 
	private  String password = "Fade2black"; 
	private String query; 
	private Connection conn; 
	private Statement stmt; 
	
	
	/*
	 * Assumees that table Commeents(id int , comment text, user varchar(255), book_title varchar(255)) 
	 * is in the database
	 */
	
	public Forum() {

		try {
			conn = DriverManager.getConnection(url,user, password);
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public  void addComment(Comment comment) throws SQLException {

		query = String.format("INSERT INTO COMMENTS(usrname, book_id, book_title, comment) VALUES (\'%s\', %d, \'%s\', \'%s\' );"
					, comment.getUserName(), comment.getBook_Id(),comment.getBook_Title(), comment.getMessage());

			if(!comment.getMessage().equals("")) {
				stmt.executeUpdate(query);
			}else {
				System.out.println("message is empty");
			}

	}
	

	
	public ArrayList<String> getComments() throws SQLException {
		query = "SELECT comment from COMMENTS;"; 
		ResultSet rs = null; 
		
		ArrayList<String> list = new ArrayList<>(); 
		
	
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				list.add(rs.getString(1) + "\n");
			}
			
		return list;
		
	}
	

}

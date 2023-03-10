package Logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import GUI.LibraryUI;



public class Forum {
	
	// databse 
	private  String user = "root";
	private String url = "jdbc:mysql://localhost:3306/myDB"; 
	static String password = LibraryUI.sqlpassword; 
	private String query; 
	private Connection conn; 
	private Statement stmt; 
	
	public Forum() {

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
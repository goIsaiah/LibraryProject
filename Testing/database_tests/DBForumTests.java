package database_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Databases.DBForum;
import Databases.DBType_enum;
import Databases.DBUser;
import Databases.DBUtil;
import DomainObjects.Comment;
import DomainObjects.User;
import GUI.LibraryUI;

class DBForumTests {
	
	/*
	 * addComment(Comment)
		getComments(int)
		getCommentList(int)
	 */
	
	public Connection con;
	public User user; 
	public DBForum forum ; 
	public Statement statement; 
	@BeforeEach
	public void init() {
		try {
			con = DBUtil.getConnection(DBType_enum.ONLINE);
			LibraryUI.conn = con;
			DBUser db = new DBUser(); 
			user = db.getUser(2);
			forum = new DBForum();
			statement = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	@Test
	void addComment_test_01() {
		String query = "Select * from Comments where book_id = 2"; 
		String message = "This is a comment for the book 1984 and a test for Forum";
		String expected = message + '\n'; 
		Comment comment = new Comment(user , message, "1984", 2);
		try {
			ResultSet r = statement.executeQuery(query); 
//			ArrayList<String> list = new ArrayList<>(); 
			boolean flag = false; 
			while(r.next()) {
				String actual = r.getString(1); 
				if(expected.equals(actual)) {
					flag = true; 
				}
			}
			
			assertTrue(flag); 
			forum.addComment(comment);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
//	@Test
	void getComments_test_01() {
		fail("Not yet implemented");
	}

	
	

}

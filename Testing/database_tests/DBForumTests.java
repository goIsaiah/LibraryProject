package database_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
		String query = "Select * from COMMENTS where book_id = 2;"; 
		String message = "This is a comment for the book 1984 and a test for Forum";
		String expected = message + '\n'; 
		Comment comment = new Comment(user , message, "1984", 2);
		try {
			boolean flag = false; 
			forum.addComment(comment);
			ResultSet r = statement.executeQuery(query); 
			while(r.next()) {
				String actual = r.getString(5); 
				
				if(message.equals(actual)) {
					System.out.printf("when equal %s\n", actual); 
					System.out.printf("when equal %s\n", expected); 
					flag = true; 
				}
			}
			assertTrue(flag); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
	@Test
	void getComments_test_01() {
		String query = "Select * from COMMENTS where book_id = 2";
		String message1 = "test for getComment 1";
		String message2 = "test for getComment 2";
		String message3 = "test for getComment 3";
		String expected1 = message1 + '\n'; 
		String expected2 = message2 + '\n'; 
		String expected3 = message3 + '\n'; 
		Comment comment1 = new Comment(user , message1, "1984", 2);
		Comment comment2 = new Comment(user , message2, "1984", 2);
		Comment comment3 = new Comment(user , message3, "1984", 2);
		
		ArrayList<String> expected = new ArrayList<>(); 
		expected.add(expected1);
		expected.add(expected2);
		expected.add(expected3);
		System.out.println(expected);
		
		try {
			forum.addComment(comment1);
			forum.addComment(comment2);
			forum.addComment(comment3);
			forum.getComments(2);
			ResultSet r = statement.executeQuery(query); 
			boolean flag = true;
			ArrayList<String> actual = forum.getComments(2);	
			System.out.println(actual);
			flag = actual.containsAll(expected); 
			assertTrue(flag); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	
	

}

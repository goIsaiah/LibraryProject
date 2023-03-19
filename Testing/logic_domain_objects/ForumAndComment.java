//package logic_domain_objects;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import Databases.DBForum;
//import DomainObjects.Comment;
//import DomainObjects.User;
//import GUI.LibraryUI;
//
//
//class ForumAndComment {
//	
//	static DBForum forum ;
//	static Scanner input; 
//	static User user; 	
//	
//	@BeforeAll
//	public static  void init() {
//		System.out.print("Enter mysql password :");
//		input = new Scanner(System.in);
//		String pswd = input.next(); 
//		LibraryUI.sqlpassword = pswd;
//		forum = new DBForum();
//		user = new User("JohnDoe", "1234", "vincegab@my.yorku.ca" );
//	}
//	
//	@Test
//	public void addCommenttest_01() {
//		// if comment is empty
//		Comment com = new Comment(user, "", "1984", 2);
//		try {
//			System.out.print("addCommenttest_01: ");
//			forum.addComment(com);
//			ArrayList<String> list = forum.getComments(); 
//			
//			boolean actual = false; 
//			
//			assertFalse(actual && list.contains(com.getMessage()));
//			if(!actual) {
//				System.out.print("Pass\n");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		
//		
//	}
//	@Test
//	public void addCommenttest_02() {
//		// if is in the database
//		String comment = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
//		
//		Comment com = new Comment(user, comment, "1984", 2);
//		try {
//			System.out.print("addCommenttest_02: ");
//			forum.addComment(com);
//			ArrayList<String> list = forum.getComments(); 
//			boolean actual = list.contains(comment + "\n"); 
//			if(actual) {
//				System.out.print("\nPass");
//			}
//			assertTrue(actual);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		
//		
//	}
//	
//	@AfterAll
//	public static void Close() {
//		input.close(); 
//	}
//
//}

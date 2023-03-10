package logic_domain_objects;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.util.*;

import org.junit.jupiter.api.Test;
import DomainObjects.*;
import GUI.LibraryUI;
import Logic.*;

class ReviewTests 
{

	@Test
	void toStringTest() 
	{
		Book b = new Book("Percy Jackson", "Rick Riordan", 2005, 4);
		User u = new User("abbey", "abbey123","abbey@gmail.com");
		String message = "Great book! So happy I read it. Would give 6 stars if I could !!";
		Review r = new Review(message, b, u);
		
		assertEquals(String.format("abbey reviewed Percy Jackson.\nReview: %s", message), r.toString());
		
	}
	
	@Test
	void editMessageTest()
	{
		Book b = new Book("Percy Jackson", "Rick Riordan", 2005, 4);
		User u = new User("abbey", "abbey123","abbey@gmail.com");
		String message = "Great book! So happy I read it. Would give 6 stars if I could !!";
		Review r = new Review(message, b, u);
		
		assertEquals(String.format("abbey reviewed Percy Jackson.\nReview: %s", message), r.toString());
		
		String newmessage = "Great book! So happy I read it.";
		r.editMessage(newmessage);
		assertEquals(String.format("abbey reviewed Percy Jackson.\nReview: %s\n(Edited)", newmessage), r.toString());
	}
	
	@Test
	void addReviewTest() throws SQLException
	{
		
		//this should be succesful
		System.out.println("Enter SQL password: ");
		Scanner sc = new Scanner(System.in);
		LibraryUI.sqlpassword = sc.nextLine();		
		String user = "root";
		String url = "";
		String password = LibraryUI.sqlpassword;
		
		/*
		 * assume that the table
		 * 
		 * CREATE TABLE Reviews(
			id INT NOT NULL,
			book VARCHAR(255),
			user VARCHAR(255),
			message TEXT,
			likes INT NOT NULL
			);

			is present in the database
		 */
		
		Book b = new Book("Percy Jackson", "Rick Riordan", 2005, 4);
		User u = new User("abbey", "abbey123","abbey@gmail.com");
		String message = "Great book! So happy I read it. Would give 6 stars if I could !!";
		Review r = new Review(message, b, u);
		r.likeMessage();
		r.likeMessage();
		r.likeMessage();
		
		
		Connection conn = null;
		Statement statement = null;
		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		url = "jdbc:mysql://localhost:3306/mydb";
		String query = String.format("INSERT INTO Reviews\n(id, book, user, message, likes) VALUES\n(%d, '%s', '%s', '%s', %d);", 1, r.getBook().getTitle(), r.getUser().getUsername(), r.getMessage(), r.getLikes());
		
		try
		{
			conn = DriverManager.getConnection(url, user, password);
			statement = conn.createStatement();

			//adding the query
			int res = statement.executeUpdate(query);
			System.out.println(res);
			statement.close();
			conn.close();
		}

		catch (SQLException e)
		{
			fail("exception thrown, error adding data into the tables");
			System.out.println(e.getMessage());
		}
		
		//check to see if the information was properly inputted into the database
		try
		{
			conn = DriverManager.getConnection(url, user, password);
			query = String.format("SELECT * FROM Reviews WHERE id = ? AND book = ? AND user = ? AND message = ? AND likes = ?");
			preparedstatement = conn.prepareStatement(query);
			preparedstatement.setInt(1, 1);
			preparedstatement.setString(2, b.getTitle());
			preparedstatement.setString(3, u.getUsername());
			preparedstatement.setString(4, message);
			preparedstatement.setInt(5, 3);
			rs = preparedstatement.executeQuery();
			if (rs.next() == false)
			{
				fail("error, not added to table");
			}
			
		}
		catch (SQLException e)
		{
			fail("exception thrown unexpectedly");
			System.out.println(e.getMessage());
		}
	}

}

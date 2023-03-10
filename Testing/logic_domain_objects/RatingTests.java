package logic_domain_objects;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.util.*;

import org.junit.jupiter.api.Test;
import DomainObjects.*;
import GUI.LibraryUI;
import Logic.*;

class RatingTests 
{

	@Test
	void toStringTest() 
	{
		Book b = new Book("Harry Potter", "JK Rowling", 2005, 4);
		User u = new User("abbey", "abbey123","abbey@gmail.com");
		Rating r = new Rating(u, b);
		r.setRating(5);
		assertEquals(5, r.getRating());
		
		assertEquals("abbey rated Harry Potter 5/5 stars", r.toString());
		
		
	}
	
	@Test
	void addRatingTest() throws SQLException
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
		 * CREATE TABLE Ratings(
			id INT NOT NULL,
			book VARCHAR(255),
			user VARCHAR(255),
			rating INT NOT NULL
			);

			is present in the database
		 */
		
		Book b = new Book("Harry Potter", "JK Rowling", 2005, 4);
		User u = new User("abbey", "abbey123","abbey@gmail.com");
		Rating r = new Rating(u, b);
		r.setRating(5);
		
		Connection conn = null;
		Statement statement = null;
		PreparedStatement preparedstatement = null;
		ResultSet rs = null;
		url = "jdbc:mysql://localhost:3306/mydb";
		String query = String.format("INSERT INTO Ratings\n(id, book, user, rating) VALUES\n(%d, '%s', '%s', %d);", 2, r.getBook().getTitle(), r.getUser().getUsername(), r.getRating());
		
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
			query = String.format("SELECT * FROM Ratings WHERE id = ? AND book = ? AND user = ? AND rating = ?");
			preparedstatement = conn.prepareStatement(query);
			preparedstatement.setInt(1, 2);
			preparedstatement.setString(2, b.getTitle());
			preparedstatement.setString(3, u.getUsername());
			preparedstatement.setInt(4, 5);
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

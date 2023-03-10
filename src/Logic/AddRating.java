package Logic;

import java.sql.*;
import java.util.*;

import DomainObjects.*;


public class AddRating 
{

	private ArrayList<Rating> ratings;
	private int id;
	//database
	private static String user = "root";
	private static String url = "";
	private static String password = "1224";

	public AddRating()
	{
		this.ratings = new ArrayList<Rating>();
		this.id = 0;
	}

	public static void main(String[] args) 
	{


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
		
		addRating(r);
		System.out.println(r.toString());



	}

	public static void addRating(Rating rating)
	{
		Connection conn = null;
		Statement statement = null;
		url = "jdbc:mysql://localhost:3306/BookMate";
		String query = String.format("INSERT INTO Ratings\n(id, book, user, rating) VALUES\n(%d, '%s', '%s', %d);", 2, rating.getBook().getTitle(), rating.getUser().getUsername(), rating.getRating());

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
			System.out.println(e.getMessage());
		}
	}

}

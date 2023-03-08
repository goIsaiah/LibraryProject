package Logic;

import java.sql.*;
import java.util.*;
import DomainObjects.*;

public class AddReview 
{
	private ArrayList<Review> reviews;
	private int id;

	//database
	private static String user = "root";
	private static String url = "";
	private static String password = "1224";

	public AddReview()
	{
		this.reviews = new ArrayList<Review>();
		this.id = 0;
	}

	public static void main(String[] args) 
	{
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

		addReview(r);
		System.out.println(r.toString());
	}

	public static void addReview(Review review)
	{
		Connection conn = null;
		Statement statement = null;
		url = "jdbc:mysql://localhost:3306/BookMate";
		String query = String.format("INSERT INTO Reviews\n(id, book, user, message, likes) VALUES\n(%d, '%s', '%s', '%s', %d);", 1, review.getBook().getTitle(), review.getUser().getUsername(), review.getMessage(), review.getLikes());

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

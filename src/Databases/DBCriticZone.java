package Databases;

import java.sql.*;
import java.util.ArrayList;

import DomainObjects.*;
import GUI.LibraryUI;

public class DBCriticZone 
{

	//declaring variables for the database
	private String user = "root";
	private String url = "jdbc:mysql://localhost:3306/myDB";
	private static String password = LibraryUI.sqlpassword;
	private String query;
	private Connection connection;
	private Statement statement;

	public DBCriticZone()
	{
		//establish a connection
		try
		{
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
		}
		catch (SQLException e) //catch any errors when connecting to the DB
		{
			e.printStackTrace();
		}
	}

	public void addCritic(Critic c)
	{
		Connection conn = null;
		Statement statement = null;
		url = "jdbc:mysql://localhost:3306/myDB";

		String query = String.format("INSERT INTO CRITICS\n(usrname, book_title, message, rating, book_id) VALUES\n('%s', '%s', '%s', '%d', '%d')", c.getUser().getUsername(), c.getBook().getTitle(), c.getReview().getMessage(), c.getRating().getRating(), c.getBook().getId());

		//checking to see if the rating is empty
		if (c.getReview().getMessage().isEmpty() || c.getRating().getRating() == 0)
		{
			System.out.println("review is empty, or no rating provided.");
		}
		else
		{
			try
			{
				//attempt to add the the query into the table
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
	
	public ArrayList<String> getReviews(int id) throws SQLException
	{
		query = String.format("SELECT message FROM CRITICS where book_id = '%d'", id);
		ResultSet rs = null;
		
		ArrayList<String> reviews = new ArrayList<>();
		
		rs = statement.executeQuery(query);
		while (rs.next())
		{
			reviews.add(rs.getString(1) + "\n");
		}
		
		return reviews;
	}
	
	public ArrayList<Integer> getRatings(int id) throws SQLException
	{
		query = String.format("SELECT rating FROM CRITICS where book_id = '%d'", id);
		ResultSet rs = null;
		
		ArrayList<Integer> ratings = new ArrayList<>();
		
		rs = statement.executeQuery(query);
		while (rs.next())
		{
			ratings.add(rs.getInt(1));
		}
		
		return ratings;
	}
	
	public ArrayList<Critic> getCriticList(int id) throws SQLException
	{
		ArrayList<Critic> criticList = new ArrayList<>();
		query = String.format("SELECT book_title, message, rating, usrname, book_id FROM CRITICS WHERE book_id = '%d'", id);
		ResultSet rs = statement.executeQuery(query);
		
		
		while (rs.next())
		{
			String title = rs.getString("book_title");
			String review = rs.getString("message");
			String username = rs.getString("usrname");
			int rating = rs.getInt("rating");
			int bookid = rs.getInt("book_id");
			
			//creating objects
			Book b = new Book(title);
			User u = new User(username);
			Review rev = new Review(review, b, u);
			Rating ra = new Rating(u, b);
			ra.setRating(rating);
			Critic c = new Critic(b, u, rev, ra, bookid);
			
			//add into arrayList
			
			criticList.add(c);
		}
		return criticList;
	}
	
	
	
}

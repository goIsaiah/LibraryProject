package Databases;

import java.sql.*;

import DomainObjects.Critic;
import DomainObjects.Rating;
import DomainObjects.Review;
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
		url = "jdbc:mysql://localhost:3306/BookMate";

		String query = String.format("INSERT INTO RATINGSANDREVIEWS\n(usrname, book_title, message, rating) VALUES\n(%s, '%s', '%s', %d);", c.getUser().getUsername(), c.getBook().getTitle(), c.getReview().getMessage(), c.getRating().getRating());

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
}

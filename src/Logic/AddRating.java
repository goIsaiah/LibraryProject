package Logic;

import java.sql.*;
import java.util.*;


public class AddRating 
{

	private ArrayList<Rating> ratings;
	private int id;
	
	public AddRating()
	{
		this.ratings = new ArrayList<Rating>();
		this.id = 0;
	}
	
	//database
	private String user = "root";
	private String url = "";
	private String password = "1224";
	
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
	
	public void addRating(Rating rating)
	{
		Connection conn = null;
	}
}

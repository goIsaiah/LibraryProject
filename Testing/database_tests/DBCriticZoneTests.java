package database_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import DomainObjects.*;

class DBCriticZoneTests {

	@Test
	void addCriticTest()
	{
		Book b = new Book(5, "Diary of a Wimpy Kid", "Jeff Kinney", 2007, "0141324902");
		User u = new User("abbey22", "1224", "a.jarmillan@gmail.com");
		Review review = new Review("average", b, u);
		Rating rating = new Rating(u, b);
		rating.setRating(4);
		Critic c = new Critic(b, u , review, rating, b.getId());
		Databases.DBCriticZone db = new Databases.DBCriticZone();

		try
		{
			db.addCritic(c);
		}
		catch (SQLException e)
		{
			fail("critic not added into table");
		}

	}

	@Test
	void getRatingsTest()
	{

		ArrayList<Integer> ratings = new ArrayList<Integer>();
		Book b = new Book(5, "Diary of a Wimpy Kid", "Jeff Kinney", 2007, "0141324902");
		Databases.DBCriticZone db = new Databases.DBCriticZone();

		try
		{
			ratings = db.getRatings(b.getId());
			assertTrue(ratings.get(0).equals(5));
			assertEquals(ratings.get(1), 2);
		}
		catch(SQLException e)
		{
			fail("list of ratings not returned");
		}
	}
	
	@Test
	void getRatingsTest2()
	{

		ArrayList<Integer> ratings = new ArrayList<Integer>();
		Book b = new Book(7, "Fahrenheit 451", "Ray Bradbury", 2011, "9781451673319");
		Databases.DBCriticZone db = new Databases.DBCriticZone();

		try
		{
			ratings = db.getRatings(b.getId());
			assertTrue(ratings.get(0).equals(3));
		}
		catch(SQLException e)
		{
			fail("list of ratings not returned");
		}
	}
	
	@Test
	void getCriticListTest()
	{
		ArrayList<Critic> criticList = new ArrayList<>();
		Book b = new Book(5, "Diary of a Wimpy Kid", "Jeff Kinney", 2007, "0141324902");
		Databases.DBCriticZone db = new Databases.DBCriticZone();
		
		try
		{
			criticList = db.getCriticList(b.getId());
			
			assertEquals(criticList.get(0).toString(), "abbey22 gave Diary of a Wimpy Kid 5 stars.\nReview: insane book");
			
		}
		catch (SQLException e)
		{ 
			fail("list of critics not returned");
		}
		
		
	}
	
	@Test
	void getCriticListTest2()
	{
		ArrayList<Critic> criticList = new ArrayList<>();
		Book b = new Book(7, "Fahrenheit 451", "Ray Bradbury", 2011, "9781451673319");
		Databases.DBCriticZone db = new Databases.DBCriticZone();
		
		try
		{
			criticList = db.getCriticList(b.getId());
			assertEquals(criticList.get(0).toString(), "Polywertz gave Fahrenheit 451 3 stars.\nReview: Decent book!");
			
		}
		catch (SQLException e)
		{ 
			fail("list of critics not returned");
		}
		
		
	}
	


}

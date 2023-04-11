package database_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import DomainObjects.*;

class DBMainTests {

	@Test
	void searchLibraryTest() throws SQLException 
	{
		Databases.DBMain db = new Databases.DBMain();
		ArrayList<Book> listOfBooks = new ArrayList<Book>();
		try
		{
			listOfBooks = db.searchLibrary("Fahrenheit 451");
			assertTrue(listOfBooks.get(0).getTitle().equals("Fahrenheit 451"));
		}
		catch(SQLException e)
		{
			fail("search not working properly");
		}
	}
	
	@Test
	void getAPILibraryTest() throws SQLException
	{
		ArrayList<Book> apiBookList = new ArrayList<Book>();
		Databases.DBMain db = new Databases.DBMain();
		try 
		{
			apiBookList = db.getAPILibrary("percy jackson");
			assertTrue(apiBookList.get(0).getTitle().equals("Percy Jackson's Greek Heroes"));
			
		} 
		catch (SQLException e) 
		{
			fail("search not working properly");
		}
	}
	
	//getAPI Library class
//get max id
	
}

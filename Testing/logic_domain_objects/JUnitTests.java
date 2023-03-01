package logic_domain_objects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import DomainObjects.User;
import Logic.AlreadyAFriendException;
import Logic.Authentication;
import Logic.Citation;
import Logic.GoogleJSON;
import Logic.SearchforBook;
import DomainObjects.Book;
import Databases.bookmain;

class JUnitTests {


	@Test
	void authenticationTest()
	{
		User user = new User("abbey", "abbey123", "abbey@my.yorku.ca");
		Authentication a = new Authentication(user);

		//testing the authentication methods
		//correctName method
		assertTrue(a.correctName("abbey"));
		assertFalse(a.correctName("jimmy"));

		//correctPassword method
		assertTrue(a.correctPassword("abbey123"));
		assertFalse(a.correctName("Abbey123"));

		//authenticated method
		assertEquals(user.getUsername(), "abbey");
		assertEquals(user.getPassword(), "abbey123");
		assertTrue(a.authenticated(a.correctName("abbey"), a.correctPassword("abbey123")));
		assertFalse(a.authenticated(a.correctName("abbey"), a.correctPassword("abbey1234")));
		assertFalse(a.authenticated(a.correctName("jimmy"), a.correctPassword("abbey123")));
	}

	@Test
	void userTest()
	{
		User abbey = new User("abbey", "abbey123", "abbey@my.yorku.ca");
		User vince = new User("vince", "vince123", "vince@my.yorku.ca");
		User sathira = new User("sathira", "sathira123", "sathira@my.yorku.ca");
		User isaiah = new User("isaiah", "isaiah123", "isaiah@my.yorku.ca");
		User jet = new User("jet", "jet123", "jet@my.yorku.ca");
		User badfriend = new User("badfriend", "badfriend123", "badfriend@my.yorku.ca");

		//testing the addFriend method
		try
		{
			abbey.addFriend(vince);
			abbey.addFriend(sathira);
			abbey.addFriend(isaiah);
			abbey.addFriend(jet);
			abbey.addFriend(badfriend);
		}
		catch (AlreadyAFriendException e)
		{
			fail("error thrown unexpectedly");
		}

		//trying to friend a user that has already been friended
		try
		{
			abbey.addFriend(vince);
			fail("exception not thrown");
		}
		catch(AlreadyAFriendException e)
		{

		}


		//testing the remove friend function
		try
		{
			abbey.removeFriend(badfriend); //this should succeed
		}
		catch (Exception e)
		{
			fail("error thrown unexpectedly");
		}

		//trying to remove a friend not on friend list
		try
		{
			abbey.removeFriend(badfriend); //now they are no longer friends, this should throw excpetion
			fail("exception not thrown");
		}
		catch (Exception e)
		{

		}
	}

	@Test
	void checkOutandReturnBookstest()
	{
		User abbey = new User("abbey", "abbey123", "abbey@my.yorku.ca");
		Book book = new Book("The Fault in Our Stars", "John Green", 2012, 4);

		try
		{
			abbey.checkoutBook(book);
		}
		catch(Exception e)
		{
			fail("exception thrown unexpectedly");
		}

		//try to checkout the book again (should fail)
		try
		{
			abbey.checkoutBook(book);
			fail("expected error not thrown");
		}
		catch(Exception e)
		{
			
		}
		
		//return the book
		try
		{
			abbey.returnBook(book);
		}
		catch(Exception e)
		{
			fail("exception thrown unexpectedly");
		}
		
		//try and return the book again (should fail)
		try
		{
			abbey.returnBook(book);
			fail("exception thrown unexpectedly");
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test
	void addandRemovefromFavourites()
	{
		User abbey = new User("abbey", "abbey123", "abbey@my.yorku.ca");
		Book book = new Book("The Fault in Our Stars", "John Green", 2012, 4);

		try
		{
			abbey.addToFavourites(book);
		}
		catch(Exception e)
		{
			fail("exception thrown unexpectedly");
		}

		//try to add to favourites again (should fail)
		try
		{
			abbey.addToFavourites(book);
			fail("expected error not thrown");
		}
		catch(Exception e)
		{
			
		}
		
		//remove from favourites
		try
		{
			abbey.removeFromFavourites(book);
		}
		catch(Exception e)
		{
			fail("exception thrown unexpectedly");
		}
		
		//try and return the book again (should fail)
		try
		{
			abbey.removeFromFavourites(book);
			fail("exception thrown unexpectedly");
		}
		catch(Exception e)
		{
			
		}
	}

//	@Test
	void GoogleJSONtest() 
	{

		GoogleJSON googleAPI = new GoogleJSON();
		String ISBN = "‎‎‎9780451524935";
		JSONObject isbnJSON = googleAPI.getJSON(ISBN);

		//		System.out.print("Enter search: ");
		String searchKey = "Harry Potter";

		JSONObject searchJSON = googleAPI.getSearch(searchKey);


		//10 is the max amount of results output by GAPI 
		JSONObject search = googleAPI.getSearchIndex(searchJSON, 0); //Search API, 0 gives the top search result
		String name = googleAPI.getSearchName(search); //Title of book
		String rating = googleAPI.getSearchRating(search); //Rating out of five
		String coverURL = googleAPI.getSearchCoverURL(search); //Cover URL
		JSONArray authors = googleAPI.getSearchAuthor(search); //Author Array
		String year = googleAPI.getSearchYear(search); //Year of published
		String publisher = googleAPI.getSearchPublisher(search); //Publisher
		// (APA) AUTHOR, FIRSTINTIAL, MIDDLEINITIAL, YEAR, TITLE, PUBLISHER
		//		System.out.println(name + rating + year + publisher);


		//writing assertions to ensure that our search functionality works
		assertEquals("Fantastic Beasts and Where to Find Them", name);
		assertEquals("4", rating);
		assertEquals("2017", year);
		assertEquals("Blurb", publisher);

		//testing another case
		searchKey = "The Fault in Our Stars";
		searchJSON = googleAPI.getSearch(searchKey);
		search = googleAPI.getSearchIndex(searchJSON, 0); //Search API, 0 gives the top search result
		name = googleAPI.getSearchName(search); //Title of book
		rating = googleAPI.getSearchRating(search); //Rating out of five
		coverURL = googleAPI.getSearchCoverURL(search); //Cover URL
		authors = googleAPI.getSearchAuthor(search); //Author Array
		year = googleAPI.getSearchYear(search); //Year of published
		publisher = googleAPI.getSearchPublisher(search); //Publisher

		//		System.out.println(name + rating + year + publisher);

		//writing assertions for the second test case
		assertEquals("The Fault in Our Stars", name);
		assertEquals("4", rating);
		assertEquals("2012", year);
		assertEquals("Penguin Books", publisher);


	}

	@Test
	void SearchforBook() {
		SearchforBook search = new SearchforBook();
		bookmain booklist = new bookmain();
		ArrayList<Book> b = booklist.getList();
		String title = "Harry Potter: The Goblet of Fire";
		assertTrue(title, search.checkBook(title));


	}

}

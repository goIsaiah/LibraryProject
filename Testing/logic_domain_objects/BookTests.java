package logic_domain_objects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import DomainObjects.Book;
import DomainObjects.User;

class BookTests {

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

}

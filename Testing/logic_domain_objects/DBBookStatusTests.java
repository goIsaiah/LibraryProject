package logic_domain_objects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.sql.*;
import java.util.*;
import DomainObjects.*;
import GUI.LibraryUI;
import Logic.*;

class DBBookStatusTests {
	
	//@Test
	void bookExistsTest() {
		Book b = new Book("Harry Potter", "JK Rowling", 2005, 4);
		Databases.DBBookStatus db = new Databases.DBBookStatus();
		db.bookExists(b);
	}

	@Test
	void checkOutBookTest() {
		Databases.DBBookStatus db = new Databases.DBBookStatus();
		Book b = new Book("The Fault in Our Stars", "John Green", 2012, 4);
		db.returnBook(b);
		User u = new User("isaiah", "isaiah123","isaiah@gmail.com");
		//Book b = new Book("Harry Potter", "JK Rowling", 2005, 4);
		db.checkOut(b, u);
		//db.returnBook(b);
		boolean available = db.isBookAvailable(b);
		User u2 = new User("abbey", "abbey123","abbey@gmail.com");
		db.checkOut(b, u2);
		assertFalse(available);
	}

}

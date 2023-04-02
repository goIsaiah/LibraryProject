package logic_domain_objects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.sql.*;
import java.util.*;
import DomainObjects.*;
import GUI.LibraryUI;
import Logic.*;

class DBBookStatusTests {

	@Test
	void checkOutBookTest() {
		User u = new User("isaiah", "isaiah123","isaiah@gmail.com");
		Book b = new Book("Harry Potter", "JK Rowling", 2005, 4);
		Databases.DBBookStatus db = new Databases.DBBookStatus();
		db.checkOut(b, u);
		boolean available = db.isBookAvailable(b);
		User u2 = new User("abbey", "abbey123","abbey@gmail.com");
		db.checkOut(b, u2);
		assertFalse(available);
	}

}

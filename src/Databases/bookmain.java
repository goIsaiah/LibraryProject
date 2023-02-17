package Databases;


import java.util.ArrayList;
import DomainObjects.Book;

public class bookmain {


		

		Book Book1 = new Book("Harry Potter: The Goblet of Fire", "JK Rowlking", 2000, 5 );

		Book Book2 = new Book("The Hobbit", "J.R.R Tolkien", 1937, 5);

		Book Book3 = new Book("The Hunger Games", "Suzane Collins", 2008, 7);

		Book Book4 = new Book("The Maze Runner", "James Dashner", 2009, 8);

		Book Book5 = new Book("IT", "Stephen King", 1986, 9);
		
		ArrayList<Book> Books; 
		
		
	public bookmain() {
		
		Books = new ArrayList<Book>();

		Books.add(Book1);
		Books.add(Book2);
		Books.add(Book3);
		Books.add(Book4);
		Books.add(Book5);
		
		
	}
	
	   public ArrayList<Book> getList() {
	       return Books;
	   }
	

	
		
		

	

}

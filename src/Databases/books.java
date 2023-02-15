package Databases;


import java.util.ArrayList;
import DomainObjects.Book;

public class books {
	private ArrayList<Book> book = new ArrayList<Book>();

	public ArrayList<Book> getBook() {
		return book;
	}

	public void setBook(ArrayList<Book> book) {
		this.book = book;
	}
	

	public void addBook(Book b) {
		book.add(b);
		
	}
	

}




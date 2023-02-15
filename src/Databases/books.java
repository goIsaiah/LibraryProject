package Databases;

import java.util.ArrayList;

public class books {
	private ArrayList<book> book = new ArrayList<book>();

	public ArrayList<book> getBook() {
		return book;
	}

	public void setBook(ArrayList<book> book) {
		this.book = book;
	}
	

	public void addBook(book b) {
		book.add(b);
		
	}
	

}




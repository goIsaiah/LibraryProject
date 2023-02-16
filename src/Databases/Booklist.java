package Databases;

import DomainObjects.Book;

public interface Booklist {
	public static final BookDB bookList = new BookDB(); 
	public boolean inCollection(String bookName); 
	public Book getBook(String bookName);
	public void insertBook(Book book);
}

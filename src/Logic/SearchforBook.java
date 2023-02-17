package Logic;
import java.util.ArrayList;

import Databases.bookmain;
import DomainObjects.Book;
public class SearchforBook {
private String query;
private bookmain b = new bookmain();
private Book searchResult;


public SearchforBook() {
	
}

public Book getSearchBook() {
	return searchResult;
}

public Boolean checkBook(String q) {
	
	ArrayList<Book> Books = b.getList(); 
	
	for(int i = 0; i <= Books.size()-1; i++) {
		String a = Books.get(i).getTitle();
		if(q.equals(a)) {
			searchResult = Books.get(i);
			return true;
		}
	}


	return false;
	
}

}

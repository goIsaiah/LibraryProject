package Logic;
import java.util.ArrayList;

import Databases.bookmain;
import DomainObjects.Book;
public class SearchforBook {
private String query;
private bookmain b;



public SearchforBook() {
	bookmain b = new bookmain();
	ArrayList<Book> Books = b.getList(); 
}

public Boolean checkBook(String q) {
	
	ArrayList<Book> Books = b.getList(); 
	
	for(int i = 0; i <= Books.size(); i++) {
		String a = Books.get(i).getTitle();
		if(q.equals(a)) {
			return true;
		}
	}


	return false;
	
}

}

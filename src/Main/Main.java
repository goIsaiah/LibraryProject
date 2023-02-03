package Main;

import org.json.JSONObject;
import java.util.Scanner;

//https://www.googleapis.com/books/v1/volumes?q{}
public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	googleJSON googleAPI = new googleJSON();
    	String ISBN = "‎‎‎9780451524935";
    	JSONObject isbnJSON = googleAPI.getJSON(ISBN);
    	
    	System.out.print("Enter search: ");
    	String searchKey = sc.nextLine();
    	sc.close();
    	JSONObject searchJSON = googleAPI.getSearch(searchKey);
    	
    	for (int i = 0; i < 10; i++) {//10 is the max amount of results output by GAPI 
        	JSONObject search = googleAPI.getSearchIndex(searchJSON, i);
        	String name = googleAPI.getSearchName(search); 
        	String rating = googleAPI.getSearchRating(search);
        	String coverURL = googleAPI.getSearchCoverURL(search);
        	System.out.print(name);
        	System.out.println(", Rated: "+ rating + " stars, " + coverURL);
    	}
    	
    }
   
}

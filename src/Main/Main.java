package Main;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	GoogleJSON googleAPI = new GoogleJSON();
    	String ISBN = "‎‎‎9780451524935";
    	JSONObject isbnJSON = googleAPI.getJSON(ISBN);
    	
    	System.out.print("Enter search: ");
    	String searchKey = sc.nextLine();
    	sc.close();
    	JSONObject searchJSON = googleAPI.getSearch(searchKey);
    	
    	for (int i = 0; i < 1; i++) {//10 is the max amount of results output by GAPI 
        	JSONObject search = googleAPI.getSearchIndex(searchJSON, i); //Search API
        	String name = googleAPI.getSearchName(search); //Title of book
        	String rating = googleAPI.getSearchRating(search); //Rating out of five
        	String coverURL = googleAPI.getSearchCoverURL(search); //Cover URL
        	JSONArray authors = googleAPI.getSearchAuthor(search); //Author Array
        	String year = googleAPI.getSearchYear(search); //Year of published
        	String publisher = googleAPI.getSearchPublisher(search); //Publisher
        	// (APA) AUTHOR, FIRSTINTIAL, MIDDLEINITIAL, YEAR, TITLE, PUBLISHER
        	Citation cite = new Citation();
        	String apa = cite.getAPA(search);
        	System.out.println(", Rated: "+ rating + " stars, " + coverURL);
    	}
    	
    }
   
}
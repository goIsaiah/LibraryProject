package Main;

import org.json.JSONObject;

//https://www.googleapis.com/books/v1/volumes?q{}
public class Main {
	
    public static void main(String[] args) {
    	googleJSON googleAPI = new googleJSON();
//    	String ISBN = "‎‎‎9780451524935";
//    	JSONObject isbnJSON = googleAPI.getJSON(ISBN);
    	
    	String searchKey = "Pokemon";
    	JSONObject searchJSON = googleAPI.getSearch(searchKey);
    	
    	for (int i = 0; i < 10; i++) {//10 is the max amount of results output by GAPI 
        	JSONObject search = googleAPI.getSearchIndex(searchJSON, i);
        	String name = googleAPI.getSearchName(search); 
        	String rating = googleAPI.getSearchRating(search);
        	System.out.print(name);
        	System.out.println(", Rated: "+ rating + " stars");
    	}
    	
    }
   
}

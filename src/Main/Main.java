package Main;

import org.json.JSONObject;

//https://www.googleapis.com/books/v1/volumes?q{}
public class Main {
    public static void main(String[] args) {
    	googleJSON googleAPI = new googleJSON();
    	String ISBN = "9780451524935";
    	JSONObject isbnJSON = googleAPI.getJSON(ISBN);
    	
    	String searchKey = "Hunger Games";
    	JSONObject searchJSON = googleAPI.getSearch(searchKey);
    	
    	System.out.println(searchJSON);
    }
   
}

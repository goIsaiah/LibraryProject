package Main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class googleJSON {
	public JSONObject getJSON(String ISBN){
        JSONObject json = null;
        String urlString = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + ISBN;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            json = new JSONObject(response.toString());
//            JSONObject item = json.getJSONArray("items").getJSONObject(0);
//            JSONObject volumeInfo = item.getJSONObject("volumeInfo");
//            String title = volumeInfo.getString("title");
//            System.out.println("The title of the book is: '"+title+"'");
            
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return json;

	}

	public JSONObject getSearch(String searchKey) {
		JSONObject json = null;
		searchKey = removeSpaces(searchKey);
        String urlString = "https://www.googleapis.com/books/v1/volumes?q={" + searchKey + "}";
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            json = new JSONObject(response.toString());
//            JSONObject item = json.getJSONArray("items").getJSONObject(0);
//            JSONObject volumeInfo = item.getJSONObject("volumeInfo");
//            String title = volumeInfo.getString("title");
//            System.out.println("The title of the book is: '"+title+"'");
            
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return json;
	}

	private String removeSpaces(String s) {
		return s.replaceAll(" ", "%20");
	}


	
}

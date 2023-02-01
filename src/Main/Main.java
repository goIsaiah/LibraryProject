package Main; 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        String ISBN = "0439023521";
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

            JSONObject json = new JSONObject(response.toString());
//            JSONObject item = json.getJSONArray("items").getJSONObject(0);
//            JSONObject volumeInfo = item.getJSONObject("volumeInfo");
//            String title = volumeInfo.getString("title");
            System.out.println(json);

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

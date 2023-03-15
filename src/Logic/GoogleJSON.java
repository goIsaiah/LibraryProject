package Logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleJSON {
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
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return json;
	}
	
	public JSONObject getSearchIndex(JSONObject searchJSON, int searchIndex) {
		JSONArray arr = searchJSON.getJSONArray("items");
    	JSONObject indexJSON = (JSONObject) arr.get(searchIndex);
		return indexJSON;
	}

	public String getSearchName(JSONObject search) {
		JSONObject volumeInfo = search.getJSONObject("volumeInfo");
		String title = volumeInfo.getString("title");
		return title;
	}

	public String getSearchRating(JSONObject search) {
		String rating = "No";
		try {
			JSONObject volumeInfo = search.getJSONObject("volumeInfo");
			int x = volumeInfo.getInt("averageRating");
			rating = String.format("%d", x);
		}catch (Exception e) {
		}
		return rating;
	}

	public String getSearchCoverURL(JSONObject search) {
		String URL = "";
		try {
			JSONObject volumeInfo = search.getJSONObject("volumeInfo");
			JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
			URL = imageLinks.getString("thumbnail");
		}catch (Exception e) {
		}
		return URL;
	}

	
	public JSONArray getSearchAuthor(JSONObject search) {
		JSONArray aArr = null;
		try {
			JSONObject volumeInfo = search.getJSONObject("volumeInfo");
			aArr = volumeInfo.getJSONArray("authors");
		}catch (Exception e) {
		}
		return aArr;
	}

	public String getSearchYear(JSONObject search) {
		String pubYear = "";
		try {
			JSONObject volumeInfo = search.getJSONObject("volumeInfo");
			pubYear = volumeInfo.getString("publishedDate");
			pubYear = pubYear.substring(0, 4);
		}catch (Exception e) {
		}
		return pubYear;
	}

	public String getSearchPublisher(JSONObject search) {
		String pub = "";
		try {
			JSONObject volumeInfo = search.getJSONObject("volumeInfo");
			pub = volumeInfo.getString("publisher");
		}catch (Exception e) {
		}
		return pub;
	}
	
	public String getSearchISBN13(JSONObject search) {
        String isbn13 = "";
        try {
            JSONObject volumeInfo = search.getJSONObject("volumeInfo");
            JSONArray industryIdentifiers = volumeInfo.getJSONArray("industryIdentifiers");
            for (int i = 0; i < industryIdentifiers.length(); i++) {
                JSONObject identifier = industryIdentifiers.getJSONObject(i);
                if (identifier.getString("type").equals("ISBN_13")) {
                    isbn13 = identifier.getString("identifier");
                    break;
                }
            }
        } catch (Exception e) {
        }
        return isbn13;
    }

    public String getSearchGenre(JSONObject search) {
        String genre = "";
        try {
            JSONArray categories = search.getJSONArray("categories");
            genre = categories.getString(0);
        } catch (Exception e) {
        }
        return genre;
    }
	
	private String removeSpaces(String s) {
		return s.replaceAll(" ", "%20"); //%20 is a whitespace for URLS
	}

	
}
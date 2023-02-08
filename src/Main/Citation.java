package Main;

import org.json.JSONArray;
import org.json.JSONObject;

public class Citation {
	GoogleJSON googleAPI = new GoogleJSON();
	
	public String getAPA(JSONObject search) {
		// (APA) AUTHOR, FIRSTINTIAL, MIDDLEINITIAL, YEAR, TITLE, PUBLISHER
		String apa = "";
		String authors = "";
		JSONArray aArr = googleAPI.getSearchAuthor(search); //Author
		int numOfAuthor = getNumAuthors(search);
		boolean noAuthors = false;
		
		switch(numOfAuthor) {
		case 0:
			noAuthors = true; 
			break;
		case 1: 
			authors = getAuthorFormat(aArr.getString(0));
			break;
		case 2: 
			String author1 = getAuthorFormat(aArr.getString(0));
			String author2 = getAuthorFormat(aArr.getString(1));
			authors = author1 + " & " + author2;
			break;
		default:
			authors = getAuthorFormat(aArr.getString(0)) + " et al.";
		}
		
		String pub = googleAPI.getSearchPublisher(search);
		String name = googleAPI.getSearchName(search);
		String year = googleAPI.getSearchYear(search);
		
		if (noAuthors || authors == "Anonymous") {
			apa = name + ",(" + year + "). " + pub + ".";
		}
		
		else if(!noAuthors) {
			apa = authors + ",(" + year + "). " + name + ", " + pub + ".";
		}
		return  apa;
	}

	private String getAuthorFormat(String fullName) {
		try {
			String nameArr[] = fullName.split(" ");
			String firstName = nameArr[0];
			String lastName = nameArr[1].substring(0,1);
			return firstName +", " + lastName + ".";
			} catch (Exception e) {
			return "Anonymous";
			}
	}

	private int getNumAuthors(JSONObject search) {
		try {
		return googleAPI.getSearchAuthor(search).length();
		} catch (Exception e) {
		return 0;
		}
	}

}
package Logic;

import org.json.*;

public class SampleGoogleJson {

	public static void main(String[] args) {
		GoogleJSON gJSON = new GoogleJSON();
		JSONObject jobj = gJSON.getSearch("Harry Potter");
		JSONObject titleobj = gJSON.getSearchIndex(jobj, 1);
		String title = gJSON.getSearchName(titleobj);
		System.out.println(title);
	}

}

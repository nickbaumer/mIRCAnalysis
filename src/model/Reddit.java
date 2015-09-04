package model;

import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Reddit {
	
	public static String redditTopLink(String subreddit) {
    	String output = new String();
	try {

		URL reddit = new URL("http://www.reddit.com/r/" + subreddit + ".json");
		HttpURLConnection connection = (HttpURLConnection)reddit.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		
		int code = connection.getResponseCode();
		System.out.println("response code: "+code);
		if (code != 200) { output = "This is not a valid subreddit."; }
		else {
			BufferedReader br = new BufferedReader(
				new InputStreamReader(reddit.openStream()));
	
			String inputLine;
			 StringBuilder builder = new StringBuilder();
		        while ((inputLine = br.readLine()) != null)
		        	builder.append(inputLine);		        	
		        	
		        br.close();
		        
		        JsonParser parser = new JsonParser();
		        JsonObject rootObj = parser.parse(builder.toString()).getAsJsonObject();
		        JsonObject locObj = rootObj
		                              .getAsJsonObject("data")
		                              .getAsJsonArray("children")
		                              .get(0)
		                              .getAsJsonObject()
		                              .getAsJsonObject("data");
	
		        String url = locObj.get("url").getAsString();
		        String title = locObj.get("title").getAsString();
		        String permalink = locObj.get("permalink").getAsString();
		        output = title + " - " + url + " - http://www.reddit.com" + permalink;
		        System.out.println(output);
		}
	        
	} catch (IOException e) {
		e.printStackTrace();
	}
	return output;
    }
}
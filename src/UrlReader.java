import java.net.*;

import java.io.*;

public class UrlReader {
	public static String getText(String url) throws Exception{
		//connecting to api link
		URL steamSite = new URL(url);
		HttpURLConnection connect = (HttpURLConnection)steamSite.openConnection();	
		//sending request "GET" and User Agent
		connect.setRequestMethod("GET");
		connect.setRequestProperty("User-Agent", "Mozilla/5.0");
		//System.out.println(connect.getResponseCode());	//logging
		
		//getting stream
		StringBuilder context = new StringBuilder();
		BufferedReader input = new BufferedReader(new InputStreamReader(connect.getInputStream()));
		
		//getting text 
		String line = "";
		while((line = input.readLine()) != null)
			context.append(line);
		input.close();
	
		return context.toString();
	}
}
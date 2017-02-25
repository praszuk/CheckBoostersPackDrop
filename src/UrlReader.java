import java.net.*;

import java.io.*;

public class UrlReader {
	public static String getText(String url) throws Exception {
		URL steamSite = new URL(url);
		URLConnection connect = steamSite.openConnection();	
		BufferedReader input = new BufferedReader(new InputStreamReader(connect.getInputStream()));
		
		StringBuilder context = new StringBuilder();
		
		String line = "";
		while((line = input.readLine()) != null)
			context.append(line);
		input.close();
	
		return context.toString();
	}
}
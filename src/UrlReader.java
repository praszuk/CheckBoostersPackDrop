import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class UrlReader {
	public static String getText(String url) throws IOException {

		//connecting to api link
		URL steamSite = new URL(url);
		HttpURLConnection connect = (HttpURLConnection)steamSite.openConnection();

		//sending request "GET" and User Agent
		connect.setRequestMethod("GET");
		connect.setRequestProperty("User-Agent", "Chrome/44.0");

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
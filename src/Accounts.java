import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Accounts {	
	/**
	 * Extract and count number of booster packs from Web API json.
	 * @return json with inventory.
	 */
	static String getInfo(long id64, String nickName) throws IOException {
		final String jsonFromURL = UrlReader.getText("http://steamcommunity.com/inventory/"+id64+"/753/6?l=english&count=5000");
		
		//regex yet Json parser soon
		Pattern p = Pattern.compile("\"type\":\"Booster Pack\"");
		Matcher m = p.matcher(jsonFromURL);
		
		//counter of boosterPacks
		int boosterPackDropped = 0;
		while(m.find())
			++boosterPackDropped;
		
		//TODO formatting to equals space between columns
		return nickName + ": " + boosterPackDropped;
	}
}

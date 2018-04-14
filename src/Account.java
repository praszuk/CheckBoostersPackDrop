import java.io.IOException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * General class to managment user accounts and data updating.
 * @author Paweł Raszuk
 *
 */
public class Account {
	private String nickName;
	private long steamId64;
	private int boosterPacks;

	public Account(long steamId64,String nickName) {
		this.steamId64 = steamId64;
		this.nickName = nickName;
		boosterPacks = 0;
	}

	/**
	 * Extract and count number of booster packs from Steam Web API json.
	 *
	 * Structure of JSON (Listed only needed):
	 * {
	 *  assests[]{
	 *  			appid 		- id of steam game application
	 *  			contextid 	- id of steam inventory category, like Steam/CS:GO/Dota tabs view
	 *  			assetid
	 *  			classid
	 *  			...
	 *  		 }
	 *  descriptions[]  {
	 *  					...
	 *  					classid
	 *  					...
	 *  					icon_url - not direct url just String without base address
	 *  					...
	 *  					name
	 *  					type
	 *  					...
	 *  					marketable
	 *  					...
	 *  				}
	 *  int total_inventory_count - it's important to load next pages (by classid last item) if this is more than 5000 (max limit).
	 *  int success
	 *  int rwgrsn - idk what doesn't mean
	 * }
	 *
	 */
	private void getDropNumber() throws IOException {
		final String jsonFromURL = UrlReader.getText("https://steamcommunity.com/inventory/"+steamId64+"/753/6?l=english&count=5000");

		JsonElement elem = new JsonParser().parse(jsonFromURL);
		JsonObject obj = elem.getAsJsonObject();
		JsonArray arr = obj.getAsJsonArray("descriptions");

		int counter = 0;

		if (arr != null)
			for (JsonElement o : arr)
				if(o.getAsJsonObject().get("type").toString().equals("\"Booster Pack\""))
					++counter;

		boosterPacks = counter;
	}

	public void update() throws IOException {
		getDropNumber();
	}

	// getters
	public int getBoosterPacks() { return boosterPacks; }
	public long getSteamId64() { return steamId64; }
	public String getNickName() { return nickName; }

	// setter
	public void setNickName(String nickName) { this.nickName = nickName; }

}

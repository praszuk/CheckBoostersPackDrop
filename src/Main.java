import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Main {
	
	static String getInfo(Entry<Long, String> account){
		String jsonFromURL = "";
		try {
			jsonFromURL = UrlReader.getText("http://steamcommunity.com/inventory/"+account.getKey()+"/753/6?l=english&count=5000");
		} catch (Exception e){
			e.printStackTrace();
		}
		
		Pattern p = Pattern.compile("\"type\":\"Booster Pack\"");					 //new pattern
		Matcher m = p.matcher(jsonFromURL);
		
		int boosterPackDropped = 0;
		while(m.find())
			++boosterPackDropped;
		
		// Print and show
		String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println(timeStamp + " > "+account.getValue()+": "+boosterPackDropped); 	//logging
		
		return account.getValue()+": "+boosterPackDropped;
	}
	
	public static void main(String[] args) {
		
		LinkedHashMap<Long, String> acc = AccountsFile.readAccounts(); 
		StringBuffer sb = new StringBuffer();
		
		for (Entry<Long, String> account : acc.entrySet()) //SteamIDs64 https://steamid.io/
			sb.append(getInfo(account)+"\n");
		
		//show in window
		JOptionPane.showMessageDialog(null, sb, "Booster Packs", JOptionPane.INFORMATION_MESSAGE);	
	}
}
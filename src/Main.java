import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Main {
	
	static String getInfo(long account){
		String jsonFromURL = "";
		try {
			jsonFromURL = UrlReader.getText("http://steamcommunity.com/inventory/"+account+"/753/6?l=english&count=5000");
		} catch (Exception e){
			e.printStackTrace();
		}
		
		//Pattern p = Pattern.compile("\"name\":\"(Booster Pack)|(Pakiet kart)\"");  //old pattern
		Pattern p = Pattern.compile("\"type\":\"Booster Pack\"");					 //new pattern
		Matcher m = p.matcher(jsonFromURL);
		
		int boosterPackDropped = 0;
		while(m.find())
			++boosterPackDropped;
		
		String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println(timeStamp + " > "+account+": "+boosterPackDropped); 	//logging
		return "ID "+account+": "+boosterPackDropped;
	}
	
	public static void main(String[] args) {
		//SteamIDs64 https://steamid.io/
		final long[] steamID = {76561198082669058l, 76561198262069609l, 76561198269625175l, 76561198270204982l, 76561198269700897l, 76561198285164380l, 76561198286733504l, 76561198288290355l};
		StringBuffer sb = new StringBuffer();
		
		//getting data from accounts 
		for (int i = 0;i<steamID.length;i++){
			sb.append(getInfo(steamID[i])+"\n");
		}	
		//show in window
		JOptionPane.showMessageDialog(null, sb, "Booster Packs", JOptionPane.INFORMATION_MESSAGE);	
	}
}
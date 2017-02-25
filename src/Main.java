import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Main {
	
	static String getInfo(int account){
		String jsonFromURL = "";
		try {
			jsonFromURL = UrlReader.getText("http://steamcommunity.com/id/lizakfake"+account+"/inventory/json/753/6");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(jsonFromURL); //print source from site.
		Pattern p = Pattern.compile("\"name\":\"(Booster Pack)|(Pakiet kart)\"");
		Matcher m = p.matcher(jsonFromURL);
		
		int boosterPackDropped = 0;
		while(m.find())
			++boosterPackDropped;
		
		String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		System.out.println(timeStamp + " > lizakfake"+account+": "+boosterPackDropped); //logging
		return "lizakfake"+account+": "+boosterPackDropped;
	}
	
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		
		//loop for check all account
		for (int i = 1;i<=8;i++){
			try {
				sb.append(getInfo(i)+"\n");
				if (i%3==0)
					Thread.sleep(120001); //every 120 seconds delay
			}catch (InterruptedException e) {e.printStackTrace();}
		}	
		//show in window
		JOptionPane.showMessageDialog(null, sb, "Pakiety kart na fejk kontach", JOptionPane.INFORMATION_MESSAGE);	
	}
}
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		LinkedHashMap<Long, String> acc = null;
		final String path = System.getProperty("user.home")+"/acc.txt";
		
		//getting accounts from file
		try {
			acc = AccountsFile.readAccounts(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "File not found in: \n"+path, "Booster Packs", JOptionPane.INFORMATION_MESSAGE);
		} 
		
		//getting data
		StringBuilder sb = new StringBuilder();
		String log = "";
		for (Entry<Long, String> account : acc.entrySet()){
			try {
				log = Accounts.getInfo(account.getKey(),account.getValue());
				sb.append(log);
			} catch (IOException e) {
				log = account.getValue() + ": Private or no connection";
				sb.append(log);
			}
			finally{
				sb.append("\n");
				System.out.println(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) + " > "+ log);
			}
		}
				
		//show in GUI
		JOptionPane.showMessageDialog(null, sb, "Booster Packs", JOptionPane.INFORMATION_MESSAGE);
	}
}
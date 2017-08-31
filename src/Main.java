import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import gui.GUI;

public class Main {
	final static String path = System.getProperty("user.home")+"/acc.txt";
	static LinkedHashMap<Long, String> accsFromFile = null;
	static ArrayList<Account> accounts = new ArrayList<>();

	public static void main(String[] args) {

		// show in GUI
		GUI gui = new GUI();
		gui.setText("<html>Getting account(s) from file...</html>");

		// getting accounts from file
		try {
			accsFromFile = AccountsFile.readAccountsFromFile(new File(path));
			System.out.println("Found a file with probably " + accsFromFile.size() +" account(s)");
		}
		catch (FileNotFoundException e) {
			System.out.println("File with Accounts not found in " + path + " program terminating.");
			JOptionPane.showMessageDialog(null, "File not found in: \n"+path, "Booster Packs", JOptionPane.INFORMATION_MESSAGE);
			System.exit(1);
		}



		// creating objects of accounts
		for (Entry<Long, String> acc : accsFromFile.entrySet())
			accounts.add(new Account(acc.getKey(), acc.getValue()));

		// getting data
		StringBuilder sb = new StringBuilder(); // putting in "HTML style"
		sb.append("<html>");
		gui.setText("<html>Collecting data...(0/"+accounts.size()+")</html>");
		String log = "";

		for (int i = 0; i< accounts.size(); i++){
			try {
				accounts.get(i).update();
				log = accounts.get(i).getNickName() + ": " + accounts.get(i).getBoosterPacks();
			}
			catch (IOException e) { log = accounts.get(i).getNickName() + ": Private or No connection"; }
			finally {
				sb.append(log + "<br>");
				gui.setText("<html>Collecting data...("+(i+1)+"/"+accounts.size()+")</html>");
				System.out.println(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().getTime()) + " > "+ log);
			}
		}
		sb.append("</html>");

		// show in GUI
		gui.setText(sb.toString());
		System.out.println("Done");
	}
}
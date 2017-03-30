import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class AccountsFile {
	public static LinkedHashMap<Long, String> readAccounts() {
		Scanner sc = null;
		try {
			sc = new Scanner(new File("res/acc.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		LinkedHashMap<Long, String> acc = new LinkedHashMap<Long, String>();
		while (sc.hasNext()){
			acc.put(Long.parseLong(sc.next()), sc.next());
		}
		
		return acc;
	}
}

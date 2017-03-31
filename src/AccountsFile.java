import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class AccountsFile {
	public static LinkedHashMap<Long, String> readAccounts(String path) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(path));
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

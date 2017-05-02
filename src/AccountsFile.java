import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class AccountsFile {
	public static LinkedHashMap<Long, String> readAccounts(String path) throws FileNotFoundException {
		
		//scanner for file with id and nickNames
		Scanner sc = new Scanner(new File(path));
		LinkedHashMap<Long, String> acc = new LinkedHashMap<Long, String>();
		
		//getting id64 and nickname
		while (sc.hasNext())
			acc.put(Long.parseLong(sc.next()), sc.next());
		
		sc.close();
		
		return acc;
	}
}

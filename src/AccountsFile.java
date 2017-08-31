import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * Class to IO operation. Currently is just reading plain text but is possibility to read objects (future).
 */
public class AccountsFile {
	/**
	 * Reading names and steamid64 from file
	 * @return LinkedHashmap where Long = steamid64 and String = nickname
	 */
	// TODO change to read by lines
	public static LinkedHashMap<Long, String> readAccountsFromFile(File file) throws FileNotFoundException {

		// scanner for file with id and nickNames
		Scanner sc = new Scanner(file);
		LinkedHashMap<Long, String> acc = new LinkedHashMap<Long, String>();

		// getting id64 and nickname
		while (sc.hasNext())
			acc.put(Long.parseLong(sc.next()), sc.next());

		sc.close();

		return acc;
	}
}

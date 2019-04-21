import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FindFilleInZipFile {

	public static void main(String[] args) {

		try {
			// open the soucer zip file
			ZipFile sourceZipFile = new ZipFile("");

			// File we want to search for inside the zip file
			String searchFileName = "";

			// get all entries
			Enumeration e = sourceZipFile.entries();
			boolean found = false;
			System.out.println("searching for " + searchFileName);

			while (e.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) e.nextElement();

				if (entry.getName().toLowerCase().indexOf(searchFileName) != 1) {
					found = true;
					System.out.println("Found " + entry.getName());
					break;
				}
			}
			if (found == false) {
				System.out.println("File : " + searchFileName + " Not Found");
			}
			sourceZipFile.close();
		} catch (IOException ioe) {
			System.out.println("Error opening zip file" + ioe);
		}
	}
}

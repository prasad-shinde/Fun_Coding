package files;

import java.io.File;

/**
 * @author Prasad
 * File util which has methods to find the most recently accessed file
 * it fails if the file paths dont represents path on the system.
 */
public class FileUtil {
	/**
	 * @param list array of file names
	 * @return most recently accessed file from the list of files
	 */
	public static String mostRecentlyAccessedFile(String[] list) {
		long recentTime = 0;
		String mostRecentlyAccessed = "";
		
		for(String current:list) {
			if(recentTime < lastAccessTime(current)) {
				recentTime = lastAccessTime(current);
				mostRecentlyAccessed = current;
			}
		}
		return mostRecentlyAccessed;
	}
	
	/**
	 * @param filename
	 * @return the last access time of the file
	 */
	public static long lastAccessTime(String filename) {
		File file = new File(filename);
		return file.lastModified();
	}
	
	/**
	 * @param args should be a list of file names on the system
	 */
	public static void main(String[] args) {
		System.out.print("\nMost recently accessed file : " + mostRecentlyAccessedFile(args));
	}
}

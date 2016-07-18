package strings;

public class StringReverse {
	public static String reverse(String str) {
		StringBuffer reversed = new StringBuffer();
		int len = str.length();
		len--;
		while(len>=0) {
			reversed.append(str.charAt(len--));
		}
		return reversed.toString();
	}
}

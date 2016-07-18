package zillow.tree;

public class StringUtil {
	/**
	 * @param str the long representation of the string.
	 * @return the long value of string
	 * 
	 * No build in functions are used.
	 */
	public static long stringToLong(String str) {
		String reversed = reverse(str);
		int num;
		long value = 0;
		long multipleOfTen = 1;
		
		for(int i=0;i<reversed.length();i++) {
			num = reversed.charAt(i) - '1' + 1;
			if(num < 0 || num > 9) {
				throw new NumberFormatException("The string " + str + "has some non numeric characters");
			}
			value += num*multipleOfTen;
			multipleOfTen *= 10;
		}
		return value;
	}
	
	public static String reverse(String str) {
		StringBuffer reversed = new StringBuffer();
		int len = str.length();
		len--;
		while(len>=0) {
			reversed.append(str.charAt(len--));
		}
		return reversed.toString();
	}
	
	public static void main(String[] args) {
		System.out.print("value : " + stringToLong("234582"));
	}
}

package strings;

public class StringCompression {
	public static String compress(String uncompressed) {
		if(uncompressed == null)
			return null;
		StringBuffer compressed = new StringBuffer();
		char current;
		int count;
		int i = 0;
		while(i<uncompressed.length()) {
			current = uncompressed.charAt(i);
			count = 1;
			i++;
			while((i<uncompressed.length()) && (current == uncompressed.charAt(i))) {
				i++;
				count++;
			}
			if(count == 1)
				compressed.append(current);
			else {
				compressed.append(current);
				compressed.append(count);
			}
		}
		return compressed.toString();
	}
	
	public static void main(String[] args) {
		System.out.println("uncompressed : " + compress("null"));
	}
}

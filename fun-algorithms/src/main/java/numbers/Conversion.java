package numbers;

public class Conversion {
	public static String decimalToGivenBase(int decimal, int base) {
		String binary = "";
		
		while(decimal > 0) {
			int mod = decimal % base;
			binary = binary + mod;
			decimal = decimal / base;
		}
		return reverse(binary);
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
		String bin = decimalToGivenBase(16, 8);
		System.out.print(bin);
	}
}

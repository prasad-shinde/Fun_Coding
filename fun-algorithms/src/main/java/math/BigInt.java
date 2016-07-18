package math;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BigInt {
	
	/**
	 * adds two integers of arbitrary lengths represented as strings 
	 * 
	 * @param addend1
	 * @param addend2
	 * @return
	 */
	public static String add(String addend1, String addend2) {
	    StringBuilder buf = new StringBuilder();
	    for ( int i1 = addend1.length() - 1, i2 = addend2.length() - 1, carry = 0;
	          (i1 >= 0 || i2 >= 0) || carry != 0;
	          i1--, i2-- ) {
	        int digit1 = i1 < 0 ? 0 :
	                     Integer.parseInt(Character.toString(addend1.charAt(i1)));
	        int digit2 = i2 < 0 ? 0 :
	                     Integer.parseInt(Character.toString(addend2.charAt(i2)));

	        int digit = digit1 + digit2 + carry;
	        if (digit > 9) {
	            carry = 1;
	            digit -= 10;
	        } else {
	            carry = 0;
	        }

	        buf.append(digit);
	    }
	    return buf.reverse().toString();
	}
	
	public static String multiply(String a,char c) {
		int carry = 0;
		int i = a.length()-1;
		int m = Integer.parseInt(c + "");
		StringBuffer sb = new StringBuffer();
		
		while(i>=0 || carry!=0) {
			int digit = Integer.parseInt(a.charAt(i) + "");
			
			int result = (digit == 0)? carry : (digit*m + carry);
			carry = (int) Math.floor(result/10);
			digit = result % 10;
			sb.append(digit);
			i--;
		}
		
		return sb.reverse().toString();
	}
	
	public static String multiply(String a,String b) {
		List<String> partialResults = new ArrayList<String>();
		int i = 0;
		int i2 = b.length()-1;
		
		while(i2>=0) {
			String s = multiply(a, b.charAt(i2));
			int temp = i;
			while(temp>0) {
				s = s + "0";
				temp--;
			}
			i++;
			i2--;
			partialResults.add(s);
		}
		
		String result = "0";
		Iterator<String> it = partialResults.iterator();
		
		while(it.hasNext()) {
			result = add(result,it.next());
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(add("1234","4321"));
		System.out.println(multiply("1234", "24"));
	}
}

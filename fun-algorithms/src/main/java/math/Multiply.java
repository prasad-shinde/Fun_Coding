package math;

import java.math.BigInteger;

public class Multiply {
	
	public static String multiply(String s1,String s2) {
		BigInteger first = new BigInteger(s1);
		BigInteger second = new BigInteger(s2);
		
		return first.multiply(second).toString(10);
	}
	
	public static void main(String[] args) {
		System.out.println(roman(100));
	}
	
	public static String roman(int num) {
	    StringBuilder result = new StringBuilder();   
	    final String[] ROMAN_LETTERS = new String[] { "I", "IV", "V", "IX", "X", "XL", "L",
	            "XC", "C", "CD", "D", "CM", "M" };
	    final int[] BREAK_POINTS = new int[] { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500,
	            900, 1000 };
	    int count = 0;
	    
	    for (int i = BREAK_POINTS.length - 1; i >= 0; i--) {
	        count = num / BREAK_POINTS[i];
	        num %= BREAK_POINTS[i];
	        while (count > 0) {
	            result.append(ROMAN_LETTERS[i]);
	            count--;
	        }
	    }
	    return result.toString();
	}
}

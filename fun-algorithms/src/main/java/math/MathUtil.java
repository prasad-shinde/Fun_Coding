package math;

import java.util.List;

public class MathUtil {
	/**
	 * @param a
	 * @param b
	 * @return
	 * 
	 * optimized GCD algorithm
	 */
	public static int gcd(int a,int b) {
		if(a==b)
			return a;
		else {
			if(a>b)
				return gcd(a-b,b);
			else
				return gcd(a,b-a);
		}
	}
	
	public static long gcd1(long a,long b) {
		a = Math.abs(a);
		b = Math.abs(b);
		
		return (b==0)?a:gcd1(b,a%b);
	}
	
	public static long gcd2(long a,long b) {
		while(b!=0) {
			long t = b;
			b = a % b;
			a = t;
		}
		return a;
	}
	
	public static long lcm(long a,long b) {
		return Math.abs(a)*Math.abs(b)/gcd1(a,b);
	}
	
	public static long lcm(List<Integer> list) {
		long[] arr = new long[list.size()];
		int j=0;
		
		for(int i:list) {
			arr[j++] = i;
		}
		
		return lcm(arr);
	}
	
	public static long lcm(long[] list) {
		if(list.length == 1)
			return list[0];
		
		long a,b,result;
		a = list[0];
		b = list[1];
		result = lcm(a,b);
		
		if(list.length == 2)
			return result;
		
		for(int i=2;i<list.length;i++) {
			result = lcm(result,list[i]);
		}
		return result;
	}
	
	public static void main(String[] args) {
		//System.out.println(gcd1(1325867522,234534622));
		System.out.println(lcm(6,12));
	}
}

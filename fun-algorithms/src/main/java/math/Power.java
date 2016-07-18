package math;

public class Power {
	
	/**
	 * @param x
	 * @param n
	 * @return
	 * 
	 * time complexity = O(n)
	 */
	public static int naivePower(int x,int n) {
		int result = 1;
		while(n>0) {
			result *= x;
			n--;
		}
		return result;
	}
	
	/**
	 * @param x
	 * @param n
	 * @return
	 * 
	 * The indian power function has a time complexity of O(lgn)
	 */
	public static int indianPower(int x,int n) {
		if(n == 0)
			return 1;

		int result = x;
		while(n > 1) {
			if((n % 2) == 1) { // n is odd
				result = result * result;
				result = result * x;
				n = (n-1)/2;
			} else { // n is even
				n = n/2;
				result = result * result;
			}
		}
		return result;
	}
}

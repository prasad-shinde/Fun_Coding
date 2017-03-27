package array;

/**
 * Fibonacci series : 
 * Fibonacci series is generated using the following formula : 
 * F(n) = F(n-1) + F(n-2)
 * F(0) = 0
 * F(1) = 1
 * 
 * 
 * series : 0,1,1,2,3,5,8,13,....
 * 
 * @author prasshinde
 *
 */
public class Fibonacci {
	
	public static void printFibo(int n) {
		if(n < 1) {
			System.out.print("0");
			return;
		}
		
		if(n <2) {
			System.out.print("0 1");
			return;
		}
		int i = 0,j=1;
		n -= 2;
		System.out.print("0 1");
		while(n>=0) {
			int k = i+j;
			System.out.print(" " + k);
			i = j;
			j = k;
			n--;
		}
	}
	
	public static int nthFiboNonRec(int n) {
		if(n < 1) {
			return 0;
		}
		
		if(n <2) {
			return 1;
		}

		int i = 0,j=1;
		int k = 1;
		n -= 2;
		while(n>=0) {
			k = i+j;
			i = j;
			j = k;
			n--;
		}
		return k;
	}
	
	/**
	 * This function has a time complexity which is exponential in n as it recomputes many answers which it has already computed
	 * 
	 * @param n
	 * @return
	 */
	public static int nthFiboRec(int n) {
		if(n < 1)
			return 0;
		if(n < 2)
			return 1;
		return nthFiboRec(n-1) + nthFiboRec(n-2);
	}
	
	
	public static void main(String[] args) {
		printFibo(10);
		System.out.println("\n"+nthFiboNonRec(2));
		System.out.println(nthFiboRec(2));
	}
}

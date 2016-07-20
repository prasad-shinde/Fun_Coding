package array;

/**
 * Catalan Numbers :
 * 
 * C(0) = 1
 * C(n+1) = C(i) * C(n-i) for 0 <= n
 * 
 * sub n = n-1, we get,
 * C(n-1+1) = C(i) * C(n-1-i) for 0 <= n-1
 * C(n) = C(i) * C(n-i-1) for n >= 1
 * 
 * @author prasshinde
 *
 */
public class CatalanNumber {

	// catalan(n) is sum of catalan(i)*catalan(n-i-1)
	public static long catalanNumber(int n) {
		if(n <= 1) 
			return 1;
		long result = 0;
		for(int i = 0;i<n;i++)
			result += catalanNumber(i) * catalanNumber(n-i-1);
		return result;
	}
}

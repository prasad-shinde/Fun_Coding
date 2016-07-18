package numbers;

public class FactorialZeros {
	/** Link : http://www.purplemath.com/modules/factzero.htm
	 * @param num
	 * @return
	 */
	public static int numOfZeros(int num) {
		int divisor = 5;
		int zeros = 0; 
		while(Math.floor(num/divisor)>0) {
			zeros += (int) Math.floor(num/divisor);
			divisor *= 5;
		}
		
		return zeros;
	}
}

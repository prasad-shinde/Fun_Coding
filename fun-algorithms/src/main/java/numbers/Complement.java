package numbers;

public class Complement {
	public static int complement(int n) {
		int result = 0;
		int power = 0;
		
		while(n>0) {
			int lsb = n & 1;
			if(lsb == 1) {
				result = (int) (result + Math.pow(2, power)*0);
			} else {
				result = (int) (result + Math.pow(2, power)*1);
			}
			power++;
			n = n>>1;
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(complement(5));
		System.out.println(complement(10));
		System.out.println(complement(7));
	}
}

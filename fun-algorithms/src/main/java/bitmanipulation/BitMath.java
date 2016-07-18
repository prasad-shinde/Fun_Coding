package bitmanipulation;

public class BitMath {
	public static int add(int x,int y) {
		if(y==0)
			return x;
		else
			return add(x^y,((x&y)<<1));
	}
	
	public static int subtract(int x,int y) {
		if(y==0)
			return x;
		else
			return subtract(x^y,((~x)&y)<<1);
	}
	
	public static boolean isOdd(int x) {
		return (x&1)==1;
	}
	
	public static boolean isEven(int x) {
		return !isOdd(x);
	}
	
	public static int square(int x) {
		if(x == 0)
			return 0;
		if(x == 1)
			return 1;
		if(isEven(x))
			return 4*square(x/2);
		else
			return (int) (4*square((int) Math.floor((double)x/2)) + (int)4*Math.floor(x/2) + 1);
	}
}

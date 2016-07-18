package math;

public class Divide {
	public static int divide(int a,int b) {
		if(a<b)
			return 0;
		else if(a > b*b) {
			return b + divide(a-b*b,b);
		}
		else if(a > 2*b)
			return 2 + divide(a-2*b,b);
		else
			return 1 + divide(a-b,b);
	}

	public static void main(String[] args) {
		System.out.print(divide(500,2));
	}
}

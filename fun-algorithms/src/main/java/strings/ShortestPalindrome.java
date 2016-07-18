package strings;

public class ShortestPalindrome {
	public static int shortPalin(String s) {
		return shortPalin(s,0,s.length()-1);
	}
	
	public static int shortPalin(String s,int i,int j) {
		if(i==j)
			return 0;
		if(i+1 == j) {
			if(s.charAt(i+1) == s.charAt(j))
				return 0;
			else
				return 1;
		}
		
		if(s.charAt(i) == s.charAt(j))
			return shortPalin(s, i+1, j-1);
		else
			return 1 + Math.min(shortPalin(s,i+1,j), shortPalin(s,i,j-1));
	}
	
	public static void main(String[] args) {
		System.out.println(shortPalin("abab"));
		System.out.println(shortPalin("ababa"));
		System.out.println(shortPalin("abcd"));
		System.out.println(shortPalin("ababade"));
		System.out.println(shortPalin("pqrabazrw"));
	}
}

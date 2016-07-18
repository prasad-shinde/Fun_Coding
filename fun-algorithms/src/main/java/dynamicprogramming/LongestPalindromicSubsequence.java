package dynamicprogramming;

public class LongestPalindromicSubsequence {
	
	//private static int[][] memo;
	/**
	 * LPS = 1 if i = j = 1								a
	 * 		 1 if j = i+1 and x[i+1] != x[j]			ab
	 * 		 2 if j = i+1 and x[i] == x[j]				aa
	 * 		 LPS(i+1,j-1) + 2 if x[i] == x[j]			abbbba
	 * 		 Max(LPS(i+1,j),LPS(i,j-1)) if x[i] != x[j]
	 * @param str
	 * @return
	 */
	public static String LPS(String str) {
		//memo = new int[str.length()][str.length()];
		String palin = LPSHelper(str,0,str.length()-1);
		//return palin + StringReverse.reverse(palin);
		return palin;
	}
	
	private static String LPSHelper(String str,int i,int j) {
		if(i == j) {
			return str.charAt(i) + "";
		}
		else if(j==(i+1) && (str.charAt(i) != str.charAt(j))) {
			return str.charAt(j) + "";
		}
		else if(j==(i+1) && (str.charAt(i) == str.charAt(j))) {
			return str.charAt(j) + str.charAt(j) + "";
		}
		else if(str.charAt(i) == str.charAt(j)) {
			return str.charAt(i) + LPSHelper(str,i+1,j-1) + str.charAt(i);
		}
		else {	// memoize everything	
			String left = LPSHelper(str,i+1,j);
			String right = LPSHelper(str,i,j-1);
			if(left.length() > right.length())
				return left;
			else
				return right;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("LPS : " + LPS("GEEKS FOR GEEKS"));
	}
}

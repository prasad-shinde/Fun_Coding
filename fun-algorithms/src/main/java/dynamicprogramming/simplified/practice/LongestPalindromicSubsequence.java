package dynamicprogramming.simplified.practice;

/**
 * LPS(i,j) = 1 								if i == j
 * 			  2 			 					if x[i] == x[j] && i+1 == j
 * 			  2 + LPS(i+1,j-1)					if x[i] == x[j]
 * 			  Max{LPS(i+1,j),LPS(i,j-1)} 		if x[i] != x[j]
 * 
 * @author prasshinde
 *
 */
public class LongestPalindromicSubsequence {
	public static int longestPalindromicSubsequence(String x,int[][] dp,int i,int j) {
		if(i>j)
			return 0;
		if(dp[i][j] != -1)
			return dp[i][j];
		
		if(x.charAt(i) == x.charAt(j)) {
			if(i == j) {
				dp[i][j] = 1;
				return 1;
			} else if (i +1 == j) {
				dp[i][j] = 2;
				return 2;
			} else {
				dp[i][j] = 2 + longestPalindromicSubsequence(x, dp, i+1, j-1);
				return dp[i][j];
			}
		} else {
			dp[i][j] = Math.max(longestPalindromicSubsequence(x, dp, i, j-1), longestPalindromicSubsequence(x, dp, i+1, j));
			return dp[i][j];
		}
	}
	
	public static void main(String[] args) {
		String str = "rotapqrsttor";
		int[][] dp = new int[str.length()][str.length()];
		for(int i=0;i<str.length();i++) {
			for(int j=0;j<str.length();j++) {
				dp[i][j] = -1;
			}
		}
		
		System.out.println(longestPalindromicSubsequence(str, dp, 0, str.length()-1));
	}
}

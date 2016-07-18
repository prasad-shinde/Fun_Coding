package dynamicprogramming.simplified;

/**
 * Longest Palindromic Subsequence :
 * Given a sequence find the longest palindromic subsequence, check out the examples given in main
 * 
 * Recurrence :
 * let a be the given sequence array.
 * LPS(i,j) = Longest palindromic subsequence which starts at i and ends at j(hence i <= j)
 * 
 * LPS(i,j) = a[i] + LPS(i+1,j-1) + a[i]		if(a[i] == a[j]) & i<j
 * 			  MaxLen{LPS(i,j-1),LPS(i+1,j)}		if(a[i] != a[j]) & i<j
 * 			  a[i] + a[i]						if(i+1 == j && a[i] == a[j])
 * 			  a[i]								if(i == j || ((i+1) == j && a[i]!= a[j]))
 * 
 * Running Time : O(n^2) using DP
 * 
 * @author Prasad
 *
 */
public class LongestPalindromicSequence {

	public static int longestPalindromicSubsequence(String s) {
		int[][] dp = new int[s.length()][s.length()];
		int i,j;
		
		for(i=0;i<s.length();i++) {
			for(j=0;j<s.length();j++) {
				if(i==j) {
					dp[i][j] = 1;
					continue;
				}
				if(i>j)
					dp[i][j] = 0;
			}
		}
		
		for(i=s.length()-2;i>=0;i--) {
			for(j = i+1;j<s.length();j++) {
				if((i+1) == j)
					if(s.charAt(i) == s.charAt(j))
						dp[i][j] = 2;
					else
						dp[i][j] = 1;
				else
					if(s.charAt(i) == s.charAt(j))
						dp[i][j] = 2 + dp[i+1][j-1];
					else
						dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
			}
		}
		
		return dp[0][s.length()-1];
	}
	
	public static void main(String[] args) {
		System.out.println(longestPalindromicSubsequence("GEEKS FOR GEEKS"));
		System.out.println(longestPalindromicSubsequence("BBABCBCAB"));
		System.out.println(longestPalindromicSubsequence("aafa"));
	}
}

package strings;

/**
 *  '?' Matches any single character.
	'*' Matches any sequence of characters (including the empty sequence).
	
	The matching should cover the entire input string (not partial).
	
	The function prototype should be:
	bool isMatch(const char *s, const char *p)
	
	Some examples:
	isMatch("aa","a") → false
	isMatch("aa","aa") → true
	isMatch("aaa","aa") → false
	isMatch("aa", "*") → true
	isMatch("aa", "a*") → true
	isMatch("ab", "?*") → true
	isMatch("aab", "c*a*b") → false
 * 
 * References : 
 * 1. Leetcode : https://leetcode.com/problems/wildcard-matching/
 * 2. Discuss : https://discuss.leetcode.com/topic/22516/my-java-dp-solution-using-2d-table/6
 * 
 * @author prasshinde
 *
 */
public class WildcardMatching {
	
	public static boolean isMatchLinear(String str,String pattern) {
		int s = 0,p = 0,match = 0,starIdx = -1;
		
		while(s<str.length()) {
			if(p < pattern.length() && (pattern.charAt(p) == '?' || pattern.charAt(p) == str.charAt(s))) {
				s++;
				p++;
			} else if(p < pattern.length() && pattern.charAt(p) == '*') {
				starIdx = p;
				match = s;
				p++;
			} else if(starIdx != -1) {
				p = starIdx+1;
				match++;
				s = match;
			} else
				return false;
		}
		
		while(p < pattern.length() && pattern.charAt(p) == '*')
			p++;
		return p == pattern.length();
	}

	public static boolean isMatch(String s,String p) {
		int m = s.length(),n = p.length();
		boolean[][] dp = new boolean[s.length()+1][p.length()+1];
		
		dp[0][0] = true;
		
		for(int i = 1;i<=m;i++) {
			dp[i][0] = false;
		}
		
		for(int i = 1;i<=n;i++) {
			if(p.charAt(i-1) == '*')
				dp[0][i] = true;
		}
		
		for(int i = 1;i<=m;i++) {
			for(int j = 1;j<=n;j++) {
				if(p.charAt(j-1) != '*') {
					dp[i][j] = dp[i-1][j-1] && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?');
				} else {
					dp[i][j] = dp[i-1][j] || dp[i][j-1];
				}
			}
		}
		return dp[m][n];
	}
	
	public static void main(String[] args) {
		System.out.println(isMatchLinear("aab", "c*a*b"));
	}
}

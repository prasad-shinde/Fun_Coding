package strings.patternmatching;

public class PatternMatcher {
	
	public static boolean isMatching(String s,String p) {
		boolean[][]dp = new boolean[s.length()+1][p.length()+1];
		int[][] isSolved = new int[s.length()+1][p.length()+1];
		for(int i = 0;i<=s.length();i++) {
			for(int j=0;j<=p.length();j++) {
				dp[i][j] = false;
				isSolved[i][j] = -1;
			}
		}
		
		return isMatching(s,p,0,0,dp,isSolved);
	}
	
	/**
	 * @param s
	 * @param p
	 * @param si
	 * @param pi
	 * @param dp
	 * @param isSolved
	 * @return
	 * 
	 * PM(i,j) = {  false	 					if s[i] != p[j] and both s[i] and p[j] are alphabets
	 * 				PM(i+1,j+1)					if s[i] == p[j]
	 * 				PM(i+1,j) || PM(i+1,j+1)	if p[j] == '*'
	 * 				PM(i+1,j+1)					if p[j] == '?'
	 * 				true						if i == len(s) && j == len(p)
	 * 				false						otherwise(either i or j reaches the end while the other pointer does not)
	 * 			 }
	 */
	private static boolean isMatching(String s,String p,int si,int pi,boolean[][] dp,int[][] isSolved) {
		if(si == s.length() && pi == p.length())
			return true;
		if(si == s.length() || pi == p.length())
			return false;
		if(isSolved[si][pi] == 1)
			return dp[si][pi];
		if((Character.isAlphabetic(s.charAt(si)) && Character.isAlphabetic(p.charAt(pi))) && s.charAt(si) != p.charAt(pi))
			return false;
		if(p.charAt(pi) == '*') {
			dp[si][pi] = isMatching(s, p, si+1, pi, dp, isSolved) || isMatching(s, p, si+1, pi+1, dp, isSolved);
			isSolved[si][pi] = 1;
		}
		else {
			dp[si][pi] = isMatching(s, p, si+1, pi+1, dp, isSolved);
			isSolved[si][pi] = 1;
		}
		return dp[si][pi];		
	}
	
	public static void main(String[] args) {
		System.out.print("\n is Mataching : " + isMatching("ababc", "a*c"));
	}
}

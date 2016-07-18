package dynamicprogramming.simplified;

public class PartitionPalindrome {
	
	public static int minPalindromePartition(String str) {
		int[][] dp = new int[str.length()][str.length()];
		boolean[][] p = new boolean[str.length()][str.length()];
		
		int i,j,l;
		
		for(i=0;i<str.length();i++) {
			dp[i][i] = 0;
			p[i][i] = true;
		}
		
		for(l=2;l<=str.length();l++) {
			for(i=0;i<str.length()-l+1;i++) {
				j = i + l -1;
				
				if(l==2)
					p[i][j] = str.charAt(i) == str.charAt(j);
				else
					p[i][j] = str.charAt(i) == str.charAt(j) && p[i+1][j-1];
				
				if(p[i][j] == true)
					dp[i][j] = 0;
				else {
					dp[i][j] = Integer.MAX_VALUE;
					for(int k=i;k<j;k++) {
						dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + 1);
					}
				}
			}
		}
		
		return dp[0][str.length()-1];
	}
	
	public static void main(String[] args) {
		String str = "ababbbabbababa";
		System.out.println("Min cuts needed for Palindrome Partitioning is " + minPalindromePartition(str));
	}
}

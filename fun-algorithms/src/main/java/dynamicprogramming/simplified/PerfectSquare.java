package dynamicprogramming.simplified;

/**
 * Perfect Square :
 * 
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 * 
 * Reference : https://leetcode.com/problems/perfect-squares/
 * 
 * Similar Problem : {@link MinCoins}
 * 
 * @author prasshinde
 *
 */
public class PerfectSquare {
	public static int numSquares(int n) {
        int size = (int)Math.floor(Math.sqrt((double)n));
        int[] dp = new int[n+1];
        
        dp[0] = 0;
        
        for(int s=1;s<=n;s++) {
        	dp[s] = Integer.MAX_VALUE;
            for(int i=1;i<=size;i++) {
                if(s>=(i*i)) {
                    dp[s] = Math.min(dp[s],dp[s-(i*i)] + 1);
                }
            }
        }
        
        return dp[n];
    }
	
	public static void main(String[] args) {
		System.out.println(numSquares(13));
	}
}

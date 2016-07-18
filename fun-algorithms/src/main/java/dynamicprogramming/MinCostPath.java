package dynamicprogramming;

public class MinCostPath {
	static int R=3,C=3;
	/**
	 * possible moves are up,down and diagonal down towards right
	 * minCost(m,n) = min(minCost(m-1,n-1),minCost(m-1,n),minCost(m,n-1)) + cost[m][n]
	 * 
	 *	Problem : http://www.geeksforgeeks.org/dynamic-programming-set-6-min-cost-path/
	 *  TC of DP solution is O(mn)
	 * @param cost
	 * @param destX
	 * @param destY
	 * @return
	 */
	public static int minCostPath(int[][] cost,int destX,int destY) {
		int[][] dp = new int[R][C];
		
		dp[0][0] = cost[0][0];
		int i,j;
		for(i=1;i<=destX;i++) {
			dp[i][0] = dp[i-1][0] + cost[i][0];
		}
		
		for(j=1;j<=destY;j++) {
			dp[0][j] = dp[0][j-1] + cost[0][j];
		}
		
		for(i=1;i<=destX;i++) {
			for(j=1;j<=destY;j++) {
				dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + cost[i][j];
			}
		}
		
		return dp[destX][destY];
	}
	
	public static void main(String[] args) {
		int cost[][] = { {1, 2, 3},
                {4, 8, 2},
                {1, 5, 3} };
		System.out.print(minCostPath(cost,2,2));
	}
}

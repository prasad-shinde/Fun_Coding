package dynamicprogramming;

public class CoinChange {
	public static int count(int[] coins,int numOfCoins,int sum) {
		int[][] dp = new int[sum + 1][numOfCoins];
		for(int i = 0;i<numOfCoins;i++) {
			dp[0][i] = 1;
		}
		
		for(int i = 1;i<sum+1;i++) {
			for(int j = 0;j<numOfCoins;j++) {
				// jth coin is included
				int x = (i-coins[j]>=0)?dp[i-coins[j]][j]:0; 
				// jth coin is not included
				int y = (j>=1)?dp[i][j-1]:0;
				
				dp[i][j] = x + y;
			}
		}
		
		return dp[sum][numOfCoins-1];
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3};
		System.out.print(count(arr,3,4));
	}
}

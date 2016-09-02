package dynamicprogramming.simplified;

public class MinCoins {
	
	/**
	 * c(i,j) = a sub problem where we solve the min number of coins needed from 0 to j to satisfy a sum of i
	 * 
	 * c(0,j) = 0
	 * c(i,0) = 0
	 * 
	 * c(i,j) = Min{1 + c(i-coins[j],j),c(i,j-1)}
	 * 
	 * @param coins
	 * @return
	 */
	public static int minCoins(int[] coins,int sum) {
		int[] c = new int[sum+1];
		
		c[0] = 0;
		
		for(int i = 1;i<sum+1;i++) {
			c[i]  = Integer.MAX_VALUE;
			for(int j=1;j<coins.length+1;j++) {
				if(i >= coins[j-1])
					c[i] = Math.min(1 + c[i-coins[j-1]], c[i]);
			}
		}
		
		return c[sum];
	}
	
	public static void main(String[] args) {
		int[] coins = {1,2,3,4};
		int sum = 10;
		System.out.println(minCoins(coins, sum));
	}
}

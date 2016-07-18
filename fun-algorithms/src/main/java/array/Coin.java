package array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Prasad
 * for a given amount and given denominations, print all the possible denominations.. REPEATATIONS ALLOWED
 * 
 * amount = 5, denominations = 1,2,3. 
	Ans- 5 ways 
	1) 1,1,1,1,1 
	2) 1,1,1,2 (combinations aren't counted eg 1,2,1,1 etc) 
	3) 1,1,3 
	4) 1,2,2 
	5) 2,3
 */
public class Coin {

	/************************************ Naive algorithm ************************************************/
	
	/**
	 * @param coins
	 * @param amount
	 * 
	 * This is brute force essentially!! very bad!! very very!!
	 * use dynamic programming :)
	 */
	public static void combinations(int[] coins,int amount) {
		List<Integer> pocket = new ArrayList<Integer>();
		combinationsHelper(coins, 0, amount, pocket);
	}
	
	private static void combinationsHelper(int[] coins,int i,int amountLeft,List<Integer> pocket) {
		if(amountLeft < 0)
			return;
		if(amountLeft == 0) {
			print(pocket);
			return;
		}
		else {
			while(i<coins.length) {
				pocket.add(coins[i]);
				combinationsHelper(coins, i, amountLeft - coins[i], pocket);
				pocket.remove(pocket.lastIndexOf(coins[i]));
				i++;
			}
		}
	}
	
	private static void print(List<Integer> pocket) {
		Iterator<Integer> it = pocket.iterator();
		System.out.print("\n");
		while(it.hasNext()) {
			System.out.print(" " + it.next());
		}
	}
	
	
	/********************************** Dynamic Programming ******************************************/
	
	public static int coinDenominations(int[] coins,int amount) {
		int[][] dp = new int[amount+1][coins.length + 1];
		for(int i = 0;i<=coins.length;i++) {
			dp[0][i] = 1;
		}
		
		for(int i= 1;i<=amount;i++) {
			dp[i][0] = 0;
		}
		
		for(int i=1;i<=amount;i++) {
			for(int j=1;j<=coins.length;j++) {
				//either jth coin is included or not
				if(dp[i][j-1] == 1)				// jth coin is not included 
					dp[i][j] = 1;
				else							// jth coin is included
					dp[i][j] = dp[i-coins[j-1]][j-1]; 
			}
		}
		
		for(int i=0;i<=amount;i++) {
			System.out.print("\n");
			for(int j=0;j<=coins.length;j++) {
				System.out.print(dp[i][j]+ " ");
			}
		}
		
		return dp[amount][coins.length];
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5};
		combinations(arr, 5);
		System.out.print("\n Has coins denominations : " + coinDenominations(arr, 5));
	}
}

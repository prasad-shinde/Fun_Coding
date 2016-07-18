package dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/** Knapsack Problem implemented as dynamic programming recursive and non-recursive function
 * @author Prasad
 *
 */
public class Knapsack {
	private int[] size;
	private int[] value;
	private int knapsackSize;
	private int[][] memo;

	public Knapsack(int[] s,int[] v,int max) {
		size = s;
		value = v;
		knapsackSize = max;
	}
	
	public void fill() {
		List<Integer> picked = new ArrayList<Integer>();
		memo = new int[knapsackSize+1][value.length+1];
		for(int i = 0;i<=knapsackSize;i++)
			for(int j = 0;j<=value.length;j++)
				memo[i][j] = 0;
		
		int max = fill(picked,knapsackSize,0);
		System.out.print("\n Max : " + max + " Values : ");
		for(int v:picked) {
			System.out.print(v + " ");
		}
		/*
		for(int i = 1;i<knapsackSize;i++) {
			System.out.println();
			for(int j = 0;j<=size.length;j++) {
				System.out.print(memo[i][j] + " ");
			}
		}*/
	}
	
	private int fill(List<Integer> v,int currentSize,int itemIndex) {
		if(currentSize == 0 || itemIndex > (size.length - 1))
			return sum(v);
		if(memo[currentSize][itemIndex]!=0) {
		//	return memo[currentSize][itemIndex];
		}
		
		int Si,Vi;
		Si = size[itemIndex];
		Vi = value[itemIndex];
		List<Integer> copy1 = new ArrayList<Integer>(),copy2 = new ArrayList<Integer>();
		
		copy1.addAll(v);
		copy2.addAll(v);
		int sum1 = fill(copy1,currentSize,itemIndex+1);
		int sum2 = sum(v);
		
		if((currentSize - Si) >= 0) {
			copy2.add(Vi);
			sum2 = fill(copy2,currentSize - Si,itemIndex+1);
		}
		if(sum1>sum2) {
			v.clear();
			v.addAll(copy1);
			memo[currentSize][itemIndex] = sum1;
			return sum1;
		}
		else {
			v.clear();
			v.addAll(copy2);
			memo[currentSize][itemIndex] = sum2;
			return sum2;
		}
	}
	
	/**
	 * Non - recursive algorithm which uses a 2-D array to store all the results
	 * 
	 * Either include the element i or do not include the element
	 * 1. i not included
	 * 		carry forward the previous sum and dont decrease the knapsack size
	 * 
	 * 2. i is included
	 * 		decrese the size of knapsack by 1 and increase the value by the value of ith element
	 * 
	 * dp[i][j] = Max(dp[i-1][j],dp[i-1][j-s[i]] + v[i]) 
	 * 
	 */
	public void knapsackFill() {
		int[][] dp = new int[size.length+1][knapsackSize+1];
		
		for(int j = 0;j<=knapsackSize;j++) {
			dp[0][j] = 0;
		}
		
		for(int i = 1;i<size.length;i++) {
			for(int j = 0;j<=knapsackSize;j++) {
				if(size[i] <= j) {		// checks if the size remaining is atleast size[i] -> else we cannot include the item
					dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-size[i]] + value[i]);
				}
				else
					dp[i][j] = dp[i-1][j];
			}
		}
		
		
		for(int i =0 ;i<size.length;i++) {
			System.out.println();
			for(int j = 0;j<=knapsackSize;j++) {
				System.out.print(dp[i][j] + " ");
			}
		}
		
		System.out.println("Max : " + dp[size.length-1][knapsackSize]);
	}
	
	private int sum(List<Integer> list) {
		int sum = 0;
		for(int i:list) {
			sum += i;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		int[] size = {3,6,3,7,4,2};
		int[] value = {12,6,34,6,41,2};
		//int val[] = {60, 100, 120};
	    //int wt[] = {10, 20, 30};
	    //int  W = 50;
		Knapsack kp = new Knapsack(size,value,9);
		kp.fill();
		kp.knapsackFill();
	}
}

package dynamicprogramming.simplified.practice;

public class Partition {
	public static boolean hasPartition(int[] arr) {
		int sum = 0;
		for(int num:arr) {
			sum+=num;
		}
		if(sum % 2 == 1)
			return false;
		sum = sum/2;
		boolean dp[][] = new boolean[sum+1][arr.length+1];
		
		for(int i=0;i<arr.length;i++) {
			dp[0][i] = true; 
		}
		
		for(int s=1;s<sum;s++) {
			dp[s][0] = false; 
		}
		
		for(int s = 1;s<=sum;s++) {
			System.out.println();
			for(int i=1;i<=arr.length;i++) {
				if(s >= arr[i-1])
					dp[s][i] = dp[s-arr[i-1]][i-1];
				else
					dp[s][i] = dp[s][i-1];
				System.out.print(dp[s][i] + " ");
			}
		}
		return dp[sum][arr.length-1];
	}
	
	public static void main(String[] args) {
		int[] arr = {3, 1, 1, 2, 2,1};
		System.out.println("\n" + hasPartition(arr));
	}
	
}

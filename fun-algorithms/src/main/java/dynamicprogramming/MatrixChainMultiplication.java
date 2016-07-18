package dynamicprogramming;

public class MatrixChainMultiplication {
	/**
	 * matMult(order,i,j) = min(matMult(order,i,k) + matMult(order,k+1,j) + order[i-1]*order[k]*order[j]  for i = 0 and j = n-1 and k = i to j
	 * @param order
	 * @param i
	 * @param j
	 * @return
	 */
	public static int matrixChainOrder(int[] order,int i,int j) {
		if(i==j)
			return 0;
		int min = Integer.MAX_VALUE;
		
		for(int k=i;k<j;k++) {
			int count = matrixChainOrder(order, i, k) + matrixChainOrder(order, k+1, j) + order[i-1] * order[k] * order[j];
			if(count < min)
				min = count;
		}
		
		return min;
	}
	
	
	public static int matrixChainOrder(int[] order,int n) {
		int[][] dp = new int[n+1][n+1];
		int i,j,k,l;
		for(i=1;i<=n;i++) {
			dp[i][i] = 0;
		}
		
		
		/*for(j = 1;j<n;j++) {
			System.out.print("\n");
			for(i=1;i<j;i++) {
				dp[i][j] = Integer.MAX_VALUE;
				for(k = i;k < j;k++) {
					int q = dp[i][k] + dp[k+1][j] + order[i-1]*order[k]*order[j];
					if(q<dp[i][j])
						dp[i][j] = q;
				}
				System.out.print(dp[i][j] + " ");
			}
		}*/
		
		for(l=2;l<=n;l++) {
			System.out.print("\n");
			for(i=1;i<n-l+1;i++) {
				j = i+l-1;
				dp[i][j] = Integer.MAX_VALUE;
				for(k=i;k<=j-1;k++) {
					int q = dp[i][k] + dp[k+1][j] + order[i-1]*order[k]*order[j];
					if(q<dp[i][j])
						dp[i][j] = q;
				}
				System.out.print(dp[i][j] + " ");
			}
		}
		
		return dp[1][n-1];
	}
	
	public static void main(String[] args) {
		int arr[] = {50, 20, 1, 10, 100};
	    int n = 5;
	 
	    System.out.println("Minimum number of multiplications is  " + matrixChainOrder(arr, 1, n-1));
	    System.out.println("Minimum number of multiplications is  " + matrixChainOrder(arr, n));
	}
}

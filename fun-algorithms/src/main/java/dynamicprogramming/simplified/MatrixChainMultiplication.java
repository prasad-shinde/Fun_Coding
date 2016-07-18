package dynamicprogramming.simplified;

/**
 * @author Prasad
 * Chain Matrix Multiplication : 
 * Matrix product is not commutative but is associative. When we have to multiply n matrices
 * The way in which they are multiplied can change the run time of the algorithm significantly.
 * This problem can be solved by using Dynamic Programming in polynomial time.
 * 
 * Subproblem : it could be the cost associated with multiplying matrices from i to j
 * 
 * Recurrence : C(i,j) = min{C(i,k) + C(k+1,j) + m[i-1]*m[k]*m[j]} for i<=k<j
 * 
 * Base case : C(i,i) = 0
 * 
 * Final Answer : C(1,n)
 */
public class MatrixChainMultiplication {
	
	public static int matrixChainMultiplication(int[] m,int n) {
		int[][] c = new int[n+1][n+1];
		
		// base case
		for(int i = 0;i<=n;i++) {
			c[i][i] = 0;
		}
		
		for(int s = 1;s<=n-1;s++) {		// s stands for the size of sub problems
			for(int i = 1;i<n-s;i++) {
				int j = i + s;			// the range of the current sub problem
				c[i][j] = Integer.MAX_VALUE;
				for(int k = i;k<j;k++) {
					c[i][j] = Math.min(c[i][k] + c[k+1][j] + m[i-1]*m[k]*m[j], c[i][j]);
				}
			}
		}
		
		return c[1][n-1];
	}
	
	public static void main(String[] args) {
		int arr[] = {50, 20, 1, 10, 100};
	    int n = 5;
	 
	    System.out.println("Minimum number of multiplications is  " + matrixChainMultiplication(arr, n));
	}
}

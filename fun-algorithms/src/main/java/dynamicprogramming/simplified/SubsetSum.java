package dynamicprogramming.simplified;


/**
 * Subset Sum : 
 * The subset problem is one of the important problem in computer science. The problem is a know NP-Complete problem. Thankfull
 * there is a pseudo polynomial solution available to this problem given below ;)
 * 
 * Problem : given a set of values(an array of values), does there exist a subset of these values which sums up to a given value.
 * 
 * Similar Problem : The subset problem is though of a special case of the knapsack problem @see{@link Knapsack}}
 * 
 * Solution Dynamic Programming : 
 * Recurrence :
 * ss(i,j) = be a subproblem whose value is true if there exists a subset from 0..j which sums upto i  
 * 
 * Base Case :
 * ss(0,j) = true
 * ss(i,0) = false		for i>0
 * 
 * ss(i,j) = ss(i-v[j],j-1) || ss(i,j-1)	if(i >= v[j]) // either the element j is in subset or not.
 * ss(i,j) = ss(i,j-1)						if(i < v[j])
 * 
 * Result : 
 * s(sum,N)									where sum is desired sum and N is the total number of values
 * 
 * Note : This works only if the element in the array are non-negative. There is a infamous version of the problem
 * where we find if the subset sums to zero. In my opinion this problem can be reduced to the above problem, by
 * finding the minimum element in the values array and adding that value(the absolute) to every element in the array
 * so that now the array does not contain any negative element. Now the problem reduces to finding a subset which 
 * sums to the absolute of the minimum negative value present in the old array.
 * 
 * Resources : Subset sum : http://en.wikipedia.org/wiki/Subset_sum_problem
 * 
 * @author Prasad
 *
 */
public class SubsetSum {
	
	public static boolean isSubsetSumming(int[] values,int sum) {
		int n = values.length;
		boolean[][] dp = new boolean[sum+1][n+1];
		int i,j;
		
		// we can achieve subset summing 0 by picking no elements at all
		for(j=0;j<=n;j++)
			dp[0][j] = true;
		
		// cannot achieve any sum using 0 elements
		for(i=1;i<=sum;i++)
			dp[i][0] = false;
		
		for(i=1;i<=sum;i++) {
			for(j=1;j<=n;j++) {
				if(i >= values[j-1]) {
					dp[i][j] = dp[i-values[j-1]][j-1];
				}
				dp[i][j] = dp[i][j] || dp[i][j-1];
			}
		}
		
		return dp[sum][n];
	}
	
	public static void main(String[] args) {
		int[] values = {3, 34, 4, 12, 5, 2};
		int sum = 51;
		System.out.println(isSubsetSumming(values, sum));
	}
}

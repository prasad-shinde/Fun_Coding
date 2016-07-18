package dynamicprogramming;

import java.util.HashMap;

/**
 * @author Prasad
 * Descrition : http://en.wikipedia.org/wiki/Subset_sum_problem
 */
public class SubsetSum {

	/**
	 * @param list
	 * @param s the sum to be statisfied by the subset
	 * @return true if there is subset which sums to s else returns false
	 * 
	 * Q(i,s) = (x[i] == s) if i==0
	 * Q(i,s) = Q(i-1,s) or (x[i] == s) or Q(i-1,s-x[i]) for A<=s<=B
	 */
	public static boolean isSubsetSum(int[] list,int s) {
		boolean[][] Q = new boolean[s+1][list.length+1];
		boolean[] included = new boolean[list.length];
		
		for(int i=0;i<list.length;i++) {
			included[i] = false;
		}
		
		for(int i=0;i<=list.length;i++) {
			Q[0][i] = true;
		}
		
		for(int i = 1;i<=s;i++)
			Q[i][0] = false;

		for(int i = 1;i<=s;i++) {
			for(int j=1;j<=list.length;j++) {
				Q[i][j] = Q[i][j-1];
				if(i>=list[j-1]) {
					Q[i][j] = Q[i][j] || Q[i-list[j-1]][j-1];
					if(Q[i][j-1] == true)
						included[j-1] = false;
					else if(Q[i-list[j-1]][j-1] == true)
						included[j-1] = true;
					else {
						//included[j-1] = false;
					}
				}
			}
		}
		
		for(int i=0;i<list.length;i++) {
			if(included[i] == true)
				System.out.print(list[i] + " ");
		}
		
		return Q[s][list.length];
	}
	
	public static boolean hasSubsetSum(int[] list,int s) {
		boolean[][] Q = new boolean[s+1][list.length+1];
		int A = 0 ,B = 0;
		
		for(int i=0;i<=list.length;i++)
			Q[0][i] = true;
		for(int i = 1;i<=s;i++)
			Q[i][0] = false;
		
		for(int num:list) {
			if(num<0)
				A+=num;
			else
				B+=num;
		}
		
		//Q[0][s] = list[0] == s;	//base condition if the first element has the required sum
		
		for(int i = 1;i<list.length;i++) {
			if((s - list[i]) > A && (s - list[i]) < B)
				Q[i][s] = Q[i-1][s] || list[i] == s || Q[i-1][s-list[i]];
			else
				Q[i][s] = Q[i-1][s] || list[i] == s;
		}
		
		return Q[list.length-1][s];
	}
	
	public static void main(String[] args) {
		int[] list = {2,1,2,-1,2};
		System.out.print(isSubsetSum(list, 7));
	}
}

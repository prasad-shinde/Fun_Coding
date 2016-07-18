package dynamicprogramming.simplified;

/**
 * @author Prasad
 * Longest Increasing Sequence :
 * Finding the longest increasing subsequence for a given sequence.
 * 
 * Recurrance :
 * LIS(i) = 1 + max{LIS(j)} if(j<i && A[i] >= A[j])
 * prev(i) = jmax
 * LIS(0) = 1
 * prev(0) = -1
 * 
 * Similar Questions : Longest convex subsequence
 * 
 * Reference : http://www.cs.berkeley.edu/~vazirani/algorithms/chap6.pdf
 */
public class LongestIncreasingSequence {
	public static int lis(int[] list) {
		int[] lis = new int[list.length];
		int[] prev = new int[list.length];
		
		// base case
		lis[0] = 1;
		prev[0] = -1;
		
		for(int i=1;i<list.length;i++) {
			lis[i] = 1;
			prev[i] = -1;
			for(int j=0;j<i;j++) {
				if(list[i] > list[j] && (lis[i] <= lis[j])) {
					lis[i] = 1+lis[j];
					prev[i] = j;
				}
			}
		}
		
		print(list,prev);
		return lis[list.length-1];
	}
	
	public static void print(int[] list,int[] prev) {
		int i = list.length-3;
		while(prev[i]!=-1) {
			System.out.print(list[i] + " ");
			i = prev[i];
		}
		System.out.print(list[i] + " ");
	}
	
	public static void main(String[] args) {
		int[] list = {5, 2, 8, 6, 3, 6, 9, 7};
		lis(list);
	}
}

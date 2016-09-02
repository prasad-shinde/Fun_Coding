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
	/**
	 * Time Complexity = O(n^2)
	 * 
	 * @param list
	 * @return
	 */
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
	
	/**
	 * Time Complexity : O(nlgn) because of binary search but finding the elements in 
	 * longest increasing sequence needs some more work probably.
	 * 
	 * @param list
	 * @return
	 */
	public static int lis1(int[] list) {
		int[] tails = new int[list.length];
		int size = 0;

		for(int num:list) {
			int i = 0;
			int j = size;
			while(i!=j) {
				int mid = (i+j)/2;
				if(tails[mid] < num) {
					i = mid+1;
				} else {
					j = mid;
				}
			}
			
			tails[i] = num;
			
			if(i==size)
				size++;
		}
		return size;
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
		int[] list = {5, 2, 8, 6, 3, 6, 4, 1, 7};
		lis1(list);
	}
}

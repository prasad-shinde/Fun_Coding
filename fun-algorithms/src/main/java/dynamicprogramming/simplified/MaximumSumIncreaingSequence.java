package dynamicprogramming.simplified;

import java.util.Arrays;

/**
 * Maximum Sum Increasing Sequence : 
 * if input is {1, 101, 2, 3, 100, 4, 5}, then output should be 106 (1 + 2 + 3 + 100), 
 * if the input array is {3, 4, 5, 10}, then output should be 22 (3 + 4 + 5 + 10) 
 * if the input array is {10, 5, 4, 3}, then output should be 10
 * 
 * Dynamic Programming Solution :
 * 
 * Recurrence : 
 * MSIS(i) = Max sum increasing sequence ending at i and including the element at index i
 * 
 * MSIS(i) = Max{MSIS(j) + v[i]}
 * prev(i) = j => pertianing the the max one
 * 
 * Answer : Max from the MSIS array
 * 
 *  Similar problem : Longest increasing subsequence @see {@link LongestIncreasingSequence}
 * 
 * @author Prasad
 *
 */
public class MaximumSumIncreaingSequence {

	/** 
	 * TC = O(n^2)
	 * @param list
	 */
	public static void msis(int[] list) {
		int[] msis = new int[list.length];
		int[] prev = new int[list.length];
		Arrays.fill(msis, 0);
		Arrays.fill(prev, -1);
		
		for(int i = 0;i<list.length;i++) {
			msis[i] = list[i];
			for(int j=0;j<i;j++) {
				if(list[i] > list[j] && (msis[i] < msis[j] + list[i])) {
					msis[i] = msis[j] + list[i];
					prev[i] = j;
				}
			}
		}
		
		printSolution(list, msis, prev);
	}
	
	private static void printSolution(int[] arr,int[] msis,int[] prev) {
		int max = Integer.MIN_VALUE;
		int i=0,maxIndex = 0;
		for(i = 0;i<msis.length;i++) {
			if(max < msis[i]) {
				max = msis[i];
				maxIndex = i;
			}
		}
		
		while(prev[maxIndex] != -1) {
			System.out.print(arr[maxIndex] + " ");
			maxIndex = prev[maxIndex];
		}
		System.out.print(arr[maxIndex]);
	}
	
	public static void main(String[] args) {
		int[] arr = {1, 101, 2, 3, 100, 4, 5};
		msis(arr);
	}
}

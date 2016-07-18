package array;

import sorts.QuickSort;

/**
 * @author Prasad
 * 1. are the start and finish time of sequence overlapping?
 * 2. list i and j of array that have a sum k
 */
public class Overlapping {
	
	// assume starts and ends are sorted
	public static boolean isOverlapping(int []start,int end[]) {
		if(start.length < 2) 
			return true;
		for(int i = 0; i < start.length - 2 ; i++) {
			if(isOverlapping(start[i],end[i],start[i+1],end[i+1]))
				return true;
		}
		return false;
	}
	
	
	private static boolean isOverlapping(int start1,int end1,int start2, int end2) {
		if((start2 > start1 && start2 < end1) || (start2 < start1 && start1< end2))
			return true;
		if((end1 < end2 && end1 > start2) || (end2 < end1 && end2>start1))
			return true;
		return false;
	}
	
	// no extra space but O(nlgn) --> due to sorting
	// hash provides a O(n) solution which uses O(n) space
	/**
	 * print i and j that have a sum k
	 * @param list
	 * @param sum
	 */
	public static void haveSum(int[] list,int sum) {
		QuickSort.quickSort(list, list.length);
		int left = 0;
		int right = list.length-1;
		while(left<right) {
			if((list[left] + list[right]) == sum) {
				System.out.println(list[left] + " " + list[right]);
				left++;
				right--;
			}
			else if((list[left] + list[right]) > sum)
				right--;
			else
				left++;
		}
	}
	
	public static void main(String[] args) {
		int[] start = {5,10,15,20,25};
		int[] end = {14,13,16,21,26};
		System.out.println("is overlapping? " + isOverlapping(start, end));
		haveSum(start, 25);
	}
}

package array;

import heap.Heap;

/**
 * @author Prasad
 * K - sorted array : 
 * 1. Sort 1st k elements using quick sort
 * 2. A[0] is in its proper places(elements from 1 to k-1 can be 1 positions away from their final positions)
 * 3. Use the concepts of min heap from the elements 1 to k(k included)
 * 		3.1 Use sift up operation starting at kth index
 * 		3.2 bubble the kth element up till elements from 1 to k are in min heap order
 * 4. Repeat step 3 j = k to n-1
 * 5. Sort last k elements using quick sort again
 * 
 * Analysis : 
 * TC = O(klgk + (n-k)*lgk + klgk) = O(nlgk)
 * 
 * @see Heap for {@link Heap#buildMinHeap()} {@link Heap#minHeapify(int)}
 * 
 * sift_up operation provided below
 */
public class KSorted {
	private int[] list;
	
	private int parent(int i) {
		return i/2;
	}
	
	private void swap(int i,int j) {
		// swap them
	}
	
	public void siftUp(int i) {
		int parent = parent(i);
		if(parent < 0)
			return;
		if(list[parent] > list[i]) {
			swap(list[parent],list[i]);
			siftUp(parent);
		}
	}
}

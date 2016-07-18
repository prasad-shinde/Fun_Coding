package heap;

public class Heap {
	private int[] list;
	
	public Heap(int size) {
		list = new int[size];
	}
	
	public Heap(int[] arr) {
		int i = 0;
		list = new int[arr.length];
		for(int num:arr) {
			list[i++] = num;
		}
	}
	
	public int left(int i) {
		return 2*i+1;
	}
	
	public int right(int i) {
		return 2*i + 2;
	}
	
	public int parent(int i) {
		return (i-1)/2;
	}
	
	/** maxHeapify assumes the left and the right sub heaps satisfy the max-heap property
	 * but the parent(at index i) does not satisfy this property and hence needs maxHeapification.
	 * TC = O(lgn)
	 * @param i
	 */
	public void maxHeapify(int i) {
		int left = left(i);
		int right = right(i);
		int largest;
		
		if((left <= list.length -1) && (list[left] > list[i]))
			largest = left;
		else
			largest = i;
		
		if((right <= list.length -1) && (list[right] > list[largest]))
			largest = right;
		
		if(i!=largest) {
			// swap A[i] and A[largest]
			maxHeapify(largest);
		}
	}
	
	/**
	 * builds the max heap of the array
	 * TC = O(nlgn)
	 */
	public void buildMaxHeap() {
		for(int i = list.length/2;i >= 0;i--) {
			maxHeapify(i);
		}
	}
	
	/** same as max heapify function where the root does not satisfy the min heap property
	 *  use minHeapify to normalize the heap 
	 *  TC = O(lgn)
	 * @param i
	 */
	public void minHeapify(int i) {
		int left = left(i);
		int right = right(i);
		int smallest;
		
		if((left <= list.length -1) && (list[left] < list[i]))
			smallest = left;
		else
			smallest = i;
		
		if((right <= list.length -1) && (list[right] < list[smallest]))
			smallest = right;
		
		if(i!=smallest)
			minHeapify(smallest);
	}
	
	/**
	 * builds the min heap of the array.
	 * TC = O(nlgn)
	 */
	public void buildMinHeap() {
		for(int i = list.length/2; i>=0; i--)
			minHeapify(i);
	}
	
	public String toString() {
		StringBuffer s = new StringBuffer();
		for(int num:list) {
			s.append(num);
			s.append(' ');
		}
		return s.toString();
	}
	
	public void print() {
		System.out.println("Heap : " + toString());
	}
}

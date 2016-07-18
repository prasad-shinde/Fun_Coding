package sorts;

public class HeapSort {
	
	private static int left(int i) {
		return 2*i;
	}
	
	private static int right(int i) {
		return 2*i+1;
	}
	
	/** Max heapify assumes the left and right subtrees are max heaps but the root violates the property of max heap
	 * and we need to max heapify it
	 * @param arr
	 * @param i
	 */
	private static void maxHeapify(int []arr,int i) {
		int largest = i;
		int left = left(i);
		int right = right(i);
		if(left<=arr[0] && arr[i] < arr[left] ) {
			largest = left;
		}
		else
			largest = i;
		
		if(right<=arr[0] && arr[largest] < arr[right]) {
			largest = right;
		}
		
		if(largest != i) {
			swap(arr,largest,i);
			maxHeapify(arr,largest);
		}
	}
	
	/** converts a given array into max heap
	 * we use size/2 because individual leafs are always max heap themselves
	 * @param arr
	 * @param size
	 */
	public static void buildMaxHeap(int[] arr,int size) {
		for(int i = size/2; i>0;i--) {
			maxHeapify(arr,i);
		}
	}
	
	public static void heapSort(int[] arr) {
		buildMaxHeap(arr,arr[0]);
		print(arr);
		int size = arr[0];
		for(int i = size;i>=1;i--) {
			swap(arr,1,i);
			arr[0] = arr[0] - 1;
			maxHeapify(arr,1);
		}
		arr[0] = size;	// restoring the size back
	}
	
	private static void swap(int[] arr,int i,int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static void print(int[] arr) {
		System.out.print("\n");
		for(int num:arr) {
			System.out.print(num+" ");
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {7,7,6,5,4,3,2,1};
		heapSort(arr);
		System.out.print("\nSorted Array: ");
		print(arr);
	}
}

package sorts;

public class QuickSort {
	public static void quickSort(int[] arr,int size) {
		quickSortHelper(arr,0,size-1);
	}
	
	private static void quickSortHelper(int[] arr,int start,int end) {
		if(start<end) {
			int pivot = partition(arr,start,end);
			quickSortHelper(arr,start,pivot-1);
			quickSortHelper(arr,pivot+1,end);
		}
	}
	
	private static int partition(int[] arr,int left,int right)
	{
		int pivotIndex = right;	// choose a random pivot or median of three for a good partition
		int pivotValue = arr[pivotIndex];
		swap(arr,pivotIndex,right);
		int storeIndex = left;
		for(int i = left; i<right;i++) {
			if(arr[i] < pivotValue) {
				swap(arr,i,storeIndex);
				storeIndex++;
			}
		}
		swap(arr,storeIndex,right);
		return storeIndex;
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
		//int[] arr = {7,5,13,8,54,9,45};
		int[] arr = {0,1,2,0,1,2,2};
		QuickSort.quickSort(arr, 7);
		System.out.print("\nSorted Array: ");
		QuickSort.print(arr);
		
		System.out.print("\nnth smallest Array: " + quickSelect(arr,0,6,4));
	}
	
	
	
	
	/**
	 * @param arr
	 * @param left
	 * @param right
	 * @return
	 * 
	 * http://en.wikipedia.org/wiki/Quickselect#Algorithm
	 * partitions the array into two halfs one which is less then the pivot value and 
	 * other which is greater then the pivot value
	 * 
	 * Time Complexity : O(n)
	 */
	
	/** returns the nth largest element from the array in O(n) time
	 * @param list
	 * @param left
	 * @param right
	 * @param n
	 * @return
	 */
	public static int quickSelect(int[] list,int left,int right,int n) {
		if(left == right) 
			return list[left];
		while(true) {
			int pivotIndex = right;
			pivotIndex = partition(list,left,right);
			if(pivotIndex == n)
				return list[pivotIndex];
			else if(pivotIndex < n)
				left = pivotIndex + 1;
			else
				right = pivotIndex - 1;
		}
	}
}

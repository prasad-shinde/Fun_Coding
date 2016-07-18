package sorts;

public class MergeSort {
	public static void mergeSort(int[] arr,int size) {
		mergeSortHelper(arr,0,size);
	}
	
	private static void mergeSortHelper(int[] arr,int start,int end) {
		int[] temp = new int[arr.length];
		if(start<end) {
			int mid = (int) Math.floor((((float)start) + ((float)end))/2);
			mergeSortHelper(arr,start,mid);
			mergeSortHelper(arr,mid+1,end);
			merge(arr,temp,start,mid+1,end);
		}
	}
	
	private static int merge(int[] arr,int[] temp,int left,int mid,int right) {
		int i = left,j = mid,k = left;
		int invCount = 0;
		
		while((i <= (mid-1)) && (j <= right)) {
			if(arr[i] <= arr[j])
				temp[k++] = arr[i++];
			else {
				temp[k++] = arr[j++];
				invCount += (mid-i);
			}
		}
		
		while(i <= (mid-1)) {
			temp[k++] = arr[i++];
		}
		
		while(j <= right) {
			temp[k++] = arr[j++];
		}
		
		for(i = left;i <= right;i++) {
			arr[i] = temp[i];
		}
		return invCount;
	}
	
	public static void print(int[] arr) {
		System.out.print("\n");
		for(int num:arr) {
			System.out.print(num+" ");
		}
	}
	
	
	public static void main(String[] args) {
		int[] arr = {7,6,5,4,3,2,1,0};
		MergeSort.mergeSort(arr, 7);
		System.out.print("\nSorted array: ");
		MergeSort.print(arr);
		
	}
}

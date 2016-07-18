package array;

public class InversionCounting {
	public static int mergeSort(int[] arr) {
		int size = arr.length;
		int[] temp = new int[arr.length];
		return mergeSortHelper(arr,temp, 0, size-1);
	}
	
	private static int mergeSortHelper(int[] arr,int[] temp,int left,int right) {
		int mid;
		int invCount = 0;
		if(right > left) {
			mid = (left + right) / 2;
			invCount = mergeSortHelper(arr,temp, left, mid);
			invCount += mergeSortHelper(arr,temp,  mid+1,right);
			invCount += merge(arr,temp,left,mid+1,right);
		}
		return invCount;
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
			arr[i] = temp [i];
		}
		return invCount;
	}
	
	public static void main(String[] args) {
		int[] arr = {2, 4, 1, 3, 5};
		System.out.println("Inversion Count : " + mergeSort(arr));
	}
}

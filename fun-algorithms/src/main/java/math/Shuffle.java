package math;

/**
 * @author Prasad
 * Fishe - Yates shuffle algorithm
 */
public class Shuffle {
	
	
	public static void shuffle(int[] arr) {
		for(int i=arr.length-1;i>0;i--) {
			int j = (int) (Math.random()*i);
			// swap a[i] and a[j]
			arr[i] = arr[i] + arr[j];
			arr[j] = arr[i] - arr[j];
			arr[i] = arr[i] - arr[j];
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8,9,0};
		shuffle(arr);
		for(int i:arr) {
			System.out.print(i + " ");
		}
	}
}

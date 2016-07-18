package bitmanipulation;

public class UniqueNumber {
	public static int uniqueNumber(int[] list) {
		int result = 0;
		for(int i = 0;i<32;i++) {
			int temp = 0;
			int x = 1 << i;
			for(int j = 0;j<list.length;j++) {
				temp += ((list[j] & x) > 0?1:0); 
			}
			if(temp % 3 > 0) {
				result = result | x;
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		int arr[] = {12, 1, 12, 3, 12, 1, 1, 2, 3, 2, 2, 3, 7};
		System.out.print("Unique number : " + uniqueNumber(arr));
	}
}

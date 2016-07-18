package array;

/**
 * @author Prasad
 * Kadane's algorithm to find the max subset sum from an array of integers
 */
public class MaxSumSubset {
	/**
	 * @param list
	 * @return
	 * 
	 * The TC of the algorithm is O(n)
	 * We can also find the range in O(n)
	 */
	/*	public static int maxSum(int[] list) {
		int maxSum = 0,maxSumTillHere = 0;
		int begin = -1,end = -1,temp=-1;
		int tempBeg=-1;
		
		for(int i:list) {
			temp = maxSumTillHere;
			maxSumTillHere = Math.max(0, maxSumTillHere + i);
			
			if(maxSumTillHere <= 0) {
				tempBeg = i;
			}
			
			temp = maxSum;
			maxSum = Math.max(maxSumTillHere, maxSum);
			if(temp != maxSum) {
				end = i;
				begin = tempBeg;
			}
		}
		System.out.print("Max sum : " + begin + " " + end);
		return maxSum;
	}
	*/
	
	public static int maxSum(int[] list) {
		int maxSum = list[0], maxSumHere = list[0];
		int begin = -1, end = -1, tempBeg = -1;
		for(int i=1; i<list.length; i++){
			maxSumHere = Math.max(maxSumHere + list[i], list[i]);
			if(maxSumHere <= 0){
				tempBeg = i+1;
			}
			if(maxSum < maxSumHere){
				maxSum = maxSumHere;
				begin = tempBeg;
				end = i;
			}
		}
		System.out.println("Max sum from:"+begin+" till:"+end);
		return maxSum;
	}
	
	public static void main(String[] args) {
		int[] arr = { 1,2,-4,3,4,-1,5,-10,11};
		System.out.print(" Max sum : " + maxSum(arr));
	}
}

package dynamicprogramming.simplified;

public class MaximumSubarray {
	
	public static int maxContigiousSum(int[] list) {
		int sum,sumTillHere;
		sumTillHere = list[0];
        sum = list[0];
		
		for(int i=1;i<list.length;i++) {
			sumTillHere = Math.max(list[i], list[i] + sumTillHere);
			sum = Math.max(sumTillHere, sum);
			if(sumTillHere < 0)
				sumTillHere = 0;
		}
		
		return sum;
	}
	
	public static int maxNonContigiousSum(int[] list) {
		int sum = list[0];
		for(int i=1;i<list.length;i++) {
			if(list[i] > 0)
				sum += list[i];
			else {
				if(sum < list[i])
					sum = list[i];
			}
		}
		
		return sum;
	}
}

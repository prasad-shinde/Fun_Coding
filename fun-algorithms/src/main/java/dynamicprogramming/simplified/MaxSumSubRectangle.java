package dynamicprogramming.simplified;

import java.util.Arrays;

public class MaxSumSubRectangle {

	public int[] maxSumSubRect(int[][] rect) {
		int m = rect.length;
		int n = rect[0].length;
		
		int[] temp = new int[m];
		int[] res = new int[4];
		int max = Integer.MIN_VALUE;
		
		for(int l=0;l<n;l++) {
			Arrays.fill(temp, 0);
			for(int r=l;r<n;r++) {
				for(int i=0;i<m;i++) {
					temp[i] += rect[i][r];
				}
				
				int[] kadaneResult = kadane(temp);
				if(max<kadaneResult[2]) {
					max = kadaneResult[2];
					res[0] = l;
					res[1] = r;
					res[2] = kadaneResult[0];
					res[3] = kadaneResult[1];
				}
			}
		}
		return res;
	}

	private int[] kadane(int[] num) {
		int[] index = new int[3];
		int beginMax,endMax,begin;
		int sum = num[0];
		int maxSum = Integer.MIN_VALUE;
		
		begin = 0;
		maxSum = sum; beginMax = begin;endMax = 0;
		
		for(int i=1;i<num.length;i++) {
			sum = Math.max(num[i], num[i]+sum);
			if(maxSum<sum) {
				if(sum == num[i]) {
					maxSum = sum;
					beginMax = i;
					endMax = i;
				} else {
					maxSum = sum;
					beginMax = begin;
					endMax = i;
				}
			}
			
			if(sum < 0) {
				sum = 0;
				begin = i+1;
			}
		}
		
		index[0] = beginMax;
		index[1] = endMax;
		index[2] = maxSum;
		return index;
	}
	
	public static void main(String[] args) {
		int input[][] = {
				{ 2,  1, -3, -4,  5},
                { 0,  6,  3,  4,  1},
                { 2, -2, -1,  4, -5},
                {-3,  3,  1,  0,  3}	
                };
		
		MaxSumSubRectangle m = new MaxSumSubRectangle();
		for(int i : m.maxSumSubRect(input)) {
			System.out.print(i + " ");
		}
	}
}

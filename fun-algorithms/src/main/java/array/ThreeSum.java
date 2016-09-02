package array;

import java.util.Arrays;

/**
 * 
 * 259. 3Sum Smaller  QuestionEditorial Solution  My Submissions
		Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n 
		that satisfy the condition nums[i] + nums[j] + nums[k] < target.
		
		For example, given nums = [-2, 0, 1, 3], and target = 2.
		
		Return 2. Because there are two triplets which sums are less than 2:
		
		[-2, 0, 1]
		[-2, 0, 3]
		
		Reference : 
		Question : https://leetcode.com/problems/3sum-smaller/
		Solution : http://www.cnblogs.com/jcliBlogger/p/4736809.html
		
 * @author prasshinde
 *
 */
public class ThreeSum {
    
    public int threeSumSmaller(int[] nums, int target) {
        int result = 0;
        Arrays.sort(nums);
        
        for(int i=0;i<nums.length-2;i++) {
            int j = i+1;
            int k = nums.length-1;
            while(j<k) {
                int sum = nums[i] + nums[j] + nums[k];
                if(sum < target) {
                    result+=k-j;
                    j++;
                } else if(sum>=target) {
                    k--;
                } 
            }
        }
        
        return result;
    }
}

package array;

/**
 * 42. Trapping Rain Water 
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
	
	For example, 
	Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
	
	Reference : 
	Question : https://leetcode.com/problems/trapping-rain-water/
	Solution : http://www.programcreek.com/2014/06/leetcode-trapping-rain-water-java/
	
 * @author prasshinde
 *
 */
public class TrapRainWater {
    public int trap(int[] height) {
        if(height == null || height.length<=2)
            return 0;
        int result = 0;
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        
        int max = height[0];
        left[0] = height[0];
        for(int i=1;i<height.length;i++) {
            if(height[i]<max) {
                left[i] = max;
            } else {
                left[i] = height[i];
                max = height[i];
            }
        }
        
        max = height[height.length-1];
        right[height.length -1] = height[height.length-1];;
        for(int i = height.length-2;i>=0;i--) {
            if(height[i] < max) {
                right[i] = max;
            } else {
                right[i] = height[i];
                max = height[i];
            }
        }
        
        for(int i=0;i<height.length;i++) {
            result += Math.min(right[i],left[i]) - height[i];
        }
        return result;
    }
}

package array;

import javax.print.attribute.standard.Finishings;

public class MedianOfTwoArray {
    
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i=0,j=0;
        int factor1 = 1,factor2 = 1;
        int size = nums1.length + nums2.length;
        while(i+j != Math.floor((size)/2)) {
            if(nums1[i] < nums2[j]) {
                int value = specialDouble(factor1);
                if(i + j + value <= Math.floor((size-1)/2)) {
                    i += value;
                    factor1++;
                } else {
                    i++;
                    factor1 = 1;
                }
            } else {
                int value = specialDouble(factor2);
                if(i + j + value <= Math.floor((size-1)/2)) {
                    j += value;
                    factor2++;
                } else {
                    j++;
                    factor2 = 1;
                }
            }
        }
        if(i >= nums1.length)
        	i--;
        if(j >= nums2.length)
        	j--;
        
        if(i != 0 && j != 0) {
            return (nums1[i] + nums2[j])/2;
        } else if(i == 0 && j!= 0) {
            if(j == nums2.length-1) {
                return (nums1[i] + nums2[j])/2;
            } else {
                return (nums2[j+1] + nums2[j])/2;
            }
        } else {
            if(i == nums1.length-1) {
                return (nums1[i] + nums2[j])/2;
            } else {
                return (nums1[i+1] + nums1[i])/2;
            }
        }
    }
	
    private static int specialDouble(int factor) {
        return 2*factor;
    }
    
    public static void main(String[] args) {
    	int[] nums1 = {};
    	int[] nums2 = {3,4};
    	System.out.print(findMedianSortedArrays(nums1,nums2));
    }
}
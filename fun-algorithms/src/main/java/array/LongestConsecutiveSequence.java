package array;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> elements = new HashSet<Integer>();
        int max = 1;
        int curMax = 1;
        for(int num:nums) {
            elements.add(num);
        }
        
        for(int num:nums) {
            curMax = 1;
            int left = num-1;
            int right = num+1;
            while(elements.contains(left)) {
                curMax++;
                elements.remove(left);
                left--;
            }
            
            while(elements.contains(right)) {
                curMax++;
                elements.remove(right);
                right++;
            }
            max = Math.max(max,curMax);
        }
        return max;
    }
}

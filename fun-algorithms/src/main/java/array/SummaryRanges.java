package array;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        if(nums == null)
            return null;
        List<String> result = new ArrayList<String>();
        if(nums.length == 1) {
            result.add(nums[0]+"");
            return result;
        }
        int start, prev;
        start = nums[0];
        prev = nums[0];
        
        for(int i=1; i<nums.length;i++){
            int num = nums[i];
            if(num == prev + 1) {
                prev = num;
            } else {
                String res = start + "";
                if(prev != start) {
                    res += "->" + prev;
                }
                result.add(res);
                start = prev = num;
            }
        }
        
        String res = start + "";
        if(prev != start) {
            res += "->" + prev;
        }
        result.add(res);
        return result;
    }
}
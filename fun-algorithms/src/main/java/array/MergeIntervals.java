package array;

import java.util.ArrayList;
import java.util.List;


 
/**
 * 56. Merge Intervals : Given a collection of intervals, merge all overlapping intervals.
	For example,
	Given [1,3],[2,6],[8,10],[15,18],
	return [1,6],[8,10],[15,18].
	
 * Reference : 
 * 1. https://leetcode.com/problems/merge-intervals/
 * 
 * @author prasshinde
 *
 */
public class MergeIntervals {
    
    //[[1,3],[2,6],[8,10],[15,18]]
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals == null || intervals.isEmpty() || intervals.size() == 1)
            return intervals;
        List<Interval> mergedIntervals = new ArrayList<Interval>();
        for(int i=0;i<intervals.size();i++) {
            Interval current = intervals.get(i);
            while(i+1 < intervals.size() && isOverlapping(current,intervals.get(i+1))) {
                current = unify(current,intervals.get(i+1));
                i++;
            }
            mergedIntervals.add(current);
        }
        return mergedIntervals;
    }
    
    // this assumes a starts before or with b 1,6 & 8,10
    private boolean isOverlapping(Interval a,Interval b) {
        if(a.start == b.start)
            return true;
        if((a.start < b.start && a.end >= b.end) || (b.start <= a.end && b.start>= a.start))
            return true;
        return false;
    }
    
    private Interval unify(Interval a,Interval b) {
        return new Interval(Math.min(a.start,b.start),Math.max(a.end,b.end));
    }
}

//Definition for an interval.
class Interval {
   int start;
   int end;
   Interval() { start = 0; end = 0; }
   Interval(int s, int e) { start = s; end = e; }
}
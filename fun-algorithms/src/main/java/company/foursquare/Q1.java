package company.foursquare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Q1 {
    static class Interval {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    static class IntervalComparator implements Comparator<Interval> {

		public int compare(Interval o1, Interval o2) {
			return o1.start - o2.start;
		}
    	
    }

    /**
     * Algorithm : 
     * 1. First we sort the intervals based on the start time in ascending order
     * 2. We start from the first interval and considering the next adjacent interval and merge them if they overlap
     * 3. If they don't overlap we add the empty slot to the result list
     * 
     * Time complexity analysis :
     * 1. Here soring takes O(nlgn) time
     * 2. After sorting we just need one pass to figure out the empty slots
     * 
     * Time Complexity : O(nlgn)
     * Space Complexity : O(n)
     * 
     * @param intervals
     * @return returns a list of empty slots/intervals between a given set of intervals
     */
    private static List<Interval> uncoveredIntervals(List<Q1.Interval> intervals) {
        List<Interval> uncovered = new ArrayList<Q1.Interval>();
        if(intervals.size() < 2)
        	return uncovered;
        
        Collections.sort(intervals, new IntervalComparator());
        Interval current = intervals.get(0);
        for(int i = 1;i<intervals.size();i++) {
        	Interval next = intervals.get(i);
        	if(!areOverlapping(current, next)) {
        		Interval range = new Interval(current.end, next.start);
        		uncovered.add(range);
        		current = next;
        	} else {
        		current.end = Math.max(current.end, next.end);
        		current.start = Math.max(current.start, next.start);
        	}
        }
        return uncovered;
    }

    private static boolean areOverlapping(Interval i1,Interval i2) {
    	return inRange(i1.start,i2.start,i2.end) || inRange(i1.end,i2.start,i2.end) ||
    			inRange(i2.start,i1.start,i1.end) || inRange(i2.end,i1.start,i1.end);
    }
    
    private static boolean inRange(int num,int left,int right) {
    	return num >= left && num <= right;
    }
    
    /*
     *  Hey! You probably don't need to edit anything below here
     */

    private static List<Q1.Interval> readIntervals(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        List<Q1.Interval> intervals = new ArrayList<Q1.Interval>();
        String line;
        while ((line = reader.readLine()) != null && line.length() != 0) {
            intervals.add(toInterval(line));
        }
        return intervals;
    }

    private static Q1.Interval toInterval(String line) {
        final String[] tokenizedInterval = line.split(" ");

        return new Interval(Integer.valueOf(tokenizedInterval[0]),
                            Integer.valueOf(tokenizedInterval[1]));
    }

    public static void main(String... args) throws IOException {
        List<Q1.Interval> intervals = Q1.readIntervals(System.in);
        List<Q1.Interval> uncovered = Q1.uncoveredIntervals(intervals);
        for (Interval i : uncovered) {
            System.out.println(i.start + " " + i.end);
        }
    }
}

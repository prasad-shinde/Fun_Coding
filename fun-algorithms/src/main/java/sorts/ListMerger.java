package sorts;

import java.util.ArrayList;
import java.util.List;

public class ListMerger {
	// Input: Any number of already sorted lists.
	// Output: One list of all elements in sorted order.
	//
	// N sorted lists, and average size of each list is k elements.
	// [[1,2],[1,3,5],[2,4,6,7]]

	boolean hasEnded(List<Integer> pointers,List<List<Integer>> list) {
	    // it would if we are at end of all the list
		return false;
	}


	// N lists
	// for x elements
	List<Integer> convertToOne(List<List<Integer>> list) {
	    int count = list.size();
	    List<Integer> result = new ArrayList<Integer>();
	    List<Integer> pointers = new ArrayList<Integer>();
	    
	    for(int i = 0;i<count;i++) {
	        pointers.add(0);
	    }
	    
	    while(!hasEnded(pointers,list)) { 
	        int min = Integer.MAX_VALUE;
	        int pointerIndex = -1;
	        
	        for(int i=0;i<pointers.size();i++) {      //  O(n)
	            int currentValue = list.get(i).get(pointers.get(i));
	            // check
	            
	            if(currentValue < min) {
	                min = currentValue;
	                pointerIndex = i;
	            } 
	        }
	        
	        result.add(min);
	        pointers.set(pointerIndex,pointers.get(pointerIndex) + 1);
	    }
	    
	    return result;
	}
}

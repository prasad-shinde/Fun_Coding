package array;

//you can also use imports, for example:
import java.util.*;

//you can use System.out.println for debugging purposes, e.g.
//System.out.println("this is a debug message");

class AttractiveCities {
 public int solution(int K, int[] C, int[] D) {
     // write your code in Java SE 8
     int numOfCities = D.length;
     int max;
     int index;
     int count = 0;
     List<Integer> selected = new ArrayList<Integer>();
     index = maxIndex(D,Integer.MAX_VALUE,selected);
     max = D[index];
     selected.add(index);
     System.out.print(" " + index);
     K--;
     
     for(int i=0;i<K;i++) {
         index = maxIndex(D,max,selected);
         System.out.print(" " + index);
         max = D[index];
         selected.add(index);
     }
     
     for(Integer i:selected) {
         if(selected.contains(C[i]))
             count++;
         else
             break;
     }
     return count + 1;
 }
 
	public int maxIndex(int[] arr,int limit,List<Integer> selected) {
	    int max = Integer.MIN_VALUE;
	    int index = -1;
	    for(int i = 0;i<arr.length;i++) {
	        if(selected.contains(i))
	            continue;
	        if(max < arr[i]) {
	            max = arr[i];
	            index = i;
	        }
	    }
	    
	    return index;
	}
}

package math;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Prasad
 * The pascal Triangle : (a+b)^n formula
 * 0           1
 * 1          1 1
 * 2         1 2 1
 * 3        1 3 3 1
 * 4       1 4 6 4 1
 * 
 *       
 * Pascal Row Formula : row[i] = row[i-1] * (n-i+1)/i;
 */
public class PascalTriangle { 
	
	/**
	 * @param n
	 * @return
	 * 
	 * TC = O(n^2) I guess we can do better, lets do it!
	 */
	public static List<Integer> nthRow(int n) {
		List<Integer> initial = new ArrayList<Integer>();
		List<Integer> result = new ArrayList<Integer>();
		if(n == 0)
			return result;
		
		initial.add(1);
		if(n == 1)
			return initial;
		initial.add(2);
		initial.add(1);
		n-=2;
		
		while(n>0) {
			Iterator<Integer> it = initial.iterator();
			int prev = -1,current = -1;
			while(it.hasNext()) {
				if(prev == -1) {
					prev = it.next();
					result.add(prev);
					continue;
				}
				current = it.next();
				result.add(prev + current);
				prev = current;
			}
			result.add(current);
			initial.clear();
			initial.addAll(result);
			result.clear();
			n--;
		}
		for(int i:initial) {
			System.out.print(i + " ");
		}
		return result;
	}
	
	/**
	 * @param n
	 * Still not O(n) but reduced it by factor of 2
	 */
	public static void nthRowOpt(int n) {
		int[] result = new int[n+1];
		for(int i = 0;i<=n;i++) {
			result[i] = 0;
		}
		
		result[0] = 1;
		if(n == 1) {
			System.out.print(1);
		}
		result[1] = 2;
		if(n == 2) {
			System.out.print("1 2");
		}
		int i=2;
		
		while(i<n) {
			int j = i-1;
			while(j>0) {
				if(result[j] != 0)
					result[j] += result[j-1];
				else
					if(i%2!=0)
						result[j] = 2*result[j-1];
				j--;
			}
			i++;
		}
		
		for(int num:result) {
			if(i==0)
				break;
			System.out.print(num + " ");
		}
	}
	
	public static int[] pascal_row(int n){
	    int[] row = new int[n+1];
	    row[0] = 1; //First element is always 1
	    
	    for(int i=1; i<n/2+1; i++){ //Progress up, until reaching the middle value
	        row[i] = row[i-1] * (n-i+1)/i;
	    }
	    
	    for(int i=n/2+1; i<=n; i++){ //Copy the inverse of the first part
	        row[i] = row[n-i];
	    }
	    
	    for(int num:row) {
			System.out.print(num + " ");
		}
	    return row;
	}
	
	
	public static void main(String[] args) {
		nthRow(15);
		System.out.println();
		//nthRowOpt(10);
		pascal_row(15);
	}
}

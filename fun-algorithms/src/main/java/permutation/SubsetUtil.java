package permutation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Subsets : Given a set as input => produce subsets of all size from the given input set
 * 
 * Example :
 * Input : 1,2,3
 * Output : {1},{2},{3},{1,2},{1,3},{2,3},{1,2,3}
 * 
 * Links : Power set : http://en.wikipedia.org/wiki/Power_set
 * 
 * @author Prasad
 *
 * @param <T> Attempt to make it generic but messed up a lot of things!
 */
public class SubsetUtil<T> {
	
	/**
	 * Example :
	 * IP : 1,2,3
	 * OP : {1},{2},{3},{1,2},{1,3},{2,3},{1,2,3}
	 * 
	 * Time Complexity : 2^n	n - number of items
	 * 
	 * @param items List of items
	 * @return
	 */
	public List<ArrayList<T>> subsets(List<T> items) {
		List<ArrayList<T>> result = new ArrayList<ArrayList<T>>();
		Iterator<T> it = items.iterator();
		
		while(it.hasNext()) {
			subsetUtil(result, it.next());
		}
		return result;
	}
	
	private void subsetUtil(List<ArrayList<T>> result,T item) {
		List<ArrayList<T>> intermediateResult = new ArrayList<ArrayList<T>>();
		ArrayList<T> currentSubset = new ArrayList<T>();

		// add the element as a single entity to the subset
		currentSubset.add(item);
		intermediateResult.add(currentSubset);
		Iterator<ArrayList<T>> it = result.iterator();
		
		// add subsets of increasing sizes
		while(it.hasNext())  {
			currentSubset = new ArrayList<T>();
			currentSubset.addAll(it.next());
			currentSubset.add(item);
			intermediateResult.add(currentSubset);
		}
		
		// update the final result by adding the intermediate result
		result.addAll(intermediateResult);
	}
	
	public void print(List<ArrayList<T>> result) {
		Iterator<ArrayList<T>> it = result.iterator();
		
		while(it.hasNext()) {
			System.out.println();
			Iterator<T> i = it.next().iterator();
			while(i.hasNext()) {
				System.out.print(i.next() + " ");
			}
		}
	}
	
	public static void main(String[] args) {
		List<Integer> r = new ArrayList<Integer>();
		r.add(1);
		r.add(2);
		r.add(3);
		
		List<ArrayList<Integer>> result;
		SubsetUtil<Integer> su = new SubsetUtil<Integer>();
		result = su.subsets(r);
		su.print(result);
	}
}

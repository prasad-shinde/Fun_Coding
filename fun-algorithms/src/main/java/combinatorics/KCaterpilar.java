package combinatorics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import math.MathUtil;
import permutation.SubsetUtil;

/**
 * K caterpilar problem :
 * There are n leaves numbered 0 to n. There are k caterpilars.
 * Every caterpilar eats the multiple of ith leaf. This is specified by a array.
 * 
 * Example :
 * Let there be n=24 leaves
 * Let there be 3 caterpilar each eating leaves in multiple of {2,3,4}
 * 
 * Uneaten leaves :  {1, 5, 7, 11, 13, 17, 19, 23}
 * 
 * Solution : 
 * This could be solved using the inculsion and exculsion principle.
 * 
 * 1. caterpilar i eats = floor(n/cat[i]) leaves
 * 
 * 2. caterpilar i and j both eat = floor(n/lcm(cat[i],cat[j])) leaves so we need to subtract these from eaten leaves because we dont
 * 															 want it to be counted twice
 * 
 * 3. caterpilar i,j and k eat = floor(n/lcm(cat[i],cat[j],cat[k]) leaves and now we need to add these to eaten leaves because it was
 * 																subtracted by the result of {i,j},{j,k} and {i,k}
 * 
 * |A U B U C| = |A| + |B| + |C| - |A ^ B| - |A ^ C| - |B ^ C| + |A ^ B ^ C|.
 * 
 * Some Inspiration for solution :
 * 1. Inculsion Exculsion Principle : http://en.wikipedia.org/wiki/Inclusion%E2%80%93exclusion_principle
 * 2. K caterpilar solution Exlationation : http://stackoverflow.com/questions/27248327/caterpillars-and-leaves-can-we-do-better-than-onc
 * 
 * @author Prasad
 *
 */
public class KCaterpilar {
	private int numOfLeaves;
	private int[] caterpilars;
	
	public KCaterpilar(int num,int[] c) {
		numOfLeaves = num;
		caterpilars = c;
	}
	
	public int uneatenLeaves() {
		int leavesEaten = 0;
		List<Integer> cats = new ArrayList<Integer>();
		for(int c:caterpilars) {
			cats.add(c);
		}
		
		List<ArrayList<Integer>> lists = new SubsetUtil<Integer>().subsets(cats);
		
		// NOTE: IMPORTANT to sort the subsets in increasing order of sizes(Actually it wont matter BUT SIMPLE TO VISUALIZE)
		sort(lists);
		//new SubsetUtil<Integer>().print(lists);		you can actually print the subsets to check the results
		
		for(List<Integer> subset:lists) {
			if((subset.size() % 2) == 1) {
				leavesEaten += Math.floor(numOfLeaves/MathUtil.lcm(subset));
			} else {
				leavesEaten -= Math.floor(numOfLeaves/MathUtil.lcm(subset));
			}
		}

		return numOfLeaves - leavesEaten;
	}
	
	
	@SuppressWarnings("rawtypes")
	private void sort(List<ArrayList<Integer>> lists) {
		Collections.sort(lists, new Comparator<ArrayList>(){
		    public int compare(ArrayList a1, ArrayList a2) {
		        return a1.size() - a2.size(); // assumes you want smallest to biggest
		    }
		});
	}
	
	public static void main(String[] args) {
		int n = 24;
		int[] cats = {2,3,4};
		KCaterpilar kc = new KCaterpilar(n, cats);
		System.out.println(kc.uneatenLeaves());
	}
}

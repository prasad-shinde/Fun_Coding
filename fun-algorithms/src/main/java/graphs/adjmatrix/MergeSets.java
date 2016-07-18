package graphs.adjmatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MergeSets {
	
	
	// TC = O(n^2) where n = number of sets in the list
	/**
	 * @param list
	 * @return
	 */
	public static List<Set<Integer>> mergeIntersectingSets(List<Set<Integer>> list) {
		List<Set<Integer>> result = new ArrayList<Set<Integer>>();
		boolean[] exists = new boolean[list.size()];
		Arrays.fill(exists, true);
		
		for(int i = 0;i<list.size();i++) {
			if(exists[i] == false)
				continue;
			for(int j=0;j<list.size();j++) {
				if(i==j || exists[j] == false)
					continue;
				if(intersect(list.get(i),list.get(j))) {
					merge(list.get(i),list.get(j));
					exists[j] = false;
				}
			}
		}
		
		for(int i=0;i<list.size();i++) {
			if(exists[i])
				result.add(list.get(i));
		}
		
		for(Set<Integer> set:result) {
			System.out.println();
			for(int item:set) {
				System.out.print(item + " ");
			}
		}
		
		return result;
	}
	
	// TC = O(n+m)
	private static void merge(Set<Integer> set1,Set<Integer> set2) {
		set1.addAll(set2);
	}
	
	// TC = O(n)
	private static boolean intersect(Set<Integer> set1,Set<Integer> set2) {
		for(int item:set1) {
			if(set2.contains(item))
				return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		List<Set<Integer>> list = new ArrayList<Set<Integer>>();
		Set<Integer> s1 = new HashSet<Integer>();
		s1.add(1);
		s1.add(2);
		s1.add(3);
		Set<Integer> s2 = new HashSet<Integer>();
		s2.add(4);
		s2.add(5);
		s2.add(6);
		Set<Integer> s3 = new HashSet<Integer>();
		s3.add(8);
		s3.add(9);
		Set<Integer> s4 =  new HashSet<Integer>();
		s4.add(3);
		s4.add(8);
		
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		
		mergeIntersectingSets(list);
	}
}

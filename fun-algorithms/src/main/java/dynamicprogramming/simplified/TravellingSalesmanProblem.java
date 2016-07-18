package dynamicprogramming.simplified;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Prasad
 * Travelling Salesman Problem :
 * 
 */
public class TravellingSalesmanProblem {
	private int[][] dist;
	private Map<String,Integer> C;
	private int n;
	
	public int tsp(int start) {
		Set<Integer> s = new HashSet<Integer>();
		s.add(1);
		C.put(generateKey(s,1), 0);
		
		for(int subsetSize = 2;subsetSize<=n;subsetSize++) {
			List<Set<Integer>> subsetList = subset(subsetSize);
			for(Set<Integer> subset:subsetList) {
				for(Integer j:subset) {
					if(j==1)
						continue;
					C.put(generateKey(subset,j), Integer.MAX_VALUE); 
					for(Integer i:subset) {
						// C(S,j) = Min{C(S-{j},i) + D[i,j]}
						Set<Integer> temp = new HashSet<Integer>();
						temp.addAll(subset);
						temp.remove(j);
						C.put(generateKey(subset,j), Math.min(C.get(generateKey(subset,j)),C.get(generateKey(temp,i)) + dist[i][j]));
					}
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		for(int i=1;i<=n;i++)
			s.add(i);
		for(int i = 1;i<=n;i++) {
			min = Math.min(min,C.get(generateKey(s,i)));
		}
		return min;
	}
	
	public String generateKey(Set<Integer> set,int n) {
		int[] unsorted = new int[set.size()];
		int i=0;
		for(Integer element:set) {
			unsorted[i++] = element;
		}
		
		Arrays.sort(unsorted);
		StringBuilder sb = new StringBuilder();
		for(int element:unsorted)
			sb.append(element);
		return sb.toString() + n;
	}
	
	private List<Set<Integer>> subset(int s) {
		return null;
	}
}

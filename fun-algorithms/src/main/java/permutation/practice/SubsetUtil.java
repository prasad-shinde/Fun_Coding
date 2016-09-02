package permutation.practice;

import java.util.ArrayList;
import java.util.List;

import binarynode.AVLNode;

public class SubsetUtil {
	
	
	/************************* Permute Subsets ********************************/
	public static List<List<Integer>> permuteSubsets(List<Integer> input) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		allSubsets(input, result, new ArrayList<Integer>());
		return result;
	}
	
	public static void allSubsets(List<Integer> input,List<List<Integer>> result,List<Integer> prefix) {
		if(input.size() == 0)
			result.add(prefix);
		
		for(int i = 0;i<input.size();i++) {
			List<Integer> newPrefix = new ArrayList<Integer>();
			List<Integer> newInput = new ArrayList<Integer>();
			
			newPrefix.addAll(prefix);
			newPrefix.add(input.get(i));
			
			newInput.addAll(input.subList(0, i));
			newInput.addAll(input.subList(i+1, input.size()));
			allSubsets(newInput,result,newPrefix);
		}
	}
	/************************* Permute Subsets ********************************/
	
	
	
	
	
	
	@SuppressWarnings("unused")
	private static void swap(List<Integer> list,int a,int b) {
		int temp = list.get(a);
		list.add(a, list.get(b));
		list.add(b, temp);
	}
	
	
	/************************* Subsets of all sizes ********************************/
	public static List<List<Integer>> allSubsets(List<Integer> list) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for(Integer num:list) {
			addToSubset(result, num);
		}
		return result;
	}
	
	private static void addToSubset(List<List<Integer>> result,int num) {
		List<List<Integer>> newAdditions = new ArrayList<List<Integer>>();
		for(List<Integer> aList:result) {
			List<Integer> partialResult = new ArrayList<Integer>();
			partialResult.addAll(aList);
			partialResult.add(num);
			newAdditions.add(partialResult);
		}
		
		List<Integer> partialResult = new ArrayList<Integer>();
		partialResult.add(num);
		
		result.add(partialResult);
		result.addAll(newAdditions);
	}
	
	/************************* Subsets of all sizes ********************************/
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
//		for(List<Integer> aList:permuteSubsets(list)) {
//			System.out.print("\n");
//			for(Integer value:aList) {
//				System.out.print(value + " ");
//			}
//		}
		
		for(List<Integer> aList:allSubsets(list)) {
			System.out.print("\n");
			for(Integer value:aList) {
				System.out.print(value + " ");
			}
		}
	}
}

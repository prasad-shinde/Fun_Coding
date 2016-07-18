package array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import set.Set;

public class SubsetSum {
	
	/*
	 * Algorithm :
	 * 1. create an empty list of sets which will hold the result
	 * 2. start from index to size - 1, initialize sum = sum; :P
	 * 3. subtract current element from the sum and if its greater then 0 add to set
	 * 4. if sum == 0  and size of set is = given size
	 * 		add it to final result array
	 * 
	 * 5. start step 2 with index = 1, make it a recursive procedure with params(sumTillNow,
	 * 		finalResult,currentSet)
	 * 
	 * */
	
	
	public static List<Set<Integer>> subsetSum(int size,int sum) {
		List<Set<Integer>> result = new ArrayList<Set<Integer>>();
		Set<Integer> set = new Set<Integer>();
		subsetSumHelper(sum, 0, size, result, set);
		return result;
	}
	
	private static void subsetSumHelper(int remainingSum,int index,int size,List<Set<Integer>> result,Set<Integer> set) {
		if(remainingSum == 0 && size == set.size()) { //|| set.size() == size
			System.out.print("Here!!\n");
			Set<Integer> newSet = copyOfSet(set);
			result.add(newSet);
		}
		//else if(set.size() < size && remainingSum > 0){
		else {
			System.out.print("\nNot Yet Current Sum : " + remainingSum);
			for(int i = index + 1 ; i<=size;i++) {
				if((remainingSum - i) < 0)
					continue;
				set.add(i);
				subsetSumHelper(remainingSum - i,i,size,result,set);
				set.remove(i);
			}
		}
	}
	
	private static Set<Integer> copyOfSet(Set<Integer> set) {
		Iterator<Integer> it = set.iterator();
		Set<Integer> newSet = new Set<Integer>();
		while(it.hasNext()) {
			newSet.add(it.next());
		}
		return newSet;
	}
	
	public static void print(List<Set<Integer>> result) {
		Iterator<Set<Integer>> it = result.iterator();
		while(it.hasNext()) {
			it.next().print();
		}
	}
	
	public static void main(String[] args) {
		List<Set<Integer>> result = subsetSum(2,4);
		System.out.print("Ans!!\n");
		print(result);
	}
}

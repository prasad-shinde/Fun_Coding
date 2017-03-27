package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Factors {
	/*
	Write a program that takes an integer and prints out all ways to multiply smaller integers that equal the original number, without repeating sets of factors. In other words, if your output contains 4 * 3, you should not print out 3 * 4 again as that would be a repeating set. Note that this is not asking for prime factorization only. Also, you can assume that the input integers are reasonable in size; correctness is more important than efficiency.
			PrintFactors(12)
			6 * 2
			4 * 3
			3 * 2 * 2



			4 
			2*2

			5
			[]

			6 
			[2,3]

			7


			 n -> {[],[]}
			 
			 
			 0 to n 
			 
			 base case [1 -> {[1]}]
			 
			 
			 public Set<List<Integer>> factors(int n) {
			     Map<Integer,Set<List<Integer>>> map = new HashMap<Integer,Set<List<Integer>>>();
			     if(n < -1) throw new Exception("Invalid input");
			     // base cases 1,2,3
			     Set<List<Integer>> baseCase = new HashSet<List<Integer>>();
			     //baseCase.add([1]);
			     map.put(1,baseCase);
			     
			     baseCase = new HashSet<List<Integer>>();
			     //baseCase.add([2]);
			     map.put(2,baseCase);
			     
			     baseCase = new HashSet<List<Integer>>();
			     //baseCase.add([3]);
			     map.put(3,baseCase);
			     
			     
			     if(n<=3)
			         return map.get(n);
			     
			     for(int i=4;i<=n;i++) {
			         Set<List<Integer>> aSet = new HashSet<List<Integer>>();
			         for(int j = 1;j<=i/2;j++) {
			             //for(int k = 1;k<=i/2;k++) {
			             int k = (int)((float)i/(float)j);
			                 if(j*k == i) {
			                     List<Integer> result = new ArrayList<Integer>();
			                     //result.add(j);
			                     //result.add(k);
			                     Set<List<Integer>> partialResult = combinations(map,j,k);
			                     aSet.addAll(partialResult);
			                     aSet.add(result);
			                 }
			             //}
			         }
			         map.put(i,aSet);
			     }
			     
			     return map.get(n);
			 }
			 
			 /*
			 {
			     10 : {[5*2],[10*1],[x*y*z]}
			     13 : {[p*q],[s*t]}
			 }
			 
			 return -> {[5,2,p,q],[5,2,s,t] ....}
			 */
			 private Set<List<Integer>> combinations(Map<Integer,Set<List<Integer>>> map,int i,int j) {
			     Set<List<Integer>> combinations = new HashSet<List<Integer>>();
			     Set<List<Integer>> first = map.get(i);
			     List<Integer> self = new ArrayList<Integer>();
			     self.add(i);
			     first.add(self);
			     
			     self = new ArrayList<Integer>();
			     self.add(j);
			     Set<List<Integer>> second = map.get(j);
			     second.add(self);
			     
			     
			     for(List<Integer> firstList:first) {
			         for(List<Integer> secondList:second) {
			             List<Integer> result = new ArrayList<Integer>();
			             result.addAll(firstList);
			             result.addAll(secondList);
			             combinations.add(result);
			         }
			     }
			     return combinations;
			 }
}

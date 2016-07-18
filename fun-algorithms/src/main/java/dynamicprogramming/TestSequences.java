package dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

public class TestSequences {
	public static void main(String[] args) {
		List<Integer> list=new ArrayList<Integer>();
		list.add(1);
		list.add(5);
		list.add(10);
		list.add(4);
		list.add(2);
		list.add(15);
		list.add(0);
		list.add(64);
		List<Integer> result = Sequences.LIS(list);
		System.out.print("\n LIS :");
		for(Integer i:result) {
			System.out.print(" " + i);
		}
	}
}

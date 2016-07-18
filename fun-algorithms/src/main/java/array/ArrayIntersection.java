package array;


import set.Set;
import linkedlist.LinkList;

public class ArrayIntersection {
	public static void intersection(int[] first,int[] second) {
		LinkList intersection = new LinkList();
		Set<Integer> set = new Set<Integer>();
		
		for(int num:first) {
			set.add(num);
		}

		
		for(int num:second) {
			if(set.contains(num))
				intersection.insertFront(num);
		}
		
		intersection.print();
	}
	
	public static void swap(int[] list,int i,int j) {
		int temp = list[i];
		list[i] = list[j];
		list[j] = temp;
	}
	
	public static void moveNonZeroToFront(int []list) {
		int end = list.length - 1;
		int i = 0;
		
		while(i<end) {
			if(list[i] == 0) {
				while(list[end] == 0)
					end--;
				swap(list,i,end);
				end--;
			}
			i++;
		}
		print(list);
	}
	
	/** Note: Ask if the list contains positive or negative numbers? 
	 * @param list
	 * @param num
	 */
	public static void combinationsSumming(int[] list,int num) {
		Set<Integer> combinations = new Set<Integer>();
		combinationsSumming(list,-1,num,combinations);
	}
	
	public static void combinationsSumming(int[] list,int index, int sum,Set<Integer> selected) {
		if(sum == 0) {
			System.out.print("\n");
			selected.print();
		}
		for(int i = index+1;i<list.length;i++) {
			int num = list[i];
			if((sum - num) <0)
				continue;
			selected.add(num);
			combinationsSumming(list,i,sum - num,selected);
			selected.remove(num);			// backtrack.. remove the added element
		}
	}
	
	public static void print(int[] list) {
		System.out.print("\n");
		for(int num:list) {
			System.out.print(num + " ");
		}
	}
	
	public static void main(String[] args) {
		int first[] = new int[] {1,2,3,4,5,6,7,8,9,10,11};
		//int second[] = new int[] {5,6,7,8,9};
		//intersection(first,second);
		//moveNonZeroToFront(first);
		combinationsSumming(first,10);
	}
}

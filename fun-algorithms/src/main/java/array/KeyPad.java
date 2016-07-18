package array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class KeyPad {

/*	2 -> A, B, C
	3 -> D, E, F
	...

	in: 23
	out: [AD, AE, AF, BD, BE, BF, CD, CE, CF]

	// 1. number  
	2. split as single digits
	3. for every digit get the character arrays
	4. we have 2 character arrays

	List getCharacterArrays(); // [2, 3] -> ["ABC", "DEF"]
*/
	/*1,2,3
	ADG
	*/

	
	public static List<String> permute(int[] list) {
		List<String> result = new ArrayList<String>();
		permute(list,0,"",result);
		
		for(String s:result) {
			System.out.println(s);
		}

		return result;
	}
	
	private static void permute(int[] list,int i,String result,List<String> all) {
		if(i >= list.length) {
			all.add(result);
			return;
		}
		
		for(char c:keysForDigit(list[i])) {
			permute(list,i+1,result+c,all);
		}
	}
	
	private static char[] keysForDigit(int digit) {
		if(digit == 1)
			return new char[]{'A','B','C'};
		if(digit == 2)
			return new char[]{'D','E','F'};
		if(digit == 3)
			return new char[]{'G','H','I'};
		if(digit == 4)
			return new char[]{'J','J','L'};
		if(digit == 5)
			return new char[]{'M','N','O'};
		if(digit == 6)
			return new char[]{'P','Q','R'};
		return null;
	}
	
	void print(List<String> str) {
		Iterator<String> it = str.iterator();
		while(it.hasNext()) {
			System.out.print(it.next() +" ");
		}
	}
	
	public static void main(String[] args) {
		//KeyPad pad = new KeyPad();
		//List<String> str = pad.permute();
		//pad.print(str);
		permute(new int[] {1,2,3,4});
	}
}

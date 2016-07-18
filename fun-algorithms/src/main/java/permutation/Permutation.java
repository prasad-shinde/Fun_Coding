package permutation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Prasad
 * Permutation of N elements or characters of strings
 * 
 * Reference : http://introcs.cs.princeton.edu/java/23recursion/Permutations.java.html
 */
public class Permutation {

	public static List<String> permutations(String s) {
		List<String> result = new ArrayList<String>();
		permutations("",s,result);
		return result;
	}
	
	private static void permutations(String prefix,String s,List<String> result) {
		if(s.length() == 0)
			result.add(prefix);
		else {
			for(int i=0;i<s.length();i++) {
				permutations(prefix + s.charAt(i),s.substring(0, i) + s.subSequence(i+1, s.length()),result);
			}
		}
	}
	
	
}

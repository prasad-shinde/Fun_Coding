package quora.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class QuoraUtil {
	/** Finds the common strings from the n list of strings
	 * @param list
	 * @return
	 */
	public static List<String> common(List<List<String>> list) {
		if(list == null)
			return null;
		
		if(list.size() == 1)
			return list.get(0);
		
		List<String> result= new ArrayList<String>();
		Iterator<List<String>> it = list.iterator();
		List<String> first,second;
		
		if(it.hasNext()) {
			first = it.next();
		} else
			return null;
		
		while(it.hasNext()) {
			second = it.next();
			result = common(first,second);
			first = result;
			if(result == null || result.size() == 0)	// if at any point there is no common the return null
				return null;
		}
		return result;
	}
	
	/** Find the common string between the two list of strings
	 * @param a
	 * @param b
	 * @return
	 */
	public static List<String> common(List<String> a,List<String> b) {
		if(a == null || b == null)
			return null;
		
		Set<String> set = new HashSet<String>();
		List<String> common = new ArrayList<String>();
		
		for(String s:a) {
			set.add(s);
		}
		
		for(String s:b) {
			if(set.contains(s)) {
				common.add(s);
			}
		}
		
		return common;
	}
}

package array;
import java.util.*;

public class NeedleAndHaystack {
	// METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
	// RETURN "null" IF NO ANAGRAM FOUND
	public static List<Integer> getAnagramIndices(String haystack, String needle) 
	{
	    List<Integer> result = new ArrayList<Integer>();
	    if(haystack == null || needle == null)
	        return result;
		
		for(int i=0;i<(haystack.length() - needle.length() + 1);i++) {
			if(matchesFrom(haystack, needle, i)) {
				result.add(i);
			}
		}
		return result;
	}
	// METHOD SIGNATURE ENDS
	
	private static boolean matchesFrom(String haystack,String needle,int index) {
		Map<Character,Integer> count = anagramMap(needle);
	    for(int i = index;i<haystack.length();i++) {
	        if(count.size() == 0)
	        	return true;
	        char c = haystack.charAt(i);
	        if(count.containsKey(c)) {
	        	if(count.get(c) > 0) {
	        		count.put(c, count.get(c) - 1);
	        		if(count.get(c) == 0)
	        			count.remove(c);
	        	} else {
	        		return false;
	        	}
	        } else {
	        	return false;
	        }
	    }
	    return count.size() == 0;
	}
	
	private static Map<Character,Integer> anagramMap(String needle) {
	    Map<Character,Integer> map = new HashMap<Character,Integer>();
	    for(char c:needle.toCharArray()) {
	        if(map.containsKey(c)) {
	            map.put(c,map.get(c)+1);
	        } else {
	            map.put(c,1);
	        }
	    }
	    return map;
	}
	
	public static void main(String[] args) {
		for(int i:getAnagramIndices("abdcghbaabcdij","bcda")) {
			System.out.print(i + " ");
		}
	}
}

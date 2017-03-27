package strings;

public class RegularExpression {
	public static boolean isMatchRecursive(String s,String p) {
		if(p.isEmpty())
			return s.isEmpty();
		
		if('*' == p.charAt(1)) {
			return isMatchRecursive(s,p.substring(2)) || !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatchRecursive(s.substring(1),p);
		} else {
			return !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatchRecursive(s.substring(1),p.substring(1));
		}
	}
	
	public static boolean isMatchDp(String s,String p) {
		return true;
	}
}

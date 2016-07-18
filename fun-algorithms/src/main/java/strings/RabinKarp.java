package strings;

public class RabinKarp {
	public static int isSubstring(String s,String pattern) {
		int hs,hp;
		hs = hash(s,pattern.length());
		hp = hash(pattern,pattern.length());
		for(int i=1;i < (s.length()-pattern.length());i++) {
			if(hs == hp) {	// check if the string is actually equal
				return i;
			} else {
				hs = rolledHash(s.substring(i-1,i-1+pattern.length()), s.charAt(i+pattern.length()), hs);
			}
		}
		return -1;
	}
	
	private static int hash(String s,int len) {
		int hash = 0;
		for(int i=0;i < len;i++) {
			hash += s.charAt(i);
		}
		return hash;
	}
	
	private static int rolledHash(String s,char ch,int oldHash) {
		int newHash = oldHash - s.charAt(0);
		newHash += ch;
		return newHash;
	}
}

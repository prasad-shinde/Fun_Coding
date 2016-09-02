package dynamicprogramming.simplified;

import java.util.HashSet;
import java.util.Set;

/**
 * 139. Word Break
	Given a string s and a dictionary of words dict, determine if s can be 
	segmented into a space-separated sequence of one or more dictionary words.
	
	For example, given
	s = "leetcode",
	dict = ["leet", "code"].
	
	Return true because "leetcode" can be segmented as "leet code".
	
	Reference : https://leetcode.com/problems/word-break/
	
 * @author prasshinde
 *
 */
public class WordBreak {
    public static boolean wordBreak(String s, Set<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        dp[0] = true;
        
        for(int i=1;i<s.length();i++) {
            dp[i] = wordDict.contains(s.substring(0,i+1));
            for(int j=0;j<i;j++) {
                dp[i] = dp[i] || (dp[j] && wordDict.contains(s.substring(j+1,i+1)));
            }
        }
        
        return dp[s.length()-1];
    }
    
    public static void main(String[] args) {
    	Set<String> dict = new HashSet<String>();
    	String s = "aaaaaaa";
    	dict.add("aaaa");
    	dict.add("aa");
    	System.out.println(wordBreak(s, dict));
    }
}

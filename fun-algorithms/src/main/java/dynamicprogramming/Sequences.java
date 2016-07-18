package dynamicprogramming;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Prasad
 * it includes :
 * 1. Longest common subsequence
 * 2. Longest common substring
 * 3. Longest increasing subsequence
 */
public class Sequences {
	
	/**
	 * @param a The first string of the sequence
	 * @param b the second string of the sequence
	 * @return  The longest common subsequences of the given strings
	 * 
	 * LCS(i,j) = {
	 * 				"" if i or j == -1
	 * 				LCS(i-1,j-1) + "a[i]"  iff a[i] == b[j]
	 * 				Max(LCS(i,j-1),LCS(i-1,j))
	 * 			  }
	 */
	public static String LCS(String a,String b,int i,int j) {
		// if string indexes go before the first character
		if(i == -1 || j == -1) {
			return "";
		}
		
		if(a.charAt(i) == b.charAt(j)) {
			String subA = a.substring(0, i);
			String subB = b.substring(0, j);
			return (LCS(subA,subB,i-1,j-1) + a.charAt(i));
		}
		else {
			String subA = a.substring(0, i);
			String subB = b.substring(0, j);
			String first = LCS(a,subB,i,j-1);
			String second = LCS(subA,b,i-1,j);

			if(first.length()>=second.length())
				return first;
			else 
				return second;
		}
	}
	
	
	public static String LCSubseq_nonrec(String a,String b) {
		int[][] editDistance = new int[a.length()+1][b.length()+1];
		int i,j=0;
		
		for(i=0;i<=a.length();i++)
			editDistance[i][0] = 0;
		
		for(i=0;i<=b.length();i++)
			editDistance[0][i] = 0;
		
		for(i=1;i<=a.length();i++) {
			for(j=1;j<=b.length();j++) {
				if(a.charAt(i-1) == b.charAt(j-1)) {
					editDistance[i][j] = editDistance[i-1][j-1]+1;
				}
				else {
					editDistance[i][j] = maxNeighbour(editDistance,i,j);
				}
			}
		}
		
		System.out.print(editDistance[a.length()][b.length()]+"\n");
		// Back track to form the LCS
		i = a.length();
		j = b.length();
		StringBuffer lcs = new StringBuffer();
		while(i!=0 && j!= 0) {
			if(a.charAt(i-1)==b.charAt(j-1)) {
				lcs.append(a.charAt(i-1));
				i--;
				j--;
			}
			else {
				if(editDistance[i][j] == editDistance[i][j-1])
					j--;
				else if(editDistance[i][j] == editDistance[i-1][j])
					i--;
			}
		}
		return reverse(lcs.toString());
	}
	
	private static String reverse(String s) {
		StringBuffer reverse = new StringBuffer();
		int len = s.length();
		len--;
		while(len>=0) {
			reverse.append(s.charAt(len));
			len--;
		}
		return reverse.toString();
	}
	
	private static int maxNeighbour(int[][] arr,int i,int j) {
		return ((int)(Math.max(arr[i-1][j-1], Math.max(arr[i-1][j], arr[i][j-1]))));
	}
	
	
	public static String longestCommonSubstring(String a,String b) {
		int[][] editDistance = new int[a.length()+1][b.length()+1];
		int i,j=0;
		int max = 0,maxI=0,maxJ=0;
		
		
		for(i=0;i<=a.length();i++)
			editDistance[i][0] = 0;
		
		for(i=0;i<=b.length();i++)
			editDistance[0][i] = 0;
		
		for(i=1;i<=a.length();i++) {
			for(j=1;j<=b.length();j++) {
				if(a.charAt(i-1) == b.charAt(j-1)) {
					editDistance[i][j] = editDistance[i-1][j-1]+1;
					if(max <= editDistance[i][j]) {
						max = editDistance[i][j];
						maxI = i;
						maxJ = j;
					}
				}
				else {
					editDistance[i][j] = 0;
				}
			}
		}
		
		StringBuffer s = new StringBuffer();
		while((editDistance[maxI][maxJ]!=0) && (maxI!=0 && maxJ!=0)) {
			s.append(a.charAt(maxI-1));
			maxI--;
			maxJ--;
		}
		
		for(i=0;i<=a.length();i++) {
			System.out.print("\n");
			for(j=0;j<=b.length();j++) {
				System.out.print(editDistance[i][j]);
				System.out.print("\t");
			}
		}
		
		return reverse(s.toString());
	}
	
	/**
	 * @param list
	 * @return longest increasing sequence of integers
	 * 
	 * The program traverses a element by element and creates a list of integers which are 
	 * largest until now */
	public static List<Integer> LIS(List<Integer> list) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Iterator<Integer> it = list.iterator();
		
		while(it.hasNext()) {
			List<Integer> current = new ArrayList<Integer>();
			int next = it.next();
			if(!result.isEmpty()) {
				Iterator<List<Integer>> prev = result.iterator();
				while(prev.hasNext()) {
					List<Integer> prevList = prev.next();
					int temp = prevList.get(prevList.size()-1);
					if(temp <= next && prevList.size() >= current.size()) {
						current.clear();
						current.addAll(prevList);
						current.add(next);
					}
				}
				if(current.size() > 0)
					result.add(current);
			} else {
				current.add(next);
				result.add(current);
			}
		}
		// this step is wrong
		// find the one with longest length
		return result.get(result.size() - 1);
	}
	
	
	public static void main(String[] args) {
		
		// longest common subsequence
		String a = "GATTACA";
		String b = "ATACA";
		
		String p = LCS(a,b,a.length()-1,b.length()-1);
		System.out.println(p);
		
		String c = "TAGACCA";
		
		System.out.println(LCS(p,c,p.length()-1,c.length()-1));
		
		a = "PRETTY";
		b = "PRTTEIN";
		
		p = LCS(a,b,a.length()-1,b.length()-1);
		System.out.println(p);
		
		
		// longest common subsequence non rec results
		System.out.println("Longest common subsequence");
		a = "GATTACA";
		b = "ATACA";
		
		p = LCSubseq_nonrec(a,b);
		System.out.println(p);
		
		c = "TAGACCA";
		
		System.out.println(LCSubseq_nonrec(p,c));
		
		a = "PRETTY";
		b = "PRTTEIN";
		
		p = LCSubseq_nonrec(a,b);
		System.out.println(p);
		
		// longest common substring
		System.out.println("Longest common substring");
		a = "GATTACA";
		b = "ATACA";
		
		p = longestCommonSubstring(a,b);
		System.out.println(p);
		
		c = "TAGACCA";
		
		System.out.println(longestCommonSubstring(p,c));
		
		a = "PRETTY";
		b = "PRTTEIN";
		
		p = longestCommonSubstring(a,b);
		System.out.println(p);
	}
}

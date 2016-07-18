package array;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test {
	public static void main(String[] args) {
		//int repeat[] = new int[] {1 ,2,3 ,4,4,5,1,1}; 
		//removeDuplicates(repeat);
		/*String[] words = words("hi how are you");
		for(String s:words) {
			System.out.print(reverse(s) + " ");
		}*/
		
		/*int[][] multi = new int[][]{
				  { 0, 1, 2, 3, 4 },
				  { 0, 0, 0, 0, 0 },
				  { 10, 11, 12, 13, 14 },
				  { 0, 0, 0, 0, 0 },
				  { 20, 21,22, 23, 24 }
				};
		print2D(multi);*/
		//System.out.println(recurFact(5));
		//System.out.println(iterativeFact(2));
		
		/*System.out.print("Fib : " + fibonacci(11));
		System.out.print("Fib : " + recurFibo(11));
		
		System.out.print("\nAre anagrams? : " + areAnagrams("hello","ollhe"));
		System.out.print("\nAre anagrams? : " + areAnagrams("hello","ollhse"));*/
		StringBuffer s = new StringBuffer("hel");
		permute(s,0);
	}
	
	public static int gcd(int a,int b) {
		while(a!=b) {
			if(a>b)
				a = a-b;
			else
				b = b-a;
		}
		return a;
	}
	
	// this function would print all the possible strings from given set of words in the string
	public static void permute(StringBuffer str, int i) {
		if(i == str.length())
			System.out.println(str);
		else {
			for(int j=i;j<str.length();j++) {
					//swap(str[i],str[j]);
					char temp = str.charAt(i);
					str.insert(i, str.charAt(j));
					str.insert(j,temp);
					permute(str,i+1);
					temp = str.charAt(i);
					str.insert(i, str.charAt(j));
					str.insert(j,temp);					
			}
		}
	}
	
	
	
	public void threeSum() {
		/*
		 * Suppose the input array is S[0..n-1]. 3SUM can be solved in O(n^2) time on average 
		 * by inserting each number S[i] into a hash table, and then for each index i and j, 
		 * checking whether the hash table contains the integer -(S[i]+S[j]).*/
	}
	
	public static int recurFibo(int nth) {
		if(nth == 0)
			return 0;
		if(nth == 1)
			return 1;
		return(recurFibo(nth-1) + recurFibo(nth-2));
	}
	
	public static boolean areAnagrams(String first,String second) {
		Map<Character,Integer> characterCount = new HashMap<Character,Integer>();
		
		for(char c:first.toCharArray()) {
			if(characterCount.containsKey(c))
				characterCount.put(c, characterCount.get(c)+1);
			else
				characterCount.put(c, 1);
		}
		
		for(char c:second.toCharArray()) {
			if(characterCount.containsKey(c)) {
				if(characterCount.get(c) != 0)
					characterCount.put(c, characterCount.get(c)-1);
				else
					return false;
			}
			else
				return false;
		}
		
		Collection<Integer> c = characterCount.values();
		Iterator<Integer> it = c.iterator();
		while(it.hasNext()) {
			if(it.next()!=0)
				return false;
		}
		return true;
	}
	
	public static int fibonacci(int nth) {
		int i=0;
		int j=1;
		int k = -1;
		if(nth == 0)
			return 0;
		if(nth == 1)
			return 1;
		nth = nth-2;
		while(nth>=0) {
			k = i + j;
			i = j;
			j = k;
			nth--;
		}
		return k;
	}
	
	public static int iterativeFact(int n) {
		int prod = 1;
		if(n==0 || n==1) {
			return 1;
		}
		for(int i=1;i<=n;i++) {
			prod *= i;
		}
		return prod;
	}
	
	public static int recurFact(int n) {
		if(n == 0 || n==1) {
			return 1;
		}
		else {
			return (n*recurFact(n-1));
		}
	}
	
	public static void removeDuplicates(int[] arr) {
		Set<Integer> unique = new HashSet<Integer>();
		for(int a:arr) {
			unique.add(a);
		}
		
		System.out.print(unique.toString());
	}
	
	public static void print2D(int [][]arr) {
		int n = 5;
		for(int i=0,j=n-1;i<n && j>=0;i++,j--) {
			//go right
			for(int l=i;l<j;l++) {
				System.out.print(arr[i][l]+" ");
			}
			
			// go down
			for(int l=i;l<n;l++) {
				System.out.print(arr[l][j]+" ");
			}
			
			//go left
			for(int l=j;l>=i;l--) {
				System.out.print(arr[j][l]+" ");
			}
			
			//go up
			for(int l=j;l>=i;l--) {
				System.out.print(arr[l][i]+" ");
			}
		}
	}
	
	public static String reverse(String str) {
		StringBuffer s = new StringBuffer();
		int len = str.length();
		len--;
		while(len>=0) {
			s.append(str.charAt(len));
			len--;
		}
		return s.toString();
	}
	
	public static String[] words(String sentence) {
		return sentence.split(" ");
	}
}

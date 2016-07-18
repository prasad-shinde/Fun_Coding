package dynamicprogramming.simplified;

/**
 * @author Prasad
 * Edit Distance :
 * The minimum edit distance between 2 strings A and B can be defined as :
 * The minimum number of insert,delete or substitution to be made into either
 * of the string to transform into the other.
 * 
 * Subproblem : at each stage find the edit distance of two prefix's of string.
 * There could be 3 case while considering the sub-problem E(i,j) :
 * case 1 : A[i] : - and B[i] : U -> we have to insert U in a to make a match, cost : 1
 * case 2 : A[i] : W and B[i] : - -> we have to delete W from A to make a match, cost : 1
 * case 3 : A[i] : N and B[i] : U -> there can be 2 sub cases
 * 										i)  if characters match, cost : 0
 * 										ii) if characters don't match, cost : 1
 * 
 * Recurrance :
 * 
 * E(i,j) = Min{ 1 + E(i-1,j), 1 + E(i,j-1), E(i-1,j-1) + diff(i,j)}
 * 
 * Application : Bio informatics : comparing DNA strings
 * 
 * Similar Questions : Longest common subsequence, Longest palindromic subsequence
 */
public class EditDistance {
	
	public static int editDistance(String a,String b) {
		int[][] E = new int[a.length()+1][b.length()+1];
		
		//base case
		for(int i=0;i<a.length();i++) {
			E[i][0] = i;
		}
		for(int j=0;j<b.length();j++) {
			E[0][j] = j;
		}
		
		for(int i=1;i<=a.length();i++) {
			for(int j=1;j<=b.length();j++) {
				E[i][j] = Math.min(1 + E[i-1][j], Math.min(1 + E[i][j-1], diff(a,b,i,j) + E[i-1][j-1]));
			}
		}
		
		return E[a.length()][b.length()];
	}
	
	private static int diff(String a,String b,int i,int j) {
		if(a.charAt(i-1) == b.charAt(j-1))
			return 0;
		else
			return 1;
	}
	
	public static void main(String[] args) {
		System.out.print(editDistance("exponential","polynomial"));
	}
}

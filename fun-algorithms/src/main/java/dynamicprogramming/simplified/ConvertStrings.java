package dynamicprogramming.simplified;

public class ConvertStrings {
	
	public static int[] distance;
	public static int[] direction;
	
	public static int minMoveCountUtil(String a,String b) {
		if(a.equals(b))
			return 0;
		
		int moves = 0;
		
		StringBuffer s1 = new StringBuffer(a);
		StringBuffer s2 = new StringBuffer(b);
		distance = new int[a.length()];
		direction = new int[a.length()];
		
		while(!s1.equals(s2)) {
			calculateDistance(s1.toString(), s2.toString());
			int minCost = minCostIndex();
			int min = Integer.MAX_VALUE;
			for(int i=0;i<distance.length;i++) {
				if(distance[i] == 0 || distance[i] > minCost)
					continue;
				
				int index = distance[i];
				
				StringBuffer temp1 = new StringBuffer(s1.toString());
				StringBuffer temp2 = new StringBuffer(s2.toString());
				StringBuffer m1 = temp1,m2= temp2;
				
				int t1,t2,t3,r1,r2,r3;
				
				// swap s1 and its left char
				swap(temp1,index,index-1 < 0? (a.length() + (index-1)): index-1);
				t1 = 1 + minMoveCountUtil(temp1.toString(),temp2.toString());
				if(min > t1) {
					min = t1;
					m1 = temp1;
					m2 = temp2;
				}
				
				
				temp1 = new StringBuffer(s1.toString());
				temp2 = new StringBuffer(s2.toString());
				// swap s2 and its left char
				swap(temp2,index,index-1 < 0? (a.length() + (index-1)): index-1);
				t2 = 1 + minMoveCountUtil(temp1.toString(),temp2.toString());
				if(min > t2) {
					min = t2;
					m1 = temp1;
					m2 = temp2;
				}
				
				temp1 = new StringBuffer(s1.toString());
				temp2 = new StringBuffer(s2.toString());
				// swap s1 & s2 and its left char
				swap(temp1,index,index-1 < 0? (a.length() + (index-1)): index-1);
				swap(temp2,index,index-1 < 0? (a.length() + (index-1)): index-1);
				t3 = 2 + minMoveCountUtil(temp1.toString(),temp2.toString());
				if(min > t3) {
					min = t3;
					m1 = temp1;
					m2 = temp2;
				}
				
				/////////////////////////////
				// swap s1 and its right char
				temp1 = new StringBuffer(s1.toString());
				temp2 = new StringBuffer(s2.toString());
				swap(temp1,index,index+1 % a.length());
				r1 = 1 + minMoveCountUtil(temp1.toString(),temp2.toString());
				if(min > r1) {
					min = r1;
					m1 = temp1;
					m2 = temp2;
				}
				
				
				temp1 = new StringBuffer(s1.toString());
				temp2 = new StringBuffer(s2.toString());
				// swap s2 and its left char
				swap(temp2,index,index+1 % a.length());
				r2 = 1 + minMoveCountUtil(temp1.toString(),temp2.toString());
				if(min > r2) {
					min = r2;
					m1 = temp1;
					m2 = temp2;
				}
				
				temp1 = new StringBuffer(s1.toString());
				temp2 = new StringBuffer(s2.toString());
				// swap s1 & s2 and its left char
				swap(temp1,index,index+1 % a.length());
				swap(temp2,index,index+1 % a.length());
				r3 = 2 + minMoveCountUtil(temp1.toString(),temp2.toString());
				if(min > r3) {
					min = r3;
					m1 = temp1;
					m2 = temp2;
				}
				moves += min;
				s1 = m1;
				s2 = m2;
				// min moves update
				//moves += min(t1,t2,t3,r1,r2,r3);
			}
			
		}
		return moves;
	}
	
	private static int min(int ...arr) {
		int min = Integer.MAX_VALUE;
		
		for(int a:arr) {
			if(min > a)
				min = a;
		}
		
		return min;
	}
	
	private static void swap(StringBuffer s1,int i,int j) {
		char temp = s1.charAt(i);
		s1.setCharAt(i, s1.charAt(j));
		s1.setCharAt(j,temp);
	}
	
	public static int minMoveCount(String a,String b) {
		int moves = 0;
		StringBuffer s1 = new StringBuffer(a);
		StringBuffer s2 = new StringBuffer(b);
		distance = new int[a.length()];
		direction = new int[a.length()];
		
		while(editDistance(s1.toString(), s2.toString()) != 0) {
			calculateDistance(s1.toString(), s2.toString());
			int index = minCostIndex();

			if(direction[index] == -1) {		// go left
				int i = index;
				while(s1.charAt(i) != s2.charAt(i)) {
					if(s2.charAt(i) == s2.charAt(((i-1) < 0)?a.length()-1:i-1)) {
						char temp = s1.charAt(i);
						s1.setCharAt(i, s1.charAt(((i-1) < 0)?a.length()-1:i-1));
						s1.setCharAt(((i-1) < 0)?a.length()-1:i-1, temp);
						moves++;
					} else {
						char temp;
						boolean flag = false;
						if(s2.charAt(((i-1) < 0)?a.length()-1:i-1) == s1.charAt(((i-1) < 0)?a.length()-1:i-1))
							flag = true;
						
						if(s2.charAt(i) != s2.charAt(((i-1) < 0)?a.length()-1:i-1)) {
							temp = s2.charAt(i);
							s2.setCharAt(i, s2.charAt(((i-1) < 0)?a.length()-1:i-1));
							s2.setCharAt(((i-1) < 0)?a.length()-1:i-1, temp);
							moves++;
						}
						
						if(flag) {
							temp = s1.charAt(i);
							s1.setCharAt(i, s1.charAt(((i-1) < 0)?a.length()-1:i-1));
							s1.setCharAt(((i-1) < 0)?a.length()-1:i-1, temp);
							moves++;
						}
					}
					i--;
					if(i < 0)
						i = a.length()-1;
				}
			} else if(direction[index] == 1) {	// go right
				int i = index;
				while(s1.charAt(Math.abs(i % a.length())) != s2.charAt(Math.abs(i % a.length()))) {
					if(s2.charAt(Math.abs(i % a.length())) == s2.charAt(Math.abs((i+1)% a.length()))) {
						char temp = s1.charAt(Math.abs(i% a.length()));
						s1.setCharAt(Math.abs(i % a.length()), s1.charAt(Math.abs((i+1) % a.length())));
						s1.setCharAt(Math.abs((i+1) % a.length()), temp);
						moves++;
					} else {
						char temp;
						boolean flag = false;
						if(s2.charAt(Math.abs((i+1) % a.length())) == s1.charAt(Math.abs((i+1) % a.length()))) {
							flag = true;
						}
							
						if(s2.charAt(Math.abs(i % a.length())) != s2.charAt(Math.abs((i+1) % a.length()))) {
							temp = s2.charAt(Math.abs(i % a.length()));
							s2.setCharAt(Math.abs(i % a.length()), s2.charAt(Math.abs((i+1)) % a.length()));
							s2.setCharAt(Math.abs((i+1) % a.length()), temp);
							moves++;
						}
						
						if(flag) {
							temp = s1.charAt(Math.abs(i % a.length()));
							s1.setCharAt(Math.abs(i % a.length()), s1.charAt(Math.abs((i+1) % a.length())));
							s1.setCharAt(Math.abs((i+1) % a.length()), temp);
							moves++;
						}
					}
					i++;
					if(i >= a.length())
						i=0;
				}
				
			}
		}
		
		return moves;
	}
	
	private static int minCostIndex() {
		int index = -1;
		int min = Integer.MAX_VALUE;
		int i=0;
		for(int cost:distance) {
			if(cost == 0) {
				i++;
				continue;
			}
			if(min > cost) {
				min = cost;
				index = i;
			}
			i++;
		}
		return index;
	}
	
	public static int editDistance(String a,String b) {
		int count = 0;
		for(int i=0;i<a.length();i++) {
			if(a.charAt(i) != b.charAt(i))
				count++;
		}
		return count;
	}
	
	public static void calculateDistance(String a,String b) {
		distance = new int[a.length()];
		direction = new int[a.length()];
		int[] dist = new int[2];
		for(int i=0;i<a.length();i++) {
			if(a.charAt(i) != b.charAt(i)) {
				dist = minCostPath(a,b,i);
				distance[i] = dist[0];
				direction[i] = dist[1];
			} else {
				distance[i] = 0;
				direction[i] = 0;
			}
		}
	}
	
	public static int[] minCostPath(String a,String b,int index) {
		int i = 0;
		int direction = 0;
		int[] costAndDirection = new int[2];
		while(i != a.length()) {
			if(a.charAt(index) == b.charAt((index-i) < 0 ?(a.length() - Math.abs(index-i)) : (index-i))) {
				direction = -1;
				break;
			}
			if(a.charAt(index) == b.charAt((index+i) % a.length())) {
				direction = 1;
				break;
			}
			i++;
		}
		costAndDirection[0] = i;
		costAndDirection[1] = (i == 0)?0:direction;
		return costAndDirection;
	}
	
	/*
	public static int minMoves(String a,String b) {
		int[][] dp = new int[a.length()+1][a.length()+1];
		for(int i = 0;i<= a.length();i++) {
			for(int j = 0;j<= a.length();j++) {
				dp[i][j] = -1;
			}
		}
		return minMoves(a.toCharArray(), b.toCharArray(),0,a.length()-1);
	}
	
	public static int minMoves(String a,String b,int i,int j,int[][] dp) {
		if(i>j)
			return 0;
		
		if(a.equals(b))
			return 0;
		
		if(dp[i][j] != -1)
			return dp[i][j];
			
		while(i < a.length()-1 && a.charAt(i) == b.charAt(i)) {
			i++;
		}
			
		
		while(j>=0 && a.charAt(j) == b.charAt(j)) {
			j--;
		}
			

		int len = a.length();
		
		if(a.charAt(0) == b.charAt(len-1) && a.charAt(len-1) == b.charAt(0)) {
			a = swap(a,0,len-1);
			return 1 + minMoves(a, b, i, j,dp);
		} else {
			dp[i][j] = 1 + Math.min(minMoves(swap(a,i,i+1), b, i, j,dp), minMoves(a, swap(b,i,i+1), i, j,dp));
			return dp[i][j];
		}
	}
	
	private static int minMoves(char[] a,char[] b,int i,int j) {
		if(equals(a,b))
			return 0;
		
		if(i>j)
			return 0;
		
		int min = 0;
		int len = a.length;
		if(a[0] == b[len-1] && a[len-1] == b[0]) {
			swap(a,0,len-1);
			min++;
		}
		
		while(!equals(a, b)) {
			while(i<j) {
				while(i<a.length && a[i] == b[i])
					i++;
				while(j>=0 && a[j] == b[j])
					j--;
				
				if(i>j)
					break;
				
				if(i+1 != len)
					swap(a,i,i+1);
				int min1 = minMoves(a,b,i+1,j);
				if(i+1 != len)
					swap(a,i,i+1);					// backtrack
				
				if(i+1 != len)
					swap(b,i,i+1);
				int min2 = minMoves(a,b,i+1,j);
				
				if(min2>min1) {
					if(i+1 != len)
						swap(b,i,i+1);
					min += min1;
					if(i+1 != len)
						swap(a,i,i+1);				// optimal solution
				} else {
					min += min2;
				}
			}
			i = 0;
			j = a.length;
		}
		
		return min;
	}
	
	private static boolean equals(char[] a,char [] b) {
		for(int i =0;i<a.length;i++)
			if(a[i] != b[i])
				return false;
		return true;
	}
	
	private static String swap(String a,int i,int j) {
		if(i<=0 || i>=a.length() || j<=0 || j>=a.length())
			return a;
		StringBuffer sb = new StringBuffer(a);
		char temp = sb.charAt(i);
		sb.setCharAt(i, sb.charAt(j));
		sb.setCharAt(j, temp);
		return sb.toString();
	}
	
	private static void swap(char[] a,int i,int j) {
		if(i<0 || i>=a.length || j<0 || j>=a.length)
			return;
		
		char temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	*/
	public static void main(String[] args) {
		System.out.println(minMoveCount("aab", "baa"));
		System.out.println(minMoveCount("disha", "ahsid"));
		System.out.println(minMoveCount("perfect", "peercft"));
		System.out.println(minMoveCount("random", "danmor"));
		System.out.println(minMoveCount("fuckme", "uckfme"));
	}
}

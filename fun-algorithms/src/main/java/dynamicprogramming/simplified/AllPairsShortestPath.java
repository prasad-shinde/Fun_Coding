package dynamicprogramming.simplified;

/**
 * @author Prasad
 * All Pairs Shortest Path :
 * This helps us in calculating the shortest distance between any pair of vertices.
 * 
 * Possible Solutions :
 * 1. Use the singles source shortest path problem and run it over all the vertices
 * This would take O(V^2E) time.
 * 
 * 2. Floyd Warshal Algorithm :
 * This what we will implement. It takes O(V^3) time. 
 * 
 * Graph G, V = {1,2,3,4...N} ,E = {set of all (u,v) edges in vertices}
 * 
 * Subproblems :
 * let dist(i,j,k) = dist of path from i to j that uses the vertices = {1,2,3,...k}
 * 
 * Base Case : 
 * dist(i,j,0) = cost(i,j) if (i,j) belongs to E else INFINITY
 * 
 * Recurrence :
 * dist(i,j,k) = Min{dist(i,k,k-1) + dist(k,j,k-1),dist(i,j,k-1)}
 * 				 Where k = new vertex included in the set
 * 				 We also have all the required subproblems solved
 * 
 * References : http://www.cs.berkeley.edu/~vazirani/algorithms/chap6.pdf page 187 or 19 in pdf
 * 
 */
public class AllPairsShortestPath {
	private int size;
	private int[][] adjMatrix;	// vertices are numbered V = {1,2,3,....size}
	private int[][][] dist;
	private boolean[][][] pathIncludesK;	// this can be converted to 2-D array
	
	public void allPairsShortestPath() {
		
		// base case - where only way between pair of vertices is the direct edge between them
		for(int i=1;i<=size;i++) {
			for(int j=1;j<=size;j++) {
				dist[i][j][0] = adjMatrix[i][j];
				if(adjMatrix[i][j] == Integer.MAX_VALUE)
					pathIncludesK[i][j][0] = false;
				else
					pathIncludesK[i][j][0] = true;
			}
		}
		
		
		for(int k=1;k<=size;k++) {
			for(int i=1;i<=size;i++) {
				for(int j=1;j<=size;j++) {
					if(dist[i][k][k-1] + dist[k][j][k-1] < dist[i][j][k-1]) {
						dist[i][j][k] = dist[i][k][k-1] + dist[k][j][k-1];
						pathIncludesK[i][j][k] = true;
					} else {
						dist[i][j][k] = dist[i][j][k-1];
						pathIncludesK[i][j][k] = false;
					}
				}
			}
		}
	}
	
	public String printPath(int i,int j) {
		return printPath(i, j, size);
	}
	
	private String printPath(int i,int j,int size) {
		if(size == 0)
			return " ";
		else {
			for(int k=0;k<=size;k++) {
				if(pathIncludesK[i][j][k]) {
					return printPath(i,k,k-1) + " " + k + " " + printPath(k,j,k-1);
				}
			}
			return "No Path exists";
		}
	}
}

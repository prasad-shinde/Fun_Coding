package dynamicprogramming.simplified;

/**
 * @author Prasad
 * Shortest path : The problem of solving shortest path is central to dynamic programming.
 * Most of the optimal solutions in dynamic programming are the shortest paths between the nodes 
 * in the DAG, where the nodes are the subproblems.
 * 
 * Usually the nodes in graph are arranged from left to right in a straight line where edges
 * go from left to right. We start solving subproblems from left to right. This helps us solve subproblems with 
 * dependencies before proceeding to the next sub problem. The edges between nodes are the dependencies
 * 
 * Shortest Path Bellman Ford Algorithm :
 * We can solve single source shortest path problem using bellman ford algorihtm.
 * The problem is to find shortest way to reach different destinations from a single source s.
 * 
 * dist(v) = shortest path from s to v
 * 
 * Recurrence :
 * dist(v) = min{dist(u) + cost(u,v)} where (u,v) belongs to E
 * prev(v) = u_min
 * Base Case:
 * dist(s) = 0
 * 
 * Shortest Reliable Path :
 * sometimes finding the path with minimum number of hops/edges is really important in transmitting data. because there
 * are chances of loss of data with increasing number of nodes between them. The above algorithm cannot be used in this case
 * as it does not mention enough information about the number of hops
 * 
 * dist(v,i) = distance of shortest path from s to v using at most i edges
 * 
 * Recurrence :
 * dist(v,i) = min{dist(u,i-1) + cost(u,v)} where (u,v) belongs to E
 * 
 * Base case :
 * dist(s,i) = 0
 */
public class ShortestPath {
	private int[][] adjMatrix;
	private int size;
	
	public ShortestPath() {
		//adjMatrix = {}
	}
	
	public ShortestPath(int[][] mat,int sz) {
		adjMatrix = mat;
		size = sz;
	}
	
	/**
	 * @param u
	 * @param v
	 * @return
	 * 
	 * Bellman Ford :
	 * 
	 * dist(v) = shortest path from s to v
	 * 
	 * Recurrence :
	 * dist(v) = min{dist(u) + cost(u,v)} where (u,v) belongs to E
	 * prev(v) = u_min
	 * 
	 * Base Case:
	 * dist(s) = 0
	 * 
	 */
	public int shortestPathBF(int u,int v) {
		int[] dist = new int[size];
		int[] prev = new int[size];
		
		for(int i=0;i<size;i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		dist[u] = 0;
		prev[u] = -1;
		
		for(int i=0;i<size;i++) {
			for(int j = 0;j<size;j++) {
				if(i!=j && adjMatrix[i][j]!=0) {
					int temp = dist[i];
					dist[i] = Math.min(dist[i], dist[j] + adjMatrix[i][j]);
					if(dist[i]!=temp)
						prev[i] = j;
				}
			}
		}
		
		return dist[v];
	}
	
	/**
	 * @param u
	 * @param v
	 * @param k
	 * @return
	 * 
	 * Shortest reliable path from u to v that uses atmost k edges
	 * dist(v,i) = distance of shortest path from s to v using at most i edges
	 * 
	 * Recurrence :
	 * dist(v,i) = min{dist(u,i-1) + cost(u,v)} where (u,v) belongs to E
	 * 
	 * Base case :
	 * dist(s,i) = 0
	 */
	public int shortestReliablePath(int u,int v,int k) {
		int[][] dist = new int[size][k];
		int[][] prev = new int[size][k];
		
		for(int i=0;i<k;i++) {
			dist[u][i] = 0;
			prev[u][i] = -1;
		}
		
		for(int i = 1;i<k;i++) {
			for(int j=0;j<size;j++) {
				dist[j][i] = Integer.MAX_VALUE;
				prev[j][i] = -1;
				for(int p=0;p<size;p++) {
					if(i!=u) {
						if(dist[j][i] > dist[p][i-1] + adjMatrix[j][p]) {
							dist[j][i] = dist[p][i-1] + adjMatrix[j][p];
							prev[j][i] = p;
						}
					}
				}
			}
		}
		
		return dist[v-1][k-1];
	}
}

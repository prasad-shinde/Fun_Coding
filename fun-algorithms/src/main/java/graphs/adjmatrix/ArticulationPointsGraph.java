package graphs.adjmatrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Articulation Points : 
 * A articulation point in a graph is a vertex whose removal disconnects the graphs into 2 separate components.
 * 
 * Algorithms : 
 * 1 . Brute Force :
 *	Algo :  
 * 		1. Remove every vertex and check if the graph is still connected.
 * 		Time Complexity : V*(V+E) = O(V^2+VE)
 * 
 * 2. DFS based approach : 
 * Conditions for a vertex to be a articulation point.
 * 
 * 1. The root of DFS tree is a AP if it has more then 1 children
 * 2. Non root vertex is a AP if it has the following properties :
 * 		A vertex v which is not the root of the DFS tree is a AP if v has a child w such that no back edge 
 * 		starting in the subtree of w reaches an ancestor of v.
 * 
 * References :
 * 1. Biconnectivity : https://www.cs.purdue.edu/homes/ayg/CS251/slides/chap9d.pdf
 * 
 * @author Prasad
 *
 */
public class ArticulationPointsGraph {
	private int[][] adjMatrix;
	private int[] parent;
	private boolean[] visited;
	private int[] depth;
	private int[] low;
	private int size;
	private List<Integer> cutVertices;
	
	private List<Integer> adjacentTo(int v) {
		List<Integer> adjacent = new ArrayList<Integer>();
		
		for(int i=0;i<size;i++) {
			if(adjMatrix[v][i]!=0)
				adjacent.add(i);
		}
		return adjacent;
	}
	
	public void articulationPoints(int vertex,int depth) {
		int childCount = 0;
		boolean isAP = false;
		
		visited[vertex] = true;
		this.depth[vertex] = depth;
		this.low[vertex] = depth;
		
		for(int nv:adjacentTo(vertex)) {
			if(!visited[nv]) {
				parent[nv] = vertex;
				articulationPoints(nv, depth+1);
				childCount++;
				if(low[nv]>=this.depth[vertex])
					isAP = true;
				this.low[vertex] = Math.min(this.low[vertex], this.low[nv]); 		// need a good proof of this
			} else if(nv != parent[vertex])
				this.low[vertex] = Math.min(this.low[vertex], this.depth[nv]);		// need a good proof of this
		}
		
		if((parent[vertex]!=-1 && isAP) || (parent[vertex] == -1 && childCount>1)) {	// if its a root => then separate condition
			System.out.print(vertex+" ");
			cutVertices.add(vertex);
		}
	}
}

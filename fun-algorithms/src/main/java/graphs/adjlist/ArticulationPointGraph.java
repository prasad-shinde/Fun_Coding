package graphs.adjlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public class ArticulationPointGraph {
	List<List<Integer>> adjList;
	int size;
	int time;

	public ArticulationPointGraph(int size) {
		adjList = new ArrayList<List<Integer>>();
		this.size = size;
		for (int i = 0; i < size; i++) {
			adjList.add(new ArrayList<Integer>());
		}
		time = 0;
	}

	/**
	 * Note : its a undirected graph hence the implementation of the method
	 * 
	 * @param u
	 * @param v
	 */
	public void addEdge(int u, int v) {
		adjList.get(u).add(v);
		adjList.get(v).add(u);
	}

	private List<Integer> adjacentTo(int u) {
		return adjList.get(u);
	}

	public boolean hasArticulationPoints() {
		return articulationPoints().size() != 0;
	}

	public Set<Integer> articulationPoints() {
		time = 0;
		Set<Integer> aps = new HashSet<Integer>();
		boolean[] visited = new boolean[size];
		int[] low = new int[size];
		int[] disc = new int[size];
		int[] parent = new int[size];

		Arrays.fill(visited, false);
		Arrays.fill(parent, -1);

		hasAPUtil(0, visited, parent, low, disc, aps);
		return aps;
	}

	private void hasAPUtil(int u, boolean[] visited, int[] parent, int[] low,
			int[] disc, Set<Integer> aps) {
		int children = 0;
		time++;
		low[u] = disc[u] = time;
		visited[u] = true;

		for (int v : adjacentTo(u)) {
			if (!visited[v]) {
				children++;
				parent[v] = u;
				hasAPUtil(v, visited, parent, low, disc, aps);
				low[u] = Math.min(low[u], low[v]);

				if (parent[u] == -1 && children > 1) {
					aps.add(u);
				}
				if (parent[u] != -1 && (disc[u] <= low[v])) {
					aps.add(u);
				}
			} else {
				// v is not u's parent
				if (v != parent[u]) {
					low[u] = Math.min(low[u], disc[v]);
				}
			}
		}
	}
	
	private void hasAp(int u,boolean[] visited,int[] parent,int[] low,int[] disc,Set<Integer> aps) {
		int children = 0;
		time++;
		low[u] = disc[u] = time;
		visited[u] = true;
		
		for(int v:adjacentTo(u)) {
			if(!visited[v]) {
				parent[v] = u;
				children++;
				hasAp(v,visited,parent,low,disc,aps);
				low[u] = Math.min(low[u], low[v]);
				
				if(parent[u] == -1 && children >1)
					aps.add(u);
				if(parent[u]!= -1 && disc[u] <= low[v])
					aps.add(u);
			} else {
				if(v != parent[u]) {
					low[u] = Math.min(low[u], disc[v]);
				}
			}
		}
	}

	public static void main(String[] args) {

	}
}

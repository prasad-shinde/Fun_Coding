package graphs.adjmatrix;

import java.util.HashSet;
import java.util.Set;

import set.DisjointSet;

public class GraphCycle {
	private Set<Integer> vertices;
	private int[][] adjMatrix;
	private int numOfVertices;
	int n;
	
	public GraphCycle(int n) {
		this.n = n;
		numOfVertices = n;
		adjMatrix = new int[n][n];
		vertices = new HashSet<Integer>();
		initialize();
	}
	
	private void initialize() {
		for(int i = 0;i<numOfVertices;i++) {
			vertices.add(i);
			for(int j = 0;j<numOfVertices;j++) {
				if(i==j)
					adjMatrix[i][j] = 0;
				else
					adjMatrix[i][j] = 1;// change to infinity
			}
		}
	}
	
	/**
	 * Directed Graph :
	 * There are better ways to check if there is a cycle in an undirected graph. This is more simple. 
	 * You can use a stack and keep the vertices which are in the current DFS tree's recursion stack on it.
	 * If you visit any vertex on the recursion stack then there is a cycle. Once you come out of recursion remove the vertex.
	 * 
	 * @return
	 */
	public boolean hasCycle() {
		boolean[] isVisited = new boolean[n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				isVisited[j] = false;
			}
			if(isCyclicUtil(i,isVisited))
				return true;
		}
		return false;
	}
	
	private boolean isCyclicUtil(int node,boolean[] isVisited) {
		if(isVisited[node])
			return true;
		isVisited[node] = true;
		for(int i=0;i<n;i++) {
			if(adjMatrix[node][i] !=0) {
				if(isCyclicUtil(i, isVisited))
					return true;
			}
		}
		
		return false;
	}
	
	/** 
	 * Undirected Graph :
	 * This algorithm uses the union find algorithm {@link DisjointSet} to check if the
	 * element belongs to the same set.
	 * @return boolean : if the graph contains cycle or not
	 */
	public boolean hasCycleUF() {
		DisjointSet djSet = new DisjointSet(n);
		for(int i = 0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(i!=j && adjMatrix[i][j]!=0) {
					if(djSet.belongToSameSet(i, j))
						return true;
					djSet.union(i, j);
				}
			}
		}
		
		return false;
	}
}

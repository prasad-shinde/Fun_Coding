package graphs.adjmatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author Prasad
 * Strongly connected components : 
 * In the mathematical theory of directed graphs, a graph is said to be strongly connected if every vertex is reachable from every other vertex. 
 * The strongly connected components of an arbitrary directed graph form a partition into subgraphs that are themselves strongly connected.
 * 
 * Algorithms for finding SCC :
 * 1. Kosarajus's Algorithm : http://en.wikipedia.org/wiki/Kosaraju%27s_algorithm
 *  	Algorithm : 
 *  	1. Do a dfs traversal of graph and add vertex on the stack after visiting all the neighbors
 *  	2. Take the transpose of current graph(i.e. reverse all the edge directions)
 *  	3. While the stack is not empty
 *  		3.1 pop element from the stack and do a dfs in the transpose graph starting from this vertex and visiting all the reachable vertices.
 *  		3.2 All the visited vertices in this dfs correspond to one SCC
 *  
 * 2. Tarjan's SCC Algorithm : 
 */
public class GraphSCC {
	private int[][] adjMatrix = {{0,0,1,1,0},
								 {1,0,0,0,0},
								 {0,1,0,0,0},
								 {0,0,0,0,1},
								 {0,0,0,0,0}};
	int size = 5;
	
	public GraphSCC() {
	}
	
	public int[][] transpose() {
		int[][] transpose = new int[size][size];
		
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				transpose[j][i] = adjMatrix[i][j];
			}
		}
		
		return transpose;
	}
	
	public void sccKosaraju() {
		Stack<Integer> stack = new Stack<Integer>();
		boolean[] visited = new boolean[size];
		int[][] tGraph = transpose();
		List<List<Integer>> scc = new ArrayList<List<Integer>>();

		Arrays.fill(visited, false);
		for(int i = 0;i<size;i++)
			if(!visited[i])
				dfsUtil(i, stack, visited);
		
		Arrays.fill(visited, false);
		while(!stack.isEmpty()) {
			int u = stack.pop();
			if(!visited[u]) {
				List<Integer> connectedComp = new ArrayList<Integer>();
				dfsUtil(u,visited,tGraph,connectedComp);
				scc.add(connectedComp);
			}
		}
		
		System.out.println("Strongly connected components");
		for(List<Integer> cc : scc) {
			for(int i:cc) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
	
	private void dfsUtil(int u,boolean[] visited,int[][] matrix,List<Integer> connectedComps) {
		if(visited[u])
			return;
		
		visited[u] = true;
		connectedComps.add(u);
		
		for(int v:neighbours(u,matrix)) {
			if(!visited[v])
				dfsUtil(v,visited,matrix,connectedComps);
		}
	}
	
	private void dfsUtil(int u,Stack<Integer> stack,boolean[] visited) {
		if(visited[u])
			return;
		
		visited[u] = true;
		
		for(int v:neighbours(u)) {
			if(!visited[v])
				dfsUtil(v, stack, visited);
		}
		
		stack.add(u);
	}
	
	private List<Integer> neighbours(int u) {
		List<Integer> neighbours = new ArrayList<Integer>();
		
		for(int i = 0;i<size;i++) {
			if(adjMatrix[u][i] !=0)
				neighbours.add(i);
		}
		return neighbours;
	}
	
	private List<Integer> neighbours(int u,int[][] matrix) {
		List<Integer> neighbours = new ArrayList<Integer>();
		
		for(int i = 0;i<size;i++) {
			if(matrix[u][i] !=0)
				neighbours.add(i);
		}
		return neighbours;
	}
	
	public static void main(String[] args) {
		GraphSCC g = new GraphSCC();
		g.sccKosaraju();
	}
}

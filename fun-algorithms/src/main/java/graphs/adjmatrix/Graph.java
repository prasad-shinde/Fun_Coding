package graphs.adjmatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Stack;


/**
 * @author Prasad
 * 
 * Graphs can be represented in two main ways :
 * 1. Adjacency List : this is a compact way of representing graphs. It does not waste any extra space.
 * It is mostly used for representing sparse graphs where e << v^2
 * 
 * 2. Adjacency matrix : this is used mostly for representing dense graphs where e ~= v^2
 * O(1) to find the edge wt for 2 vertices
 */
public class Graph {
	private Set<Integer> vertices;
	private int[][] adjMatrix;
	private int numOfVertices;
	
	public Graph(int n) {
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
	
	private int root() {
		return 0;
	}
	
	public void addEdge(int u,int v,int weight) {
		if(u>=numOfVertices || v>=numOfVertices)
			throw new NoSuchElementException("Invalid vertex of edge, should be less then " + numOfVertices);
		else {
			adjMatrix[u][v] = weight;
			adjMatrix[v][u] = weight;
		}
	}
	
	/**
	 * @param vertex
	 * @return
	 * TC = O(|vertices|)
	 */
	private Set<Integer> neighboursOf(int vertex) {
		Set<Integer> neighbours = new HashSet<Integer>();
		for(int i=0;i<numOfVertices;i++) {
			if(i != vertex && adjMatrix[vertex][i] != Integer.MAX_VALUE) {
				neighbours.add(i);
			}
		}
		return neighbours;
	}
	
	/*************************  DFS *************************************/
	public String dfs() {
		StringBuffer path = new StringBuffer();
		System.out.print("\n DFS: ");
		boolean[] visited = new boolean[numOfVertices];
		for(int i=0;i<numOfVertices;i++) {
			visited[i] = false;
		} 
		
		for(Integer u:vertices) {
			if(!visited[u])
				dfs(u,visited,path);
		}
		return path.substring(0, path.length()-1);
	}
	
	public void dfs(int vertex,boolean[] visited,StringBuffer path) {
		if(visited[vertex])	// if vertex is already visited then return
			return;
		visited[vertex] = true;
		path.append(vertex+",");
		System.out.print(vertex + " ");
		Set<Integer> neighbours = neighboursOf(vertex);
		for(Integer neighbour:neighbours) {
			dfs(neighbour,visited,path);
		}
	}
	
	/********************** BFS **************************************/
	public String bfs() {
		StringBuffer path = new StringBuffer();
		int root = root();
		boolean[] visited = new boolean[numOfVertices];
		for(int i=0;i<numOfVertices;i++) {
			visited[i] = false;
		}
		
		List<Integer> queue = new ArrayList<Integer>();
		queue.add(root);
		while(queue.size()>0) {
			int current = queue.remove(0);
			if(visited[current])
				continue;
			visited[current] = true;
			path.append(current+",");
			Set<Integer> neighbours = neighboursOf(current);
			for(Integer neigh:neighbours) {
				if(!visited[neigh]) {
					queue.add(neigh);
				}
			}
		}
		return path.substring(0, path.length()-1);
	}
	
	/*********************** shortest path - Djikstra ***********************/
	public int shortestPath(int source,int dest) {
		int[] dist = new int[numOfVertices];
		int[] prev = new int[numOfVertices];
		Set<Integer> unvisitedVertices = new HashSet<Integer>();
		unvisitedVertices.addAll(vertices);
		
		for(int i=0;i<numOfVertices;i++) {
			dist[i] = Integer.MAX_VALUE;
			prev[i] = -1;
		}
		dist[source] = 0;
		
		while(unvisitedVertices.size()!=0) {
			int minDistVertex = minDistanceVertex(unvisitedVertices, dist);
			unvisitedVertices.remove(minDistVertex);
			Set<Integer> neighbours = neighboursOf(minDistVertex);
			for(Integer neighbour:neighbours) {
				if(dist[neighbour] > (dist[minDistVertex]) + adjMatrix[minDistVertex][neighbour]) {
					dist[neighbour] = (dist[minDistVertex]) + adjMatrix[minDistVertex][neighbour];
					prev[neighbour] = minDistVertex;
				}
			}
		}
		
		return dist[dest];
	}
	
	public int minDistanceVertex(Set<Integer> unvisitedVertices,int[] dist) {
		int min = Integer.MAX_VALUE;
		int minDistVertex = -1;
		
		for(Integer vertex:unvisitedVertices) {
			if(dist[vertex] < min) {
				min = dist[vertex];
				minDistVertex = vertex;
			}
		}

		return minDistVertex;
	}
	
	/********************** spanning tree ******************************/
	/*
	 * Algorithm :
	 * 1. create a empty set of vertices
	 * 2. find min weight edge and add its vertices to the set if it does not forms a cycle
	 * 3. continue till the set has all the vertices
	 */
	public List<Integer[]> minimumSpanningTree() {
		boolean[][] usedEdges = new boolean[numOfVertices][numOfVertices];
		List<Integer[]> spanningTree = new ArrayList<Integer[]>();
		Set<Integer> availableVertices = new HashSet<Integer>();
		availableVertices.addAll(vertices);
		for(int i=0;i<numOfVertices;i++) {
			for(int j=0;j<numOfVertices;j++) {
				usedEdges[i][j] = false;
			}
		}
		
		while(availableVertices.size() != 0) {
			Integer[] edge = nextMinWeightEdge(usedEdges, availableVertices);
			availableVertices.remove(edge[0]);
			availableVertices.remove(edge[1]);
			spanningTree.add(edge);
		}
		
		return spanningTree;
	}
	
	private Integer[] nextMinWeightEdge(boolean[][] usedEdges,Set<Integer> availableVertices) {
		Integer[] edge = new Integer[2];
		int min = Integer.MAX_VALUE;
		
		for(Integer u:availableVertices) {
			for(Integer v:vertices) {
				if(u != v) {
					if(min > adjMatrix[u][v]) {
						min = adjMatrix[u][v];
						edge[0] = u;
						edge[1] = v;
					}
				}
			}
		}
		return edge;
	}
	
	/********************** A* Algorithm***********************/
	
	public List<Integer> aStar(int start,int goal) {
		int[] f = new int[numOfVertices];
		int[] g = new int[numOfVertices];
		Map<Integer,Integer> cameFrom = new HashMap<Integer,Integer>(); 
				
		Set<Integer> closedSet = new HashSet<Integer>();
		Set<Integer> openSet = new HashSet<Integer>();
		openSet.add(start);
		g[start] = 0;
		f[start] = g[start] + heuristicCostEstimate(start, goal);
		
		while(openSet.size()!=0) {
			int current = minFScoreVertex(openSet, f);
			if(current == goal) 
				return reconstructPath(cameFrom,goal);
			
			openSet.remove(current);
			closedSet.add(current);
			
			for(Integer neighbor:neighboursOf(current)) {
				if(closedSet.contains(neighbor))
					continue;
				int tentative_g_score = g[current] + adjMatrix[current][neighbor];
				if(!openSet.contains(neighbor) || tentative_g_score < g[neighbor]) {
					cameFrom.put(neighbor, current);
					g[neighbor] = tentative_g_score;
					f[neighbor] = g[neighbor] + heuristicCostEstimate(neighbor, goal);
					
					if(!openSet.contains(neighbor))
						openSet.add(neighbor);
				}
			}
		}
		
		return null;
	}
	
	private List<Integer> reconstructPath(Map<Integer,Integer> cameFrom,int current) {
		List<Integer> path = new ArrayList<Integer>();
		path.add(current);
		
		while(cameFrom.containsKey(current)) {
			current = cameFrom.get(current);
			path.add(current);
		}		
		return path;
	}
	
	private int minFScoreVertex(Set<Integer> openSet,int[] fScore) {
		int min = Integer.MAX_VALUE;
		int vertex = -1;
		
		for(Integer v:openSet) {
			if(min > fScore[v]) {
				min = fScore[v];
				vertex = v;
			}
		}
		return vertex;
	}
	
	/**
	 * @param u
	 * @param v
	 * @return
	 * 
	 * this can be a BFS. which returns the no of edges from u to v
	 * Good Heuristics : http://theory.stanford.edu/~amitp/GameProgramming/Heuristics.html
	 */
	private int heuristicCostEstimate(int u,int v) {
		return 1;
	}
	
	
	/************************* topological sort ****************************/
	public void topologicalSort() {
		/*
		 * 1. Let s be a set of vertices with no incoming edges
		 * 2. L be empty list
		 * 3. while set s is not empty
		 * 	4. pick a vertex v from s
		 * 	5. remove v from s, add it to the tail of L
		 * 	6. for all outgoing edges from v to u
		 * 	7. remove that edge from graph and if u has no incoming edges then add it to s
		 * 8. if graph still has edges -> there is no topological sort
		 * 9. return L which is topological order
		 */
		
		// OR
		
		/*
		 * Algorithm :
		 * 1. Use DFS
		 * 2. When a vertex is finished(i.e. its color turns black), add it to the list
		 * 3. The list in the end contains topologically sorted vertices 
		 */
	}
	
	
	/**
	 * Topological Sorting for DAG's
	 * 
	 *  Note : This wont work if the graph ain't a DAG. Because for cyclic graphs there is no topological order. 
	 */
	public void topoSort() {
		boolean[] visited = new boolean[this.numOfVertices];
		Arrays.fill(visited, false);
		Stack<Integer> topologicalOrder = new Stack<Integer>();
		
		for(int i=0;i<numOfVertices;i++) {
			if(!visited[i])
				dfsUtil(i,visited,topologicalOrder);
		}
		
		System.out.print("\nTopological Order : ");
		while(!topologicalOrder.isEmpty()) {
			System.out.print(topologicalOrder.pop());
		}
	}
	
	private void dfsUtil(int u,boolean[] visited,Stack<Integer> topologicalOrder) {
		if(visited[u])
			return;
		
		visited[u] = true;
		for(Integer neighbour:neighboursOf(u)) {
			if(!visited[neighbour])
				dfsUtil(neighbour, visited, topologicalOrder);
		}
		
		// may be a performance overhead as we insert front and shifting due to that
		topologicalOrder.push(u);
	}
	
	public static void main(String[] args) {
		Graph g = new Graph(5);
		g.dfs();
		System.out.print("\nShortest path : " + g.shortestPath(1, 4));
		
		List<Integer[]> mst = g.minimumSpanningTree();
		for(Integer[] edge:mst) {
			System.out.print("\n"+edge[0]+"->"+edge[1]);
		}
		
		g.topoSort();
	}
}

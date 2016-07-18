package graphs.adjlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Eulerian Path/Circuit :
 * 
 * Eulerian Path : Eulerian path is a path in a graph which starts and ends at the same vertex.
 * 
 * Eulerian Circuit : Eulerian Curcuit is a Eulerian Path which starts and ends on the same vertex.
 * 
 * Follwoing are the conditions for a graph to be Eulerian
 * 
 * Eulerian Cycle :
 * An undirected graph has Eulerian cycle if following two conditions are true.
 * 		a) All vertices with non-zero degree are connected. We don’t care about vertices with zero degree because they don’t belong 
 * 		to Eulerian Cycle or Path (we only consider all edges).
 * 		b) All vertices have even degree
 * 
 * Eulerian Path
 * An undirected graph has Eulerian Path if following two conditions are true.
 * 		a) Same as condition (a) for Eulerian Cycle
 * 		b) If zero or two vertices have odd degree and all other vertices have even degree. 
 * 		Note that only one vertex with odd degree is not possible in an undirected graph 
 * 		(sum of all degrees is always even in an undirected graph)
 * 
 * @author Prasad
 *
 */
public class EulerCycleGraph {
	private List<List<Integer>> adjList;
	private int size;
	
	public EulerCycleGraph(int size) {
		adjList = new ArrayList<List<Integer>>();
		
		for(int i = 0;i<size;i++) {
			adjList.add(new ArrayList<Integer>());
		}
		
		this.size = size;
	}
	
	public void addEdge(int u,int v) {
		adjList.get(u).add(v);
		adjList.get(v).add(u);
	}
	
	public List<Integer> neighbours(int v) {
		return adjList.get(v);
	}
	
	public List<Integer> adjacentTo(int v) {
		return neighbours(v);
	}
	
	private void dfsUtil(int v,boolean[] visited) {
		if(visited[v])
			return;
		
		visited[v] = true;
		
		for(int u:adjacentTo(v)) {
			if(!visited[u])
				dfsUtil(u,visited);
		}
	}
	
	private boolean isConnected() {
		boolean[] visited = new boolean[size];
		
		for(int i=0;i<size;i++)
			visited[i] = false;
		
		int start = -1;
		for(int i=0;i<size;i++)
			if(adjacentTo(i).size() !=0) {
				start = i;
				break;
			}
		
		
		// here the graph has no edges at all. This is obviously not a connected graph
		// but we return true in our case because we use this to find a euler path/cycle
		// and a graph with 0 edges is said to be eulerian.
		if(start == -1)
			return true;
		
		dfsUtil(start,visited);
		
		for(int i=0;i<size;i++)
			if(adjacentTo(i).size() > 0 && visited[i] == false)	// any isolated vertex is not considered in our case
				return false;
		
		// if all the non-zero degree vertices are visited the the graph is connected
		return true;
	}
	
	public boolean isEulerian() {
		if(!isConnected())
			return false;
		
		int odd = 0;
		
		for(int i=0;i<size;i++) {
			if(adjacentTo(i).size() % 2 == 1)
				odd++;
		}
		
		if(odd > 2)
			return false;
		
		if(odd == 0)
			System.out.println("The graph has a Eulerian Cycle");
		else if(odd == 2)
			System.out.println("The graph has a Eulerian Path which starts at either of the odd degree vertex");
		// there wont be a single vertex with odd degree because the graph is undirected
		
		return true;
	}
	
	public static void main(String[] args) {
		EulerCycleGraph g1 = new EulerCycleGraph(5);
	    g1.addEdge(1, 0);
	    g1.addEdge(0, 2);
	    g1.addEdge(2, 1);
	    g1.addEdge(0, 3);
	    g1.addEdge(3, 4);
	    g1.isEulerian();
	    
	    EulerCycleGraph g2 = new EulerCycleGraph(5);
	    g2.addEdge(1, 0);
	    g2.addEdge(0, 2);
	    g2.addEdge(2, 1);
	    g2.addEdge(0, 3);
	    g2.addEdge(3, 4);
	    g2.addEdge(4, 0);
	    g2.isEulerian();
	}
}

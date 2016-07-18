package graphs; 
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Prasad
 *
 */
public class Graph {
	// We could use a set too instead of map
	private Map<Integer,Vertex> vertices;
	private Set<Edge> edges;
	int root;
	int numOfVertices;
	
	public Graph() {
		vertices = new HashMap<Integer,Vertex>();
		edges = new HashSet<Edge>();
		numOfVertices = 0;
	}
	
	public Graph(int root) {
		this.root = root;
		vertices = new HashMap<Integer,Vertex>();
		addVertex(root);
	}
	
	public int root() {
		return root;
	}
	
	public Set<Integer> vertices() {
		return vertices.keySet();
	}

	public Vertex getVertex(int vertex) {
		return vertices.get(vertex);
	}
	
	public void addVertex(int vertex) {
		Vertex v = new Vertex(vertex);
		vertices.put(vertex,v);
		numOfVertices++;
	}
	
	public void addVertexAndItsNeighbours(int vertex,List<Vertex> neighbours) {
		Vertex v = new Vertex(vertex);
		vertices.put(vertex,v);
		v.addNeighbours(neighbours);
		numOfVertices++;
	}
	
	public void addNieghbourToVertex(int vertex,Vertex neighbour) {
		Vertex v = vertices.get(vertex);
		if(v!=null)
			v.addNeighbour(neighbour);
		else
			throw new IllegalArgumentException("Vertex not present in graph : " + vertex);
	}
	
	public void addNieghbourToVertex(int vertex,int neighbour) {
		Vertex v = vertices.get(vertex);
		Vertex n = vertices.get(neighbour);
		
		if(n == null)
			addVertex(neighbour);
			n = vertices.get(neighbour);
		
		if(v!=null)
			v.addNeighbour(n);
		else
			throw new IllegalArgumentException("Vertex not present in graph : " + vertex);
	}
	
	public void addNieghboursToVertex(int vertex,int[] neighbours) {
		for(int n:neighbours) {
			addNieghbourToVertex(vertex,n);
		}
	}
	
	public void addNieghboursToVertex(int vertex,List<Vertex> neighbours) {
		Vertex v = vertices.get(vertex);
		if(v!=null)
			v.addNeighbours(neighbours);
		else
			throw new IllegalArgumentException("Vertex not present in graph : " + vertex);
	}
	
	/** The algorithm used here is that of Dijkstra algorithm
	 * references : http://math.mit.edu/~rothvoss/18.304.3PM/Presentations/1-Melissa.pdf
	 * TC : O(v^2)
	 * @param source the source vertex
	 * @param destination the destination vertex
	 */
	public void shortestPath(int source,int destination) {
		int[] dist = new int[numOfVertices];
		int[] prev = new int[numOfVertices];
		
		Set<Integer> vertexs = vertices();
		Iterator<Integer> it = vertexs.iterator();
		
		dist[source] = 0;
		prev[source] = -1;
		
		while(it.hasNext()) {
			int current = it.next();
			if(current != source)
				dist[current] = Integer.MAX_VALUE;
		}
		
		while(!vertexs.isEmpty()) {
			int minCostVertex = minCostVertex(vertexs, dist);
			vertexs.remove(minCostVertex);
			
			Vertex min = this.vertices.get(minCostVertex);
			List<Vertex> neighours = min.neighbours();
			Iterator<Vertex> neigh = neighours.iterator();
			
			while(neigh.hasNext()) {
				Vertex currentVertex = neigh.next();
				if(dist[currentVertex.vertex()] > (dist[min.vertex()] + edgeWeight(min, currentVertex))) {
					dist[currentVertex.vertex()] = (int) (dist[min.vertex()] + edgeWeight(min, currentVertex));
					prev[currentVertex.vertex()] = min.vertex();
				}
			}
		}
	}
	
	private double edgeWeight(Vertex from,Vertex to) {
		return 0;
	}
	
	private int minCostVertex(Set<Integer> vertex,int[] dist) {
		int minCostVertex = -1;
		int minCost = Integer.MAX_VALUE;
		int current;
		Iterator<Integer> it = vertex.iterator();
		
		while(it.hasNext()) {
			current = it.next();
			if(dist[current] < minCost) {
				minCost = dist[current];
				minCostVertex = current;
			}
		}
		return minCostVertex;
	}
	
	
	//************** Depth First Search **************************************

	public void DFS() {
		Map<Integer,Boolean> visited = new HashMap<Integer,Boolean>();
		int r = root();
		Vertex root = getVertex(r);
		Set<Integer> keys = vertices();
		for(Integer vertex:keys) {
			visited.put(vertex, false);
		}
		DFS(root,visited);
	}
	
	private void DFS(Vertex v, Map<Integer,Boolean> visited) {
		if(isVisited(v,visited))
			return;
		System.out.print(v.vertex()+ " ");
		markVisited(v,visited);
		List<Vertex> neighbours = v.neighbours();
		Iterator<Vertex> it = neighbours.iterator();
		while(it.hasNext()) {
			DFS(it.next(),visited);
		}
	}
	
	private void markVisited(Vertex v,Map<Integer,Boolean> visited) {
		visited.put(v.vertex(), true);
	}
	
	private Boolean isVisited(Vertex v,Map<Integer,Boolean> visited) {
		Boolean b = visited.get(v.vertex());
		if(b == null)
			throw new IllegalStateException("Vertex " + v.vertex() + " was never present in the Graph");
		return b;
	}
}

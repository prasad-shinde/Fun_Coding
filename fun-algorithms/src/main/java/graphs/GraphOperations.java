package graphs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Prasad
 *
 */
public class GraphOperations {
	private Graph g;
	//private Map<Integer,Boolean> visited;
	
	
	public GraphOperations(Graph g) {
		this.g = g;
		//visited = new HashMap<Integer,Boolean>();
	}
	
	public void DFS() {
		Map<Integer,Boolean> visited = new HashMap<Integer,Boolean>();
		int r = g.root();
		Vertex root = g.getVertex(r);
		Set<Integer> keys = g.vertices();
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

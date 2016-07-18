package backtracking;

/**
 * @author Prasad
 * The Hamiltonian path for a graph g is a path visiting each vertex of the graph only once. A Hamiltonian cycle
 * is a Hamiltonian path with a edge from the last vertex in the path to the first vertex.
 * 
 * Note : TSP for a graph is a hamiltonian cycle with minimum weight
 */
public class HamiltonianCycle {
	private int[][] graph;
	private int size;
	private int[] path;
	
	public HamiltonianCycle(int[][] g,int size) {
		graph = g;
		this.size = size;
		path = new int[size];
	}
	
	private boolean isSafe(int position,int vertex) {
		if(graph[path[position-1]][vertex] == 0)
			return false;
		for(int i=0;i<position;i++) {
			if(path[i] == vertex)
				return false;
		}
		return true;
	}
	
	public void hamiltonianCycle() {
		if(hamiltonianCycle(0) ==  true)
			print();
		else
			System.out.println("No hamiltonian cycle exist");
	}
	
	private boolean hamiltonianCycle(int position) {
		if(position == size) {
			if(graph[path[position-1]][path[0]] == 1)
				return true;
			else
				return false;
		}
		
		for(int i = 0;i<size;i++) {
			if(isSafe(position + 1,i)) {
				path[position+1] = i;
				if(hamiltonianCycle(position+1) == true)
					return true;
				path[position+1] = -1;
			}
		}
		return true;
	}
	
	private void print() {
		System.out.println("Hamiltonian path : ");
		for(int i = 0;i<size;i++) {
			System.out.print(path[i] + " ");
		}
	}
	
	public static void main(String[] args) {
		int graph1[][] = {{0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 1, 1, 1, 0},
               };
		HamiltonianCycle g = new HamiltonianCycle(graph1, 5);
		g.hamiltonianCycle();
	}
}

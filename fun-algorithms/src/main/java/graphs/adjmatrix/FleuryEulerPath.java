package graphs.adjmatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import graphs.adjlist.EulerCycleGraph;

/**
 * Fleurys Eulerian Path: See {@link EulerCycleGraph} for Euler path and Euler
 * circuit description. It also show ways to check if a graph is eulerian or not
 * {@link EulerCycleGraph#isEulerian()} .
 * 
 * Fleurys Algorithms helps us print the Euler circuit or path.
 * 
 * Algorithm: 
 * 1. Make sure the graph has either 0 or 2 odd vertices. 
 * 2. If there are 0 odd vertices, start anywhere. If there are 2 odd vertices, start at one of them. 
 * 3. Follow edges one at a time. If you have a choice between a bridge and a non-bridge, always choose the non-bridge. 
 * 4. Stop when you run out of edges.
 * 
 * The idea is here is to not burn the bridges so that we can get back to the
 * same vertex.
 * 
 * @author Prasad
 * 
 */
public class FleuryEulerPath {
	int[][] adjMatrix;
	int size;

	public FleuryEulerPath(int size) {
		this.size = size;
		adjMatrix = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				adjMatrix[i][j] = 0;
			}
		}
	}

	public void addEdge(int u, int v) {
		adjMatrix[u][v] = 1;
		adjMatrix[v][u] = 1;
	}

	public void removeEdge(int u, int v) {
		adjMatrix[u][v] = 0;
		adjMatrix[v][u] = 0;
	}

	public List<Integer> adjacentTo(int u) {
		List<Integer> neigh = new ArrayList<Integer>();

		for (int i = 0; i < size; i++) {
			if (i != u && adjMatrix[u][i] != 0) {
				neigh.add(i);
			}
		}

		return neigh;
	}

	private int dfsCount(int u, boolean[] visited) {
		if (visited[u])
			return 0;

		int count = 1;
		visited[u] = true;

		for (int neigh : adjacentTo(u)) {
			if (!visited[neigh]) {
				count += dfsCount(neigh, visited);
			}
		}

		return count;
	}

	private boolean isValid(int u, int v) {
		// if the edge is not valid or has been removed
		if(adjMatrix[u][v] == 0 || adjMatrix[v][u] == 0)
			return false;
		
		List<Integer> neighbours = adjacentTo(u);
		if (neighbours.size() == 1)
			return true;

		boolean[] visited = new boolean[size];
		
		Arrays.fill(visited, false);
		int count1 = dfsCount(u, visited);
		removeEdge(u, v);
		Arrays.fill(visited, false);
		int count2 = dfsCount(u, visited);
		addEdge(u, v);

		if (count1 > count2)
			return false;
		else
			return true;
	}

	public void printEulerPath() {
		int i = 0;
		int start = 0;

		while (i < size) {
			if ((adjacentTo(i).size() % 2) == 1) {
				start = i;
				break;
			}
			i++;
		}
		
		System.out.println();
		printEulerUtil(start);
	}

	private void printEulerUtil(int u) {
		for (int v : adjacentTo(u)) {
			if (isValid(u, v) && adjMatrix[u][v] != 0) {
				System.out.print(u + "-" + v + " ");
				removeEdge(u, v);
				printEulerUtil(v);
			}
		}
	}

	public static void main(String[] args) {
		FleuryEulerPath g1 = new FleuryEulerPath(4);
		g1.addEdge(0, 1);
		g1.addEdge(0, 2);
		g1.addEdge(1, 2);
		g1.addEdge(2, 3);
		g1.printEulerPath();

		FleuryEulerPath g2 = new FleuryEulerPath(3);
		g2.addEdge(0, 1);
		g2.addEdge(1, 2);
		g2.addEdge(2, 0);
		g2.printEulerPath();

		FleuryEulerPath g3 = new FleuryEulerPath(5);
		g3.addEdge(1, 0);
		g3.addEdge(0, 2);
		g3.addEdge(2, 1);
		g3.addEdge(0, 3);
		g3.addEdge(3, 4);
		g3.addEdge(3, 2);
		g3.addEdge(3, 1);
		g3.addEdge(2, 4);
		g3.printEulerPath();
	}
}

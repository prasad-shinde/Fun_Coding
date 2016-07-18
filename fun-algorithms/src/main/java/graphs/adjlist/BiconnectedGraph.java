package graphs.adjlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Prasad
 * 
 */
public class BiconnectedGraph {
	List<List<Integer>> adjList;
	int size;
	int time;

	public BiconnectedGraph(int size) {
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

	/**
	 * A graph is biconnected if it has the following two properties : 1. The
	 * graph is connected 2. The graph does not have Articulation Points
	 * 
	 * @return
	 */
	public boolean isBiconnected() {
		if (!isConnected())
			return false;

		if (hasArticulationPoints())
			return false;

		return true;
	}

	private boolean isConnected() {
		boolean[] visited = new boolean[size];
		Arrays.fill(visited, false);

		dfsUtil(0, visited);

		for (int i = 0; i < size; i++) {
			if (!visited[i])
				return false;
		}

		return true;
	}

	private List<Integer> adjacentTo(int u) {
		return adjList.get(u);
	}

	private void dfsUtil(int u, boolean[] visited) {
		if (visited[u])
			return;
		visited[u] = true;

		for (int v : adjacentTo(u)) {
			if (!visited[v]) {
				dfsUtil(v, visited);
			}
		}
	}

	private boolean hasArticulationPoints() {
		time = 0;
		Set<Integer> aps = new HashSet<Integer>();
		boolean[] visited = new boolean[size];
		int[] low = new int[size];
		int[] disc = new int[size];
		int[] parent = new int[size];
		Arrays.fill(visited, false);
		Arrays.fill(parent, -1);

		hasAPUtil(0, visited, parent, low, disc, aps);
		return aps.size() != 0;
	}

	public void hasAPUtil(int u, boolean[] visited, int[] parent, int[] low,
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
				if (v != parent[u]) {
					low[u] = Math.min(low[u], disc[v]);
				}
			}
		}
	}

	public static void main(String[] args) {
		BiconnectedGraph g1 = new BiconnectedGraph(2);
		g1.addEdge(0, 1);
		if (g1.isBiconnected())
			System.out.print("Yes\n");
		else
			System.out.print("No\n");

		BiconnectedGraph g2 = new BiconnectedGraph(5);
		g2.addEdge(1, 0);
		g2.addEdge(0, 2);
		g2.addEdge(2, 1);
		g2.addEdge(0, 3);
		g2.addEdge(3, 4);
		g2.addEdge(2, 4);

		if (g2.isBiconnected())
			System.out.print("Yes\n");
		else
			System.out.print("No\n");

		BiconnectedGraph g3 = new BiconnectedGraph(3);
		g3.addEdge(0, 1);
		g3.addEdge(1, 2);
		if (g3.isBiconnected())
			System.out.print("Yes\n");
		else
			System.out.print("No\n");

		BiconnectedGraph g4 = new BiconnectedGraph(5);
		g4.addEdge(1, 0);
		g4.addEdge(0, 2);
		g4.addEdge(2, 1);
		g4.addEdge(0, 3);
		g4.addEdge(3, 4);
		if (g4.isBiconnected())
			System.out.print("Yes\n");
		else
			System.out.print("No\n");

		BiconnectedGraph g5 = new BiconnectedGraph(3);
		g5.addEdge(0, 1);
		g5.addEdge(1, 2);
		g5.addEdge(2, 0);
		if (g5.isBiconnected())
			System.out.print("Yes\n");
		else
			System.out.print("No\n");
	}
}

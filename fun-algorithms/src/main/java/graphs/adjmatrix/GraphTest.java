package graphs.adjmatrix;

import static org.junit.Assert.*;

import org.junit.Test;

public class GraphTest {

	@Test
	public void testDfs() {
		Graph g = new Graph(5);
		assertEquals("DFS output","0,1,2,3,4",g.dfs());
	}

	@Test
	public void testBfs() {
		Graph g = new Graph(5);
		assertEquals("DFS output","0,1,2,3,4",g.bfs());
	}
	
	@Test
	public void testShortestPath() {
		Graph g = new Graph(5);
		assertEquals("Shortest path from 1 to 4",1,g.shortestPath(1, 4));
	}
}

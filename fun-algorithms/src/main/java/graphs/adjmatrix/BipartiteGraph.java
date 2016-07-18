package graphs.adjmatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import queue.Queue;

/**
 * Bipartite Graph : 
 * In the mathematical field of graph theory, a bipartite graph (or bigraph) is a graph whose vertices can be divided 
 * into two disjoint sets U and V (that is, U and V are each independent sets) such that every edge connects a vertex in U to one in V.
 * 
 * Property :
 * All bipartite graphs are 2-colorable.
 * 
 * Algorithm for testing this (This works from connected components): 
 * 1. create a queue, and add a random vertex to the queue and color it blue.
 * 2. while queue is not empty
 * 3.	dequeue a element from queue
 * 4.	color the neighbors of the extracted element with a different color then itself and enqueue them
 * 5.	if while coloring you end up coloring a node with 2 different colors then the graph is not bipartite
 * 6. if there are no color conflicts then the graph is bipartite.
 * 7. end
 * 
 * Note : You can run this over each vertex in the connected components to check the entire picture.
 * 
 * Links :
 * 1. Bipartite Grpahs : http://en.wikipedia.org/wiki/Bipartite_graph
 * 2. 2 colorable algorithm : http://stackoverflow.com/questions/2889036/writing-a-program-to-check-if-a-graph-is-bipartite
 * 
 * @author Prasad
 * 
 */
public class BipartiteGraph {
	// add a edge from 0->1 and its not bipartite anymore
	private int[][] adjMatrix = {{0,0,0,1,1},
								 {0,0,0,1,0},
								 {0,0,0,0,1},
								 {1,1,0,0,0},
								 {1,0,1,0,0}};
	int size = 5;
	
	public BipartiteGraph() {
	}
	
	private List<Integer> neighbours(int u) {
		List<Integer> neighbours = new ArrayList<Integer>();
		
		for(int i = 0;i<size;i++) {
			if(adjMatrix[u][i] !=0)
				neighbours.add(i);
		}
		return neighbours;
	}
	
	public boolean isBipartite() {
		Queue<Integer> q = new Queue<Integer>();
		int[] color = new int[this.size];
		
		Arrays.fill(color,-1);
		
		q.enqueue(0);
		color[0] = 0;
		
		while(!q.isEmpty()) {
			int current = q.dequeue();
			int currentColor = color[current];
			
			for(int neighbour:neighbours(current)) {
				// if color of neighbour is same as parent
				// means the graph is not bipartite
				if(color[neighbour] == currentColor)
					return false;
				
				// if not yet colored, color it with different color and enqueue it
				if(color[neighbour] == -1) {
					if(currentColor == 0) {
						color[neighbour] = 1;
					} else {
						color[neighbour] = 0;
					}
					q.enqueue(neighbour);
				}
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		BipartiteGraph bg = new BipartiteGraph();
		if(bg.isBipartite())
			System.out.println("Is 2 - colorable and so is bipartite!");
		else
			System.out.println("Is NOT 2 - colorable and so is NOT bipartite!");
		
	}
}

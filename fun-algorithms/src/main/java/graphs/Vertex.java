package graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Prasad
 *
 */
public class Vertex implements Comparable<Vertex>{
	private int v;
	private List<Vertex> neighbours;
	
	public Vertex(int value)
	{
		v = value;
		neighbours = new ArrayList<Vertex>();
	}
	
	public void addNeighbour(Vertex n) {
		neighbours.add(n);
	}
	
	public void addNeighbours(List<Vertex> n) {
		this.neighbours.addAll(n);
	}
	
	public int vertex() {
		return v;
	}
	
	public List<Vertex> neighbours() {
		return neighbours;
	}

	public int compareTo(Vertex arg0) {
		if(v == arg0.vertex())
			return 0;
		else
			return -1;
	}
}

package graphs;

public class Edge implements Comparable<Edge>{
	private int from,to;
	private double weight;
	
	public Edge(int from,int to,double wt) {
		this.from = from;
		this.to = to;
		this.weight = wt;
	}
	
	public int to() {
		return to;
	}
	
	public int from() {
		return from;
	}
	
	public double weight() {
		return weight;
	}
	
	@Override
	public int compareTo(Edge arg0) {
		if(weight < arg0.weight()) {
			return -1;
		} else if(weight > arg0.weight()) {
			return 1;
		} else {
			return 0;
		}
	}
}

package set;

public class DisjointSet {
	int[] parent;
	int size;
	
	public DisjointSet(int size) {
		this.size = size;
		parent = new int[size];
		initialize();
	}
	
	private void initialize() {
		for(int i=0;i<size;i++) {
			parent[i] = -1;
		}
	}
	
	public int find(int key) {
		if(parent[key] == -1)
			return key;
		return find(parent[key]);
	}
	
	public void union(int x,int y) {
		int xRoot = find(x);
		int yRoot = find(y);
		parent[xRoot] = yRoot;
	}
	
	public boolean belongToSameSet(int x,int y) {
		int xRoot = find(x);
		int yRoot = find(y);
		return xRoot == yRoot;
	}
}

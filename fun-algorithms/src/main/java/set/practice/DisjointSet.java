package set.practice;

public class DisjointSet {
	int[] parent;
	int[] rank;
	
	public DisjointSet(int size) {
		parent = new int[size];
		rank = new int[size];
	}
	
	public void makeSet() {
		for(int i=0;i<parent.length;i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}
	
	public int find(int x) {
		if(parent[x]!=x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}
	
	public void union(int x,int y) {
		int xRoot = find(x);
		int yRoot = find(y);
		
		if(xRoot == yRoot)
			return;
		
		if(rank[xRoot] > rank[yRoot]) {
			parent[yRoot] = xRoot;
		} else if (rank[xRoot] < rank[yRoot]){
			parent[xRoot] = yRoot;
		} else {
			parent[xRoot] = yRoot;
			rank[xRoot] += 1;
		}
	}
}

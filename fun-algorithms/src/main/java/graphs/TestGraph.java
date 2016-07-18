package graphs;

public class TestGraph {
	public static void main(String[] args) {
		Graph g = new Graph(1);
		g.addVertex(2);
		g.addVertex(3);
		g.addVertex(4);
		g.addVertex(5);
		g.addVertex(6);
		g.addVertex(7);
		
		
		g.addNieghbourToVertex(1, 2);
		g.addNieghbourToVertex(1, 3);
		
		g.addNieghbourToVertex(2, 1);
		g.addNieghbourToVertex(2, 4);
		g.addNieghbourToVertex(2, 5);
		
		g.addNieghbourToVertex(3, 1);
		g.addNieghbourToVertex(3, 6);
		
		int[] temp = {2,5,6};
		g.addNieghboursToVertex(4, temp);
		
		int[] temp1 = {2,4,7};
		g.addNieghboursToVertex(5, temp1);
		
		int[] temp2 = {3,4,7};
		g.addNieghboursToVertex(6, temp2);
		
		int[] temp3 = {5,6};
		g.addNieghboursToVertex(7, temp3);
		
		//GraphOperations op = new GraphOperations(g);
		g.DFS();
	}
}

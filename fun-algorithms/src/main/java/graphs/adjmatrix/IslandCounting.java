package graphs.adjmatrix;


public class IslandCounting {
	private int[][] map;
	private int size;
	
	public IslandCounting(int[][] map,int n) {
		this.map = map;
		this.size = n;
	}
	
	private void dfs(int i,int j,boolean[][] visited) {
		if(!isValid(i,j))
			return;
		if(visited[i][j])
			return;
		visited[i][j] = true;
		if(map[i][j] == 0)
			return;
		
		dfs(i-1,j-1,visited);
		dfs(i-1,j,visited);
		dfs(i-1,j+1,visited);
		
		dfs(i,j-1,visited);
		dfs(i,j+1,visited);
		
		dfs(i+1,j-1,visited);
		dfs(i+1,j,visited);
		dfs(i+1,j+1,visited);
	}
	
	private boolean isValid(int i,int j) {
		return i >= 0 && i<size && j>=0 && j<size; 
	}
	
	public int totalIslands() {
		int count = 0;
		boolean[][] visited = new boolean[size][size];
		
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				visited[i][j] = false;
			}
		}
		
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(!visited[i][j] && map[i][j] != 0) {
					dfs(i,j,visited);
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[][] map = {  
				{1, 1, 0, 0, 0},
		        {0, 1, 0, 0, 1},
		        {1, 0, 0, 1, 1},
		        {0, 0, 0, 0, 0},
		        {1, 0, 1, 0, 1}
		    	};
		
		IslandCounting g = new IslandCounting(map, 5);
		System.out.println(g.totalIslands());
	}
}

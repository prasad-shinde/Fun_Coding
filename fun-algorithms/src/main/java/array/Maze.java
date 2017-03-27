package array;

public class Maze {
	public static int findMinNumSteps(int[][] maze, int rows, int columns, int exitRow, int exitCol)
	{
		boolean[][] visited = new boolean[rows][columns];
		int[] result = {Integer.MAX_VALUE};
		for(int i=0;i<rows;i++) {
		    for(int j =0;j<columns;j++) {
		        visited[i][j] = false;
		    }
		}
		dfs(maze,visited,rows,columns,0,0,exitRow,exitCol,0,result);
		return result[0] == Integer.MAX_VALUE? -1 : result[0];
	}
	
	public static void dfs(int[][] maze,boolean[][] visited,int rows,int columns,
	    int curr,int curc,int exitRow,int exitCol,int dist,int[] result) {
	    if(!isValid(curr,curc,rows,columns,maze,visited))
	        return;
	    if(curr == exitRow && curc == exitCol) {
	        result[0] = Math.min(result[0],dist);
	    }
	    
	    visited[curr][curc] = true;
	    dfs(maze,visited,rows,columns,curr+1,curc,exitRow,exitCol,dist+1,result);
	    dfs(maze,visited,rows,columns,curr,curc+1,exitRow,exitCol,dist+1,result);
	    dfs(maze,visited,rows,columns,curr-1,curc,exitRow,exitCol,dist+1,result);
	    dfs(maze,visited,rows,columns,curr,curc-1,exitRow,exitCol,dist+1,result);
	    visited[curr][curc] = false;
	}
	
	public static boolean isValid(int curr,int curc,int row,int col,int[][] maze,boolean[][] visited) {
	    return curc >=0 && curr>=0 && curr< row && curc < col && maze[curr][curc] == 0 
	    && visited[curr][curc] == false;
	}
	
	public static void main(String[] args) {
		int[][] maze = {{0,0,0,0},{1,0,1,0},{1,0,0,0}};
		System.out.print(findMinNumSteps(maze,3,4,1,1));
	}
}

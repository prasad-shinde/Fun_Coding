package backtracking;

public class NQueenAgain {
	public static void printSolutions(int n) {
		int[][] board = new int[n][n];
		int i,j;
		for(i=0;i<n;i++) {
			for(j=0;j<n;j++) {
				board[i][j] = 0;
			}
		}
		
		
	}
	
	private static boolean isSafe(int[][] board,int row,int column,int size) {
		for(int i = 0;i<size;i++) {
			if(i!=column) {
				if(board[row][i] == 1)
					return false;
			}
			if(i!=row) {
				if(board[i][column] == 1)
					return false;
			}
		}
		
		int i = row-1,j = column-1;
		while(i>=0 && j>00) {
			if(board[i][j] == 1)
				return false;
			i--;
			j--;
		}
		i = row+1;
		j = column-1;
		while(i<size && j>=0) {
			if(board[i][j] == 1)
				return false;
			i++;
			j--;
		}
		i = row+1;
		j = column+1;
		while(i<size && j<size) {
			if(board[i][j] == 1)
				return false;
			i++;
			j++;
		}
		i = row-1;
		j = column+1;
		while(i>=0 && j<size) {
			if(board[i][j] == 1)
				return false;
			i--;
			j++;
		}
		return true;
	}
	
	public static void print(int[][] board,int size) {
		for(int i = 0;i<size;i++) {
			System.out.print("\n");
			for(int j = 0;j<size;j++) {
				System.out.print(board[i][j] + "\t");
			}
		}
	}
	
	public static void nQueens(int[][] board,int x,int size) {
		if(x == size+1)				// if at end print solution
			print(board,size);
		for(int j=0;j<size;j++) {
			if(isSafe(board, x, j, size)) {
				board[x][j] = 1;	// place queen
				nQueens(board, x+1, size);
				board[x][j] = 0;	// backtrack
			}
		}
	}
}

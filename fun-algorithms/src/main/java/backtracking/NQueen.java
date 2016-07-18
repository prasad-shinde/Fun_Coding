package backtracking;

public class NQueen {
	
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
	
	public static boolean NQueen(int[][] board,int size,int rowIndex) {
		if(rowIndex == size)
			return true;
		for(int i = 0;i<size;i++) {
			if(isSafe(board,rowIndex,i,size)) {
				board[rowIndex][i] = 1;
				if(NQueen(board, size, rowIndex+1) == true)
					return true;
				board[rowIndex][i] = 0;
			}
		}
		return false;
	}
	
	public static void NQueen(int[][] board,int size) {
		if(NQueen(board, size, 0) == true)
			print(board,size);
		else
			System.out.print("\nsolution does not exist");
	}
	
	public static void print(int[][] board,int size) {
		for(int i = 0;i<size;i++) {
			System.out.print("\n");
			for(int j = 0;j<size;j++) {
				System.out.print(board[i][j] + "\t");
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] board = { {0, 0, 0, 0},
		        {0, 0, 0, 0},
		        {0, 0, 0, 0},
		        {0, 0, 0, 0}
		    };
		NQueen(board, 4);
	}
}

package dynamicprogramming.simplified;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Mobile Numeric Keypad : 
 * How many unique keys can be generated from the digits of keypads of N digits.
 * Conditions : The only possible allowed moves are up,down,left and right from a particular key.
 * You cannot move to either '*' or '#'
 * 
 * Keypad : 
 * 						{'1','2','3'}
                        {'4','5','6'}
                        {'7','8','9'}
                        {'*','0','#'}
   
   Dynamic Programming Solution : 
   
   Moves(k,n) = number of possible digits of length n starting with key k
   
   Moves(k,n) = 1						if n == 1
   			  = Sum(Moves(i,n-1))       where i = {valid neighbour of key k in up,down,left or right direction}
   										{@link MobileKeyPad#neighbours(int)}
 * @author Prasad
 *
 */
public class MobileKeyPad {

	/*
	public static int possibleMoves(int key) {
		switch (key) {
		case 0:
			return 2;
		case 1:
			return 3;
		case 2:
			return 4;
		case 3:
			return 3;
		case 4:
			return 4;
		case 5:
			return 5;
		case 6:
			return 4;
		case 7:
			return 3;
		case 8:
			return 5;
		case 9:
			return 3;
		}
		return 0;
	}*/

	public static List<Integer> neighbours(int key) {
		List<Integer> neighbours = new ArrayList<Integer>();
		switch (key) {
		case 0:
			neighbours.add(8);
			return neighbours;
		case 1:
			neighbours.add(4);
			neighbours.add(2);
			return neighbours;
		case 2:
			neighbours.add(1);
			neighbours.add(5);
			neighbours.add(3);
			return neighbours;
		case 3:
			neighbours.add(2);
			neighbours.add(6);
			return neighbours;
		case 4:
			neighbours.add(1);
			neighbours.add(5);
			neighbours.add(7);
			return neighbours;
		case 5:
			neighbours.add(2);
			neighbours.add(4);
			neighbours.add(6);
			neighbours.add(8);
			return neighbours;
		case 6:
			neighbours.add(3);
			neighbours.add(5);
			neighbours.add(9);
			return neighbours;
		case 7:
			neighbours.add(4);
			neighbours.add(8);
			return neighbours;
		case 8:
			neighbours.add(7);
			neighbours.add(5);
			neighbours.add(9);
			neighbours.add(0);
			return neighbours;
		case 9:
			neighbours.add(6);
			neighbours.add(8);
			return neighbours;
		}
		return neighbours;
	}

	public static int moves(int n) {
		int[][] moves = new int[10][n + 1];
		int totalMoves = 0;

		// base case of the recurrence
		for (int i = 0; i < 10; i++) {
			moves[i][0] = 0;
			moves[i][1] = 1;
		}

		// build solutions bottom up
		for (int i = 2; i < n + 1; i++) {
			for (int j = 0; j < 10; j++) {
				moves[j][i] = 0;
				for (int neighbour : neighbours(j)) {
					moves[j][i] += moves[neighbour][i - 1];
				}
				moves[j][i] += moves[j][i - 1];
			}
		}

		for (int i = 0; i < 10; i++) {
			totalMoves += moves[i][n];
		}

		return totalMoves;
	}

	public static void main(String[] args) {
		System.out.println(moves(3));
	}
}

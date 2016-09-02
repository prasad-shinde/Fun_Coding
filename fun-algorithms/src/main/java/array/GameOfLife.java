package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 289. Game of Life
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
	Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
	
	Any live cell with fewer than two live neighbors dies, as if caused by under-population.
	Any live cell with two or three live neighbors lives on to the next generation.
	Any live cell with more than three live neighbors dies, as if by over-population..
	Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
	Write a function to compute the next state (after one update) of the board given its current state.
	
	Follow up: 
	Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
	In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
	
	Reference : 
	Question : https://leetcode.com/problems/game-of-life/
	
	Solution with no extra space : http://blog.csdn.net/xudli/article/details/48896549
	
 * @author prasshinde
 *
 */
public class GameOfLife {
    public void gameOfLife(int[][] board) {
        int[][] original = new int[board.length][board[0].length];
        copy(board,original);
        for(int i=0; i<original.length; i++)
          for(int j=0; j<original[i].length; j++)
            if(willSurvive(original,i,j))
                board[i][j] = 1;
            else
                board[i][j] = 0;
    }
    
    private static void copy(int[][] original,int[][] copy) {
        for(int i=0; i<original.length; i++)
          for(int j=0; j<original[i].length; j++)
            copy[i][j]=original[i][j];
    }
    
    private static boolean willSurvive(int[][] board,int i,int j) {
        int aliveNeighbours = 0;
        int deadNeighbours = 0;
        for(List<Integer> neighbour:neighboursOf(board,i,j)) {
            if(board[neighbour.get(0)][neighbour.get(1)] == 1)
                aliveNeighbours++;
            else
                deadNeighbours++;
        }
        if(board[i][j] == 1) {
            if(aliveNeighbours == 2 || aliveNeighbours == 3)
                return true;
            else
                return false;
        } else {
            if(aliveNeighbours == 3)
                return true;
            else
                return false;
        }
    }
    
    private static List<List<Integer>> neighboursOf(int[][] board,int i,int j) {
        List<List<Integer>> neighbours = new ArrayList<List<Integer>>();
        List<Integer> neigh = new ArrayList<Integer>();
        if(i-1 >=0 && j-1>=0) {
            neigh.add(i-1);
            neigh.add(j-1);
            neighbours.add(neigh);
            neigh = new ArrayList<Integer>();
        }
        if(i >=0 && j-1>=0) {
            neigh.add(i);
            neigh.add(j-1);
            neighbours.add(neigh);
            neigh = new ArrayList<Integer>();
        }
        if(i+1 < board.length && j-1>=0) {
            neigh.add(i+1);
            neigh.add(j-1);
            neighbours.add(neigh);
            neigh = new ArrayList<Integer>();
        }
        if(i-1 >=0 && j>=0) {
            neigh.add(i-1);
            neigh.add(j);
            neighbours.add(neigh);
            neigh = new ArrayList<Integer>();
        }
        if(i+1 <board.length && j>=0) {
            neigh.add(i+1);
            neigh.add(j);
            neighbours.add(neigh);
            neigh = new ArrayList<Integer>();
        }
        if(i-1 >=0 && j+1<board[0].length) {
            neigh.add(i-1);
            neigh.add(j+1);
            neighbours.add(neigh);
            neigh = new ArrayList<Integer>();
        }
        if(i >=0 && j+1<board[0].length) {
            neigh.add(i);
            neigh.add(j+1);
            neighbours.add(neigh);
            neigh = new ArrayList<Integer>();
        }
        if(i+1 <board.length && j+1<board[0].length) {
            neigh.add(i+1);
            neigh.add(j+1);
            neighbours.add(neigh);
            neigh = new ArrayList<Integer>();
        }
        return neighbours;
    }
}

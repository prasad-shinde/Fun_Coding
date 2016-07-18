package puzzle;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is not the optimal way to solve the problem. but there were few constraints due which it had to be done like this.
 * 
 * @author Prasad
 *
 */
public class PuzzleSolver {
	
	public static String[] DICTIONARY = {"OX","CAT","TOY","AT","DOG","CATAPULT","T"};
	
	private static int row,col;
	
    public static boolean isWord(String testWord) {
    	for(String s:DICTIONARY)
    		if (s.equals(testWord))
    			return true;
        return false;
    }
    
    public static void countRowsAndCols(char[][] puzzle) {
    	int i=0;
    	for(char[] row:puzzle) {
    		col = row.length;		// here we are assuming every row will have same number of columns
    		i++;
    	}
    	row = i;
    }
    
    public static int findWords(char[][] puzzle) {
    	Map<Character,List<Point>> positions = new HashMap<Character,List<Point>>();
    	int i=0,j=0;
    	countRowsAndCols(puzzle);
    	for(char[] row:puzzle) {
    		j=0;
    		for(char c:row) {
    			if(positions.containsKey(c)) {
    				List<Point> list = positions.get(c);
    				list.add(new Point(i,j));
    			} else {
    				List<Point> list = new ArrayList<Point>();
    				list.add(new Point(i,j));
    				positions.put(c, list);
    			}
    			j++;
    		}
    		i++;
    	}
    	
    	int count = 0;
    	for(String word:DICTIONARY) {
    		count += wordCount1(word,positions,puzzle); 
    	}
    	return count;
    }
    
    private static int wordCount1(String word,Map<Character,List<Point>> positions,char[][] puzzle) {
    	int count = 0;
    	List<Point> starts = positions.get(word.charAt(0));
    	if(starts == null)
    		return 0;
    	if(word.length() == 1)
    		return starts.size();
    	for(Point p:starts) {
    		if(checkDiagonalLD(p, word, puzzle))
    			count ++;
    		if(checkDiagonalLU(p, word, puzzle))
    			count++;
    		if(checkDiagonalRD(p, word, puzzle))
    			count++;
    		if(checkDiagonalRU(p, word, puzzle))
    			count++;
    		if(checkHorizontalFrwd(p, word, puzzle))
    			count++;
    		if(checkHorizontalBack(p, word, puzzle))
    			count++;
    		if(checkVerticalDown(p, word, puzzle))
    			count++;
    		if(checkVerticalUp(p, word, puzzle))
    			count++;
    	}
    	return count;
    }
    
    private static boolean checkHorizontalFrwd(Point p,String word,char[][] puzzle) {
    	if(p.y + word.length() - 1 >= col)
    		return false;
    	int i=0;
    	while(i<word.length()) {
    		if(puzzle[p.x][p.y+i] != word.charAt(i))
    			return false;
    		i++;
    	}
    	return true;
    }
    
    private static boolean checkHorizontalBack(Point p,String word,char[][] puzzle) {
    	if(p.y - word.length() + 1 < 0)
    		return false;
    	
    	int i=0;
    	while(i<word.length()) {
    		if(puzzle[p.x][p.y-i] != word.charAt(i))
    			return false;
    		i++;
    	}
    	return true;
    }
    
    private static boolean checkVerticalDown(Point p,String word,char[][] puzzle) {
    	if(p.x + word.length() - 1 >= row)
    		return false;
    	
    	int i=0;
    	while(i<word.length()) {
    		if(puzzle[p.x+i][p.y] != word.charAt(i))
    			return false;
    		i++;
    	}
    	return true;
    }
    
    private static boolean checkVerticalUp(Point p,String word,char[][] puzzle) {
    	if(p.x - word.length()  + 1 < 0)
    		return false;
    	
    	int i=0;
    	while(i<word.length()) {
    		if(puzzle[p.x-i][p.y] != word.charAt(i))
    			return false;
    		i++;
    	}
    	return true;
    }
    
    
    // diagonal up + and -
    private static boolean checkDiagonalLU(Point p,String word,char[][] puzzle) {
    	if(p.x - word.length() + 1  < 0 || p.y - word.length() + 1  < 0)
    		return false;
    	
    	int i=0;
    	while(i<word.length()) {
    		if(puzzle[p.x-i][p.y-i] != word.charAt(i))
    			return false;
    		i++;
    	}
    	return true;
    }
    
    private static boolean checkDiagonalRU(Point p,String word,char[][] puzzle) {
    	if(p.x - word.length() + 1  < 0 || p.y + word.length() - 1  >= col)
    		return false;
    	
    	int i=0;
    	while(i<word.length()) {
    		if(puzzle[p.x-i][p.y+i] != word.charAt(i))
    			return false;
    		i++;
    	}
    	return true;
    }
    
    
    // diagonal down + and -
    private static boolean checkDiagonalLD(Point p,String word,char[][] puzzle) {
    	if(p.x + word.length() - 1  >= row || p.y - word.length() + 1  < 0)
    		return false;
    	
    	int i=0;
    	while(i<word.length()) {
    		if(puzzle[p.x+i][p.y-i] != word.charAt(i))
    			return false;
    		i++;
    	}
    	return true;
    }
    
    private static boolean checkDiagonalRD(Point p,String word,char[][] puzzle) {
    	if(p.x + word.length() - 1  >= row || p.y + word.length() - 1  >= col)
    		return false;
    	
    	int i=0;
    	while(i<word.length()) {
    		if(puzzle[p.x+i][p.y+i] != word.charAt(i))
    			return false;
    		i++;
    	}
    	return true;
    }
    
    
    private static int wordCount(String word,Map<Character,List<Point>> positions,char[][] puzzle) {
    	int count = 0;
    	List<Point> starts = positions.get(word.charAt(0));
    	if(starts == null)
    		return 0;
    	for(Point p:starts) {
    		count += wordCount(word,0,p.x,p.y,puzzle);
    	}
    	return count;
    }
    
    private static int wordCount(String word,int wordIndex,int x,int y,char[][] puzzle) {
    	if(wordIndex == word.length())
    		return 1;
    	
    	if(word.charAt(wordIndex) != puzzle[x][y])
    		return 0;
    	
    	if(word.charAt(wordIndex) == puzzle[x][y] && wordIndex == (word.length()-1))
    		return 1;
    	
    	int count = 0;
    	List<Point> neighbours = neighbours(new Point(x,y));
    	for(Point p:neighbours) {
    		count += wordCount(word,wordIndex+1,p.x,p.y,puzzle);
    	}
    	return count;
    }
    
    private static List<Point> neighbours(Point p) {
    	List<Point> neighbours = new ArrayList<Point>();
    	List<Point> temp = new ArrayList<Point>();
    	temp.add(new Point(p.x-1,p.y-1));
    	temp.add(new Point(p.x-1,p.y));
    	temp.add(new Point(p.x-1,p.y+1));
    	temp.add(new Point(p.x,p.y-1));
    	temp.add(new Point(p.x,p.y+1));
    	temp.add(new Point(p.x+1,p.y-1));
    	temp.add(new Point(p.x+1,p.y));
    	temp.add(new Point(p.x+1,p.y+1));
    	
    	for(Point p1:temp) {
    		if(isValid(p1))
    			neighbours.add(p1);
    	}
    	return neighbours;
    }
    
    private static boolean isValid(Point p) {
    	if(p.x < 0 || p.x >= row || p.y <0 || p.y >= col)
    		return false;
    	return true;
    }
    
    public static void main(String[] args) {
    	char[][] puzzle = {
    			{'C','A','T'},
    			{'X','Z','T'},
    			{'Y','O','T'}
    	};

    	char[][] puzzle1 = {
    			{'C','A','T','A','P','U','L','T'},
    			{'X','Z','T','T','O','Y','O','O'},
    			{'Y','O','T','O','X','T','X','X'}
    	};
    	System.out.println(findWords(puzzle));
    }
}

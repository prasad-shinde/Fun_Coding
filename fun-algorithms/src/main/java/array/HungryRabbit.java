package array;

import java.util.ArrayList;
import java.util.List;

public class HungryRabbit {
	private int[][] garden;
	Point rabbitPosition;
	private int n,m;
	private long carrotsEaten = 0;
	
	public long carrotsEaten(int[][] garden) {
		this.carrotsEaten = 0;
		this.garden = garden;
		
		n = garden.length;
		m = garden[0].length;
		
		initializeRabbitPosition();
		moveAndEat(this.rabbitPosition);
		while(!isRabbitSleeping()) {
			Point maxNeighbour = maxNeighbour();
			moveAndEat(maxNeighbour);
		}
		return carrotsEaten;
	}
	
	private Point maxNeighbour() {
		int max = 0;
		Point maxNeighbour = new Point();
		if(isValidPosition(new Point(rabbitPosition.getX()-1,rabbitPosition.getY())) && garden[rabbitPosition.getX()-1][rabbitPosition.getY()] >max) {
			max = garden[rabbitPosition.getX()-1][rabbitPosition.getY()];
			maxNeighbour.setX(rabbitPosition.getX()-1);
			maxNeighbour.setY(rabbitPosition.getY());
		}
		if(isValidPosition(new Point(rabbitPosition.getX(),rabbitPosition.getY()-1)) && garden[rabbitPosition.getX()][rabbitPosition.getY()-1] >max) {
			max = garden[rabbitPosition.getX()][rabbitPosition.getY()-1];
			maxNeighbour.setX(rabbitPosition.getX());
			maxNeighbour.setY(rabbitPosition.getY()-1);
		}
		if(isValidPosition(new Point(rabbitPosition.getX()+1,rabbitPosition.getY())) && garden[rabbitPosition.getX()+1][rabbitPosition.getY()] >max) {
			max = garden[rabbitPosition.getX()+1][rabbitPosition.getY()];
			maxNeighbour.setX(rabbitPosition.getX()+1);
			maxNeighbour.setY(rabbitPosition.getY());
		}
		if(isValidPosition(new Point(rabbitPosition.getX(),rabbitPosition.getY()+1)) && garden[rabbitPosition.getX()][rabbitPosition.getY()+1] >max) {
			max = garden[rabbitPosition.getX()][rabbitPosition.getY()+1];
			maxNeighbour.setX(rabbitPosition.getX());
			maxNeighbour.setY(rabbitPosition.getY()+1);
		}
		return maxNeighbour;
	}
	
	private void moveAndEat(Point p) {
		rabbitPosition.setX(p.getX());
		rabbitPosition.setY(p.getY());
		carrotsEaten += garden[p.getX()][p.getY()];
		garden[p.getX()][p.getY()] = 0;
	}
	
	private boolean isRabbitSleeping() {
		if(isValidPosition(new Point(rabbitPosition.getX()-1,rabbitPosition.getY()))) {
			if(garden[rabbitPosition.getX()-1][rabbitPosition.getY()] >0)
				return false;
		}
		if(isValidPosition(new Point(rabbitPosition.getX(),rabbitPosition.getY()-1))) {
			if(garden[rabbitPosition.getX()][rabbitPosition.getY()-1] >0)
				return false;
		}
		if(isValidPosition(new Point(rabbitPosition.getX()+1,rabbitPosition.getY()))) {
			if(garden[rabbitPosition.getX()+1][rabbitPosition.getY()] >0)
				return false;
		}
		if(isValidPosition(new Point(rabbitPosition.getX(),rabbitPosition.getY()+1))) {
			if(garden[rabbitPosition.getX()][rabbitPosition.getY()+1] >0)
				return false;
		}
		return true;
	}
	
	private boolean isValidPosition(Point p) {
		return p.getX()<n && p.getX()>=0 && p.getY()<m && p.getY()>=0;
	}
	
	private void initializeRabbitPosition() {
		initializeRabbitPosition(possibleCenters());
	}
	
	private void initializeRabbitPosition(List<Point> possibleCenters) {
		int max = 0;
		for(Point p:possibleCenters) {
			if(garden[p.getX()][p.getY()] > max) {
				max = garden[p.getX()][p.getY()];
				rabbitPosition = p; 
			}
		}
	}
	
	private List<Point> possibleCenters() {
		List<Point> possibleCenter = new ArrayList<Point>();
		if(n % 2 != 0 && m % 2  !=0) {
			possibleCenter.add(new Point((n)/2,(m)/2));
			possibleCenter.add(new Point((n)/2,(m)/2));
			possibleCenter.add(new Point((n)/2,(m)/2));
			possibleCenter.add(new Point((n)/2,(m)/2));
		} else if(n%2 != 0) {
			possibleCenter.add(new Point((n)/2,(m-1)/2));
			possibleCenter.add(new Point((n)/2,(m+1)/2));
		} else {
			possibleCenter.add(new Point((n-1)/2,(m)/2));
			possibleCenter.add(new Point((n+1)/2,(m)/2));
		}
		return possibleCenter;
	}
	
	public static void main(String[] args) {
		HungryRabbit hr = new HungryRabbit();
		int[][] garden = {{5, 7, 8, 6, 3},
		                  {0, 0, 7, 0, 4},
		                  {4, 6, 3, 4, 9},
		                  {3, 1, 0, 5, 8}};
		System.out.println(hr.carrotsEaten(garden));
	}
}

class Rabbit {
	Point position;
	long carrotsEaten;
	
	public void moveAndEat(Point p,int[][] garden) {
		position.setX(p.getX());
		position.setY(p.getY());
		carrotsEaten += garden[p.getX()][p.getY()];
		garden[p.getX()][p.getY()] = 0;
	}
	
	public boolean isSleeping(int[][] garden) {
		if(isValidPosition(new Point(position.getX()-1,position.getY()),garden)) {
			if(garden[position.getX()-1][position.getY()] >0)
				return false;
		}
		if(isValidPosition(new Point(position.getX(),position.getY()-1),garden)) {
			if(garden[position.getX()][position.getY()-1] >0)
				return false;
		}
		if(isValidPosition(new Point(position.getX()+1,position.getY()),garden)) {
			if(garden[position.getX()+1][position.getY()] >0)
				return false;
		}
		if(isValidPosition(new Point(position.getX(),position.getY()+1),garden)) {
			if(garden[position.getX()][position.getY()+1] >0)
				return false;
		}
		return true;
	}
	
	private boolean isValidPosition(Point p,int[][] garden) {
		int n = garden.length;
		int m = garden[0].length;
		return p.getX()<n && p.getX()>=0 && p.getY()<m && p.getY()>=0;
	}
}

class Point {
	public int x,y;
	
	public Point() {
		this.x = -1;
		this.y = -1;
	}
	
	public Point(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}

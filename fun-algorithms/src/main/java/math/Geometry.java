package math;

import java.awt.Point;

public class Geometry {
	public static int position(Point a,Point b,Point c) {
		return (int) Math.signum((b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x));
	}
}

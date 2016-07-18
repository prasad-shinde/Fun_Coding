package quora.heap;

import quora.util.AgeGenerator;

/**The Node class which stores the data from the commands
 * @author Prasad
 *
 */
public class Node {
	private double score;
	private String id;
	private String type;
	private long age;
	
	public Node(String type,double score,String uniqueId) {
		this.score = score;
		id = uniqueId;
		this.type = type;
		age = AgeGenerator.getInstance().age();
	}
	
	@Override
	public boolean equals(Object n) {
		try {
			return score == ((Node)n).score;
		} catch (ClassCastException c) {
			return false;
		}
	}
	
	public String type() {
		return type;
	}
	
	public double score() {
		return score;
	}
	
	public boolean lessThan(Node n) {
		return score < n.score;
	}
	
	public boolean lessThanOrEquals(Node n) {
		if(score != n.score)
			return score <= n.score;
		else
			 return age < n.age;
	}
	
	@Override
	public String toString() {
		return id;
	}
}

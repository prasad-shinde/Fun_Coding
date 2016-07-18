package trees.binarytree.clrs;

public class BinarySearchTree {
	private Node root;
	
	public Node treeMinimum(Node n) {
		if(n==null)
			return n;
		
		while(n.left() != null) {
			n = n.left();
		}
		
		return n;
	}
	
	public Node treeMaximum(Node n) {
		if(n== null)
			return n;
		
		while(n.right()!=null) {
			n = n.right();
		}
		
		return n;
	}
	
	public Node successor(Node n) {
		if(n.right()!=null) {
			return treeMinimum(n.right());
		} else {
			Node p = n.parent();
			while(p != null && p.right() == n) {
				n = p;
				p = p.parent();
			}
			return p;
		}
	}
	
	public Node predecessor() {
		return null;
	}
	
	public int longestDiameter(Node n) {
		int lh = 0,rh = 0;
		int ld,rd,diam;
		if(n.left()!=null)
			lh = n.left().height();
		if(n.right()!=null)
			rh = n.right().height();
		diam = 1 + lh + rh;
		ld = longestDiameter(n.left());
		rd = longestDiameter(n.right());
		
		return Math.max(diam, Math.max(ld, rd));
	}
	
	
	int longestDiameter_1(Node node) {
		if(node == null)
			return 0;
		int lh=0,rh=0;
		if(node.left()!=null)
			lh = node.left().height();
		if(node.right()!=null)
			rh = node.right().height();
		return(Math.max(lh+rh+1, Math.max(longestDiameter(node.left()), longestDiameter(node.right()))));
	}
}

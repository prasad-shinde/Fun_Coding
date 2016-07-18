package trees.binarytree.clrs;

public class Node {
	private int key;
	private Node left,right,parent;
	
	public Node(int k,Node parent) {
		key = k;
		left = right = null;
		this.parent = parent;
	}
	
	public void insertLeft(Node n) {
		left = n;
	}
	
	public void insertRight(Node n) {
		right = n;
	}
	
	public void modifyParent(Node n) {
		parent = n;
	}
	
	public Node left() {
		return left;
	}
	
	public Node right() {
		return right;
	}
	
	public Node parent() {
		return parent;
	}
	
	public int height() {
		if(left ==null && right == null) {
			return 0;
		} else if(left!= null && right!=null) {
			return 1 + Math.max(left.height(), right.height());
		} else if(left != null && right == null) {
			return 1 + right.height();
		} else
			return 1 + left.height();
	}
	
	public void dfs(Node n) {
		if(n == null)
			return;
		System.out.println(n.key);
		dfs(n.left);
		dfs(n.right);
	}
}

package binarynode.avlnode;

public class AVLNode {
	int bf;
	int key;
	AVLNode left,right,parent;
	
	public AVLNode(int value,AVLNode parent) {
		key = value;
		left = right = null;
		this.parent = parent; 
	}
	
	public void insert(int value) {
		if(key <= value) {
			if(right == null) {
				right = new AVLNode(value,this);
				check(right);
			} else {
				right.insert(value);
			}
		} else {
			if(left == null) {
				left = new AVLNode(value,this);
				check(left);
			} else {
				left.insert(value);
			}
		}
	}
	
	private void check(AVLNode node) {
		while(node != null) {
			if(node.balanceFactor() == 2) { // left case
				AVLNode left = node.left;
				if(left.balanceFactor() == -1) { //left-right case
					leftRotate(left);
				}
				rightRotate(node.left); //left-left case
				check(node.left);
			} else if (node.balanceFactor() == -2) {
				
			} else {
				node = node.parent;
			}
		}
	}
	
	public int height() {
		return height(this);
	}
	
	private int height(AVLNode node) {
		if(node == null)
			return 0;
		else {
			return 1 + Math.max(height(node.left), height(this.right));
		}
	}
	
	public int balanceFactor() {	// we can even cache this to not compute it everytime unless modified
		bf = height(left) - height(right);
		return bf;
	}
	
	/**
	 * @param node
	 * 
	 * thinking in beta - this is the left rotate of LR case
	 */
	private void leftRotate(AVLNode node) {
		AVLNode parent = node.parent;
		AVLNode right = node.right;
		
		right.parent = node.parent;
		if(node.isLeft())
			parent.left = right;
		else
			parent.right = right;
		
		node.right = right.left;
		right.left = node;
		node.parent = right;
	}
	
	/**
	 * @param node
	 * 
	 * Beta - Left left case - right rotate
	 */
	private void rightRotate(AVLNode node) {
		AVLNode parent = node.parent;
		AVLNode right = node.right;
		
		// rotate node to right and update its parent
		node.right = parent;
		node.parent = parent.parent;
		
		// adjust node's right link and update its new parent
		parent.left = right;
		right.parent = parent;
		
		parent.parent = node;
	}
	
	public boolean isLeft() {
		if(parent == null)	// it means that the node is root
			return false;
		else
			return parent.left == this;
	}
	
	public String preorder() {
		return preorder(this);
	}
	
	private String preorder(AVLNode node) {
		if(node == null)
			return "";
		else {
			return node.key + preorder(node.left) + preorder(node.right);
		}
	}
}

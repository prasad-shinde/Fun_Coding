package binarynode;

public abstract class Node {
	public abstract Node left();
	public abstract Node right();
	public abstract Node parent();
	public abstract int height();
	public abstract boolean insert(int value);
	public abstract boolean insertLeft(int value);
	public abstract boolean insertRight(int value);
	
	public abstract boolean insertLeft(Node value);
	public abstract boolean insertRight(Node value);
	public abstract boolean modifyparent(Node value);
	
	public abstract String inorder();
	public abstract String preorder();
	public abstract String postorder();
	public abstract String data();
	public abstract int value();
	public abstract boolean isNull();
	
	
	// extras
	public abstract void mirror();
	public abstract boolean isLeaf();
	public abstract int subTreeSum();
}

package linkedlist.node;

public abstract class Node {
	public abstract Node next();
	public abstract Node endNode();
	public abstract void addNext(int value);
	public abstract void addNext(Node value);
	public abstract void print();
	public abstract boolean isNullNode();
	public abstract int data();
}

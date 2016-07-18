package linkedlist.node;

public class NullNode extends Node {

	private Node parent;
	
	public NullNode(Node p) {
		parent = p;
	}
	
	@Override
	public Node next() {
		return null;
	}

	@Override
	public Node endNode() {
		return parent;
	}

	@Override
	public void addNext(int value) {
		parent = new ListNode(value);
	}

	@Override
	public void print() {
		System.out.print("");
	}
	
	public String toString() {
		return "";
	}

	@Override
	public void addNext(Node value) {
		parent = value;		
	}

	@Override
	public boolean isNullNode() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int data() {
		// TODO Auto-generated method stub
		return 0;
	}
}

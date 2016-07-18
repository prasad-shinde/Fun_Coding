package linkedlist.node;

public class ListNode extends Node {

	private int data;
	private Node next;
	
	public ListNode(int value) {
		data = value;
		next = new NullNode(this);
	}
	
	@Override
	public Node next() {
		return next;
	}

	public int value() {
		return data;
	}
	
	public void addNext(int value) {
		next = new ListNode(value);
	}

	@Override
	public Node endNode() {
		return next.endNode();
	}

	@Override
	public void print() {
		System.out.print(this.toString());
	}
	
	public String toString() {
		return data+next.toString();
	}

	@Override
	public void addNext(Node value) {
		next = value;
	}

	@Override
	public boolean isNullNode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int data() {
		// TODO Auto-generated method stub
		return data;
	}
}

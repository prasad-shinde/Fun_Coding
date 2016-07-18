package queue;

public class DoublyListNode <T>{
	private T data;
	private DoublyListNode<T> next;
	private DoublyListNode<T> previous;
	
	public DoublyListNode(T value) {
		data = value;
		next = previous = null;
	}
	
	public void addNext(T value) {
		DoublyListNode<T> node = new DoublyListNode<T>(value);
		node.next = next;
		node.previous = this;
		next = node;
	}
	
	public void addPrevious(T value) {
		DoublyListNode<T> node = new DoublyListNode<T>(value);
		node.previous = previous;
		node.next = this;
		previous = node;
	}
	
	public T data() {
		return data;
	}
	
	public DoublyListNode<T> next() {
		return next;
	}
	
	public DoublyListNode<T> previous() {
		return previous;
	}
	
	public void delete() {
		DoublyListNode<T> previous = previous();
		DoublyListNode<T> next = next();
		if(previous != null)
			previous.next = next;
		if(next!=null)
			next.previous = previous;
	}
	
	public String toString() {
		if(next != null)
			return (" " + data + next.toString());
		else
			return (" " +data+"");
	}
}

package queue;

public class DoublyLinkList<T> {
	private DoublyListNode<T> front;
	private DoublyListNode<T> last;
	
	public DoublyLinkList() {
		front = last = null;
	}
	
	public DoublyLinkList(T data) {
		DoublyListNode<T> node = new DoublyListNode<T>(data);
		front = last = node;
	}
	
	public void insertAtEnd(T data) {
		if(last == null) {
			create(data);
		}
		else {
			last.addNext(data);
			last = last.next();
		}
	}
	
	private void create(T data) {
		DoublyListNode<T> node = new DoublyListNode<T>(data);
		front = last = node;
	}
	
	public void insertAtFront(T data) {
		if(front == null) {
			create(data);
		}
		else {
			front.addPrevious(data);
			front = front.previous();
		}
	}
	
	public void moveToFront(T data) {
		DoublyListNode<T> node = front;
		while(node!=last) {
			if(node.data() == data) {
				node.delete();
				break;
			}
		}
		front.addPrevious(data);
		front = front.previous();
	}
	
	public T removeLast() {
		if(last == null || front == null)
			return null;
		
		T value = last.data();
		if(last == front) {
			last = front = null;
			return value;
		}
		
		DoublyListNode<T> node = last.previous();
		last.delete();
		last = node;
		return value;
	}
	
	public boolean isEmpty() {
		if(last == null || front == null)
			return true;
		return false;
	}
	
	public String toString() {
		return front.toString();
	}
}

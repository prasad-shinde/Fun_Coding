package queue;

public class Queue<T> {
	private DoublyLinkList<T> queue;
	
	public Queue() {
		queue = new DoublyLinkList<T>();
	}
	
	public void enqueue(T data) {
		queue.insertAtFront(data);
	}
	
	public T dequeue() {
		return queue.removeLast();
	}
	
	public void moveToFront(T data) {
		queue.moveToFront(data);
	}
	
	public String toString() {
		return queue.toString();
	}
	
	public boolean isEmpty() {
		return queue.isEmpty();
	}
}

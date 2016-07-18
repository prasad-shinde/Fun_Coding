package linkedlist;

public class LinklistTest {
	public static void main(String[] args) {
		LinkList l = new LinkList(1);
		l.insertAtEnd(2);
		l.insertAtEnd(3);
		l.insertAtEnd(4);
		l.insertAtEnd(5);
		l.insertAtEnd(6);
		
		l.print();
		l.reverse();
	}
}

package linkedlist;

import linkedlist.node.ListNode;
import linkedlist.node.Node;
import linkedlist.node.NullNode;

public class LinkList {
	private Node root;
	
	public LinkList() {
		root = new NullNode(null);
	}
	
	public LinkList(int data) {
		root = new ListNode(data);
	}
	
	public void insertAtEnd(int value) {
		Node endNode = root.endNode();
		if(endNode == null)
			root = new ListNode(value);
		else
			endNode.addNext(value);
	}
	
	public void insertFront(int value) {
		Node newRoot = new ListNode(value);
		newRoot.addNext(root);
		root = newRoot;
	}
	
	public void print() {
		root.print();
	}
	
	public Node reverse() {
		Node r = null;
		r = reverse(root,r);
		if(r!=null) {
			System.out.print("Data : "+r.data());
			r.print();
		} else {
			throw new NullPointerException("Something went wrong here!!");
		}
		return r;
	}
	
	
	private Node reverse(Node n,Node r) {
		if(n.next().isNullNode()) {
			r = n;
			System.out.print("Data : "+r.data());
			return r;
		}
		else {
			r = reverse(n.next(),r);
			r.addNext(n);
			return r;
		}
	}
}

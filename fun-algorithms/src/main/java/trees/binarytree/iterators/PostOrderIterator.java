package trees.binarytree.iterators;

import java.util.Iterator;
import java.util.Stack;

import binarynode.Node;

public class PostOrderIterator implements Iterator<String>{
	private Node next;
	private Node root;
	Stack<Node> stack;
	
	public PostOrderIterator(Node root) {
		this.root = root;
		next = root;
		stack = new Stack<Node>();
		
		while(!next.left().isNull() || !next.right().isNull()) {
			while(!next.left().isNull()) {
				stack.push(next);
				next = next.left();
			}
			if(!next.right().isNull()) {
				next = next.right();
				if(next.left().isNull())
					stack.push(next);
			}
		}
	}
	
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	
	public String next() {
		Node current = next;
		
		if(hasNext() == false)
			return null;
		
		if(next == root)
		{
			next = null;
			return current.data();
		}
		
		next = stack.peek();
		
		if((!next.right().isNull()) && (next.right() != current)) {
			next = next.right();
			
			while(!next.left().isNull() || !next.right().isNull()) {
				while(!next.left().isNull()) {
					stack.push(next);
					next = next.left();
				}
				if(!next.right().isNull()) {
					next = next.right();
					if(next.left().isNull())
						stack.push(next);
				}
			}
		}
		else
			stack.pop();
		return current.data();
	}

	public void remove() {
	}
}

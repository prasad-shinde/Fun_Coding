package trees.binarytree.iterators.practice;

import java.util.Iterator;

import binarynode.Node;

/**
 * @author Prasad
 * Algorithm :
 * Base case: Move to the left most child in the tree till its present.
 * 
 * 1. if right child is present
 * 2.	move right by 1
 * 3.	move to the leftmost child
 * 4. else
 * 5.	move up till the parent's right child is not null and its equal to the child
 * 
 */
public class InOrderIterator implements Iterator<String>{
	Node next;
	
	public InOrderIterator(Node node) {
		next = node;
		while(!next.left().isNull()) {
			next = next.left();
		}
	}
	
	@Override
	public boolean hasNext() {
		return next!=null && !next.isNull();
	}

	@Override
	public String next() {
		Node current = next;
		if(!hasNext()) {
			throw new NullPointerException("End of traversal");
		}
		
		if(!next.right().isNull()) {
			next = next.right();
			while(!next.left().isNull()) {
				next = next.left();
			}
		} else {
			Node child = next;
			Node parent = next.parent();
			
			while((parent!=null && !parent.isNull()) && (parent.right().isNull() || parent.right() == child)) {
				child = parent;
				parent = parent.parent();
			}
			next = parent;
		}
		
		return current.data();
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}

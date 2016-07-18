package trees.binarytree.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import binarynode.Node;

public class InOrderIterator implements Iterator<String> {
	private Node next;
	
	public InOrderIterator(Node root) {
		next = root;
		while(!next.left().isNull()) {
			next = next.left();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 * 
	 * No element when we are back to root backtracking
	 */
	@Override
	public boolean hasNext() {
		return (next != null);
	}

	@Override
	public String next() {
		Node current = next;
		if(!hasNext()) {
			throw new NoSuchElementException();
		}
		
		if(next.right().isNull()) {
			Node parent = next.parent();
			if(parent.left() == next) {
				next = parent;
			} else {
				Node child = next;
				while((parent != null) && (parent.right() == child)) {
					child = parent;
					parent = parent.parent();
				}
				if(parent == null || parent.isNull()) {
						next = null;
				}
				else {
					next = parent;
				}
			}
		} else {
			next = next.right();
			while(!next.left().isNull())
				next = next.left();
		}
		return current.data();
	}

	@Override
	public void remove() {
	}

}

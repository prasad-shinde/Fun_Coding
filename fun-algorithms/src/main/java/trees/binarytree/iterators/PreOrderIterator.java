package trees.binarytree.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

import binarynode.Node;

public class PreOrderIterator implements Iterator<String>{
	private Node nextNode;
	
	public PreOrderIterator(Node root) {
		nextNode = root;
	}
	
	public boolean hasNext() {
		return nextNode != null;
	}

	@Override
	public String next() {
		if(nextNode == null || nextNode.isNull())
			throw new NoSuchElementException();
		Node current = nextNode;
		if(!nextNode.left().isNull()) {
			nextNode = nextNode.left();
		} else if(!nextNode.right().isNull()) {
			nextNode = nextNode.right();
		} else {
			Node parent = nextNode.parent();
			Node child = nextNode;
			while((parent != null && !parent.isNull()) && (parent.right().isNull() || parent.right() == child)) {
				child = parent;
				parent = parent.parent();
			}
			if(parent == null || parent.isNull())
				nextNode = null;
			else
				nextNode = parent.right();
		}
		return current.data();
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
}

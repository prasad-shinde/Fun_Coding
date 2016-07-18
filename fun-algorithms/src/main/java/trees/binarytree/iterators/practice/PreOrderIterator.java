package trees.binarytree.iterators.practice;

import java.util.Iterator;

import binarynode.Node;

/**
 * @author Prasad
 * 
	Preorder iterator :
		Preorder iterator uses stack to store the nodes which are visited.
		We use this stack to backtrack to the first non visited right child.
	Algorithm
	1. if there is left child.
	2.	go left by 1.
	3. else
	4.	if there is right child 
	5.		go right by 1
	6.	else
	7.		go back to the first unvisited right child till you reach root.	
	8.	end if
	9. end if
 *
 *
 */
public class PreOrderIterator implements Iterator<String>{
	Node next;
	
	public PreOrderIterator(Node node) {
		next=node;
	}
	
	@Override
	public boolean hasNext() {
		return next!=null;
	}
	@Override
	public String next() {
		if(!hasNext())
			throw new NullPointerException("End of preorder traversal");
		
		Node current = next;
		
		if(!next.left().isNull()) {
			next = next.left();
		} else if(!next.right().isNull()) {
			next = next.right();
		} else {
			Node child = next;
			Node parent = next.parent();
			
			while((parent!=null && !parent.isNull()) && (parent.right().isNull() || parent.right() == child)) {
					child = parent;
					parent = parent.parent();
			}
			if((parent == null) || parent.isNull())
				next = parent;
			else
				next = parent.right();
		}
		return current.data();
	}
	
	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}

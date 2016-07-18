package trees.binarytree;

import binarynode.BinaryNode;
import binarynode.Node;

public class MinHeap {
	private Node root;
	
	public MinHeap() {
		root = null;
	}
	
	public MinHeap(int data) {
		root = new BinaryNode(data,null);
	}
	
	public void add(int value) {
		if(root == null)
			root = new BinaryNode(value,null);
		else
			insert(root,value);
	}
	
	public void insert(Node n,int value) {
		if(n.left() == null && n.right() == null)
			n.insertLeft(value);
		if(n.left() != null && n.right() == null)
			n.insertRight(value);
		if(n.right()!=null)
			insert(n.right(),value);
		else if(n.left()!=null)
			insert(n.left(),value);
	}
}

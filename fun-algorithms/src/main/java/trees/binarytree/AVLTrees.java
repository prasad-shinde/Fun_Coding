package trees.binarytree;

import binarynode.AVLNode;

public class AVLTrees extends BinarySearchTree {
	
	public AVLTrees(int data) {
		root = new AVLNode(data, null);
	}
	
	public boolean insert(int value) {
		root.insert(value);
		return true;		
	}
	
	
}

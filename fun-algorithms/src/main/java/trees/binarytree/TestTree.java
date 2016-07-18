package trees.binarytree;

import java.util.Iterator;

public class TestTree {
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(25);
		bst.insert(10);
		bst.insert(1);
		bst.insert(5);
		bst.insert(40);
		
		bst.insert(15);
		bst.insert(20);
		
		bst.insert(30);
		bst.insert(35);
		
		bst.insert(45);
		bst.insert(50);
		
		//System.out.println(bst.treeSum());
		System.out.println(bst.inorder());
		
		Iterator<String> it = bst.inOrderIterator();
		System.out.println("In order iterator : ");
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println("\nPreOrder recursive: ");
		System.out.println(bst.preorder());
		
		it = bst.preOrderIterator();
		System.out.println("pre order iterator : ");
		while(it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		
	}
}

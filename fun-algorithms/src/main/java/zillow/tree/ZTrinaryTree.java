package zillow.tree;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ZTrinaryTree {
	private ZNode root;
	
	public ZTrinaryTree() {
		root = null;
	}
	
	public void addAll(List<Integer> list) {
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()) {
			insert(it.next());
		}
	}
	
	public void insert(int value) {
		if(root == null) {
			root = new ZTernaryNode(value);
		} else {
			root.insert(value);
		}
	}
	
	public void delete(int value) {
		if(root == null)
			throw new NoSuchElementException("The value " + value + " is not present in the tree");
		else
			root.delete(value);
	}
	
	public String inorder() {
		return root.inorder();
	}
	
	public void printInorder() {
		System.out.println("Inorder : " + inorder());
	}
}

package trees.binarytree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import trees.binarytree.iterators.InOrderIterator;
import trees.binarytree.iterators.PostOrderIterator;
import trees.binarytree.iterators.PreOrderIterator;
import binarynode.*;

/**
 * @author Prasad
 *
 */
public class BinarySearchTree {
	protected Node root;
	
	public BinarySearchTree() {
		root = new NullNode(null, false);
	}
	
	public BinarySearchTree(int value) {
		root = new BinaryNode(value,null);
	}
	
	public int height() {
		return root.height();
	}
	
	public boolean insert(int value) {
		if (!root.isNull())
			return root.insert(value);
		else {
			root = new BinaryNode(value, null);
			return true;
		}
	}
	
	public String inorder() {
		return root.inorder();
	}
	
	public String preorder() {
		return root.preorder();
	}
	
	public String postorder() {
		return root.postorder();
	}
	
	
	public boolean isBST() {
		return isBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}
	
	/*
	 * Really tricky question.. I was misguided completely..
	 * */
	public boolean isBST(Node node, int min, int max) {

		if (node.isNull())
			return true;
		int value = Integer.parseInt(node.data());

		if (value < min || value > max)
			return false;

		return isBST(node.left(), min, value)
				&& isBST(node.right(), value + 1, max);
	}
	
	public Iterator<String> postOrderIterator() {
		return new PostOrderIterator(root);
	}
	
	public Iterator<String> inOrderIterator() {
		return new InOrderIterator(root);
	}
	
	public Iterator<String> preOrderIterator() {
		return new PreOrderIterator(root);
	}
	
	public void levelOrder() {
		List<Node> level;
		List<Node> childrens = new ArrayList<Node>();;
		Iterator<Node> it;
		
		level = new ArrayList<Node>();
		level.add(root);

		while(true) {
			it = level.iterator();
			System.out.println();
			if(it.hasNext()) {
				while(it.hasNext()) {
					Node current = it.next();
					System.out.print(current.data() + " ");
					Node left = current.left();
					Node right = current.right();
					if(!left.isNull())
						childrens.add(left);
					if(!right.isNull())
						childrens.add(right);
				}
			}
			else
				break;
			level.clear();
			level.addAll(childrens);
			childrens.clear();
		}
	}
	
	public void printVerticalPaths() {
		verticalPathHelper(root, "");
	}
	
	private void verticalPathHelper(Node node,String path) {
		if(node.isLeaf()) {
			System.out.println(path + " " + node.data());
		}
		else {
			if(!node.left().isNull())
				verticalPathHelper(node.left(), path + " " + node.data());
			if(!node.right().isNull())
				verticalPathHelper(node.right(), path + " " + node.data());
		}
	}
	
	public boolean hasPathSum(int sum) {
		return hasPathSumHelper(root, sum);
	}
	
	private boolean hasPathSumHelper(Node node,int sum) {
		if(node.isNull()) {
			return sum == 0;
		}
		else {
			boolean left = false,right = false;
			left = hasPathSumHelper(node.left(),sum - Integer.parseInt(node.data()));
			right = hasPathSumHelper(node.right(),sum - Integer.parseInt(node.data()));
			return left || right;
		}
	}
	
	// OO solution
	public void mirror() {
		root.mirror();
	}
	
	/**
	 * @param root
	 * 
	 * cases :
	 * 1. root is null
	 * 2. root is the only element
	 * 3. complete tree
	 * 4. skewed tree
	 */
	public void mirror(Node root) {
		if(root == null) 
			return;
		else {
			Node left = root.left();
			mirror(root.left());
			mirror(root.right());
			root.insertLeft(root.right());
			root.insertRight(left);
		}
	}
	
	public int treeSum() {
		return root.subTreeSum();
	}
	
	public void elementWithSum(int sum) {
		
	}
	
	public Node lca(Node n1,Node n2) {
		Set<String> nodesEncountered = new HashSet<String>();
		
		while(n1 != null || n2!= null) {
			if(nodesEncountered.contains(n1.data()))
				return n1;
			else
				nodesEncountered.add(n1.data());
			
			if(nodesEncountered.contains(n2.data()))
				return n2;
			else
				nodesEncountered.add(n2.data());
			
			if(n1.parent() != null && n1.parent().isNull()) {
				n1 = n1.parent(); 
			}
			
			if(n2.parent() != null && n2.parent().isNull()) {
				n2 = n2.parent(); 
			}
		}
		return null;
	}
	
	public List<Node> path(Node key,Node root) {
		List<Node> path = new ArrayList<Node>();
		if(pathUtil(key, root, path))
			return path;
		return null;
	}
	
	public boolean pathUtil(Node key,Node root,List<Node> path) {
		if(root == null)
			return false;
		
		// if the key to be found is the same as root
		if(root.value() == key.value()) {
			path.add(root);
			return true;
		}
		
		// else find in left and right subtree
		path.add(root);
		if(pathUtil(key, root.left(), path) || pathUtil(key, root.right(), path))
			return true;
		path.remove(root);	// back track if not present in this tree
		return false;
	}
}

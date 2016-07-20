package trees.binarytree.balanced;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Red Black Trees :
 * RB trees are BST's which are balanced. This means the queries like get,predecessor,successor,max,min run in O(lgn) time.
 * where n is the total number of internal nodes in the tree.
 * 
 * Properties of RB trees :
 * 1. Every node is either red or black.
 * 2. Root and leaves(nil/null nodes) are always black
 * 3. Every Red node must have a black parent
 * 4. All simple paths from a given root node to the leaves descending from the given node have equal/same black height.
 * 
 * Black Height : Maximum number of black node from a given node to a path till leaf node.
 * 
 *  References : http://algs4.cs.princeton.edu/33balanced/RedBlackBST.java.html
 *  
 * @author Prasad
 *
 * @param <Key>		The key over which the BST is constructed
 * @param <Value>	The value which is stored corresponding to the keys
 */
public class RedBlackTree <Key extends Comparable<Key>,Value> {
	private static boolean RED = true;
	private static boolean BLACK = false;
	
	private Node root;
	
	private class Node {
		private Key key;
		private Value value;
		private Node left,right;
		private Node parent;		// may not be used for this class
		private boolean color;		// color of node
		private int n;				// subtree count
		
		public Node(Key key,Value value,boolean color,int n) {
			this.key = key;
			this.value = value;
			this.color = color;
			this.n = n;
		}
	}

	/*
	 *  size of subtree
	 */
	
	public int size() {
		return size(root);
	}
	
	private int size(Node h) {
		if(h == null)
			return 0;
		return h.n;
	}
	
	/*
	 * is tree empty
	 */
	
	public boolean isEmpty() {
		return root == null;
	}
	
	/*
	 * Get a value from RB tree
	 */
	
	public Value get(Key key) {
		return get(root,key);
	}
	
	/**
	 * Find a value associated with a given key in a subtree rooted at h.
	 * 
	 * @param h		subtree node
	 * @param key	key
	 * @return		value associated with key /null if key is not present in tree 
	 */
	private Value get(Node h,Key key) {
		while(h!=null) {
			int cmp = key.compareTo(h.key);
			if(cmp < 0)
				h = h.left;
			else if(cmp > 0)
				h = h.right;
			else
				return h.value;
		}
		return null;
	}
	
	/*
	 *  insert into a binary tree
	 */
	
	public void put(Key key,Value val) {
		root = put(root,key,val);
		root.color = BLACK;
	}
	
	private Node put(Node h,Key key,Value val) {
		if(h == null)
			return new Node(key,val,RED,1);
		
		int cmp = key.compareTo(h.key);
		if(cmp<0)
			h.left = put(h.left,key,val);
		else if(cmp>0)
			h.right = put(h.right,key,val);
		else
			h.value = val;
		
		// fix the red black property
		if(isRed(h.right) && !isRed(h.left))
			h = rotateLeft(h);
		if(isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
		if(isRed(h.left) && isRed(h.right))
			flipColors(h);
		
		h.n = size(h.left) + size(h.right) + 1;
		return h;
	}
	
	// is red util
	private boolean isRed(Node h) {
		if(h == null)
			return false;
		return h.color == RED;
	}
	
	// rotate functions
	/**
	 * Rotate the left - left link rooted at h to balance it. Also re-color the nodes and
	 * adjust the subtree counts
	 * 
	 * @param h node to be rotated left
	 * @return Node which is the new root
	 */
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		
		// adjust parents
		x.parent = h.parent;
		h.parent = x;
		
		// adjust colors
		x.color = x.left.color;
		x.left.color = RED;
		
		// adjust the size of subtrees
		x.n = h.n;
		h.n = size(h.left) + size(h.right) + 1;
		
		return x;
	}
	
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		
		// adjust parent
		x.parent = h.parent;
		h.parent = x;
		
		// re-color nodes
		x.color = x.right.color;
		x.right.color = RED;
		
		// adjust subtree size
		x.n = h.n;
		h.n = size(h.left) + size(h.right) + 1;
		
		return x;
	}
	
	/**
	 * Flips the colors of the node and its children
	 * 
	 * @param h
	 */
	private void flipColors(Node h) {
		// h must have opposite color then its children
		// the children's usually have same colors
		// usually the node has color black and its two children's are red
		// we flip the colors and the root becomes red and two children's become black
		// the black height remains same and also there are no consecutive red children's in the tree
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
	}
	
	/*
	 * print level wise
	 */
	
	public void bfs() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		
		while(!q.isEmpty()) {
			Queue<Node> childrens = new LinkedList<Node>();
			System.out.println();
			// print elements at the same level and add collect the next level children's
			while(!q.isEmpty()) {
				Node current = q.remove();
				System.out.print("(" + current.key + "," + current.value + ") ");
				if(current.left != null)
					childrens.add(current.left);
				if(current.right != null)
					childrens.add(current.right);
			}
			q.addAll(childrens);
			childrens.clear();
		}
	}
	
	
	public static void main(String[] args) {
		RedBlackTree<String, Integer> st = new RedBlackTree<String, Integer>();
		String[] str = {"s","e","a","r","c","h","e","x","a","m","p","l","e"};
        for (int i = 0; i <str.length; i++) {
            String key = str[i];
            st.put(key, i);
        }
        st.bfs();
        //for (String s : st.keys())
          //  StdOut.println(s + " " + st.get(s));
        //StdOut.println();
	}
}





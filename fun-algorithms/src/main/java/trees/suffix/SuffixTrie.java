package trees.suffix;

import java.util.HashMap;
import java.util.Map;

public class SuffixTrie {
	Node root;
	
	public SuffixTrie() {
		root = new Node();
	}
	
	/**
	 * Naive algorithm.
	 * Time complexity of construction suffix tree = O(n^2)
	 * 
	 * Ukkonens Algorithm does this in O(n) time, using a online algorithm
	 * @param s
	 */
	public void construct(String s) {
		int i = 0;
		s = s+'$';
		while(i<s.length()) {
			String sub = s.substring(i);
			int index = 0;
			Node current = root;
			while(index < sub.length()) {
				if(current.contains(sub.charAt(index))) {
					current = current.get(sub.charAt(index));
				} else {
					current.add(sub.charAt(index));
					current = current.get(sub.charAt(index));
				}
				index++;
			}
			i++;
		}
	}
	
	public boolean hasSubstring(String s) {
		return nodeEndingIn(s) != null;
	}
	
	private Node nodeEndingIn(String s) {
		int i = 0;
		Node current = root;
		while(i<s.length()) {
			if(current.contains(s.charAt(i))) {
				current = current.get(s.charAt(i));
			} else
				return null;
			i++;
		}
		
		return current;
	}
	
	public boolean hasSuffix(String suffix) {
		Node next = nodeEndingIn(suffix);
		return next.contains('$');
	}
	
	class Node {
		char key;
		Node parent;
		Map<Character,Node> childrens;
		
		public Node() {
			parent = null;
			childrens = new HashMap<Character,Node>();
		}
		
		public Node(char k,Node p) {
			parent = p;
			key = k;
			childrens = new HashMap<Character,Node>();
		}
		
		public void add(char key) {
			if(!childrens.containsKey(key)) {
				childrens.put(key, new Node(key,this));
			}
		}
		
		public Node get(char key) {
			return childrens.get(key);
		}
		
		public boolean contains(char key) {
			return childrens.containsKey(key);
		}
	}
}

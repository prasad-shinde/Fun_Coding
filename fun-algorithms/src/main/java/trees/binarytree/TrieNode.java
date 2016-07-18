package trees.binarytree;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TrieNode {
	private char value;
	private TrieNode parent;
	private Map<Character,TrieNode> childrens;

	public TrieNode() {
		parent = null;
		childrens = new HashMap<Character,TrieNode>();
	}
	
	public TrieNode(char data,TrieNode parent) {
		value = data;
		this.parent = parent;
		childrens = new HashMap<Character,TrieNode>();
	}
	
	public Collection<TrieNode> childrens() {
		return childrens.values();
	}
	
	public void addChildren(char data) {
		TrieNode node = new TrieNode(data, this);
		childrens.put(data, node);
	}
	
	public char value() {
		return value;
	}
	
	public int numberOfChildrens() {
		return childrens.size();
	}
	
	public boolean hasChild(char c) {
		return childrens.containsKey(c);
	}
	
	public TrieNode getChild(char c) {
		if(childrens.containsKey(c))
			return childrens.get(c);
		return null;
	}
	
	public TrieNode parent() {
		return parent;
	}
	
	public void addString(String str) {
		if(str.length() == 0)
			return;
		char c = str.charAt(0);
		if(!childrens.containsKey(c))
			addChildren(c);
		TrieNode child = childrens.get(c);
		child.addString(str.substring(1));
	}
	
	public void addChildren(TrieNode child) {
		childrens.put(child.value(), child);
	}
}

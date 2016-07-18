package trees.binarytree;

import strings.StringReverse;


/**
 * @author Prasad
 * Trie implementation.
 * Deals with following :
 * 1. find the unique prefix for every string in the list
 * 2. we can find a common prefix for a list of strings
 * 		just return the string before the node with 2 or more childrens
 */
public class Trie {
	private TrieNode root;
	//the root does not store any data
	
	public Trie() {
		root = new TrieNode();
	}
	
	public void addString(String str) {
		root.addString(str);
	}
	
	
	/*
	 *  Use the shorest unique prefix to represent each word in the array 
		input: ["zebra", "dog", "duck",”dove”] 
		output: {zebra: z, dog: do, duck: du} 
		[zebra, dog, duck, dove] 
		{zebra:z, dog: dog, duck: du, dove: dov}
	 * */
	public String uniquePrefix(String str) {
		TrieNode temp = root;
		int i = 0;
		while(i < str.length()) {
			if(temp.hasChild(str.charAt(i))) {
				temp = temp.getChild(str.charAt(i));
				i++;
			}
			else {
				throw new ArrayIndexOutOfBoundsException("character " + str.charAt(i) + "is not present");
			}			
		}
		
		while(temp.parent().numberOfChildrens() <= 1) {
			temp = temp.parent();
		}
		
		StringBuffer prefix = new StringBuffer();
		if(temp != root) {
			while(temp != root) {
				prefix.append(temp.value());
				temp = temp.parent();
			}
		}
		else
			prefix.append(str.charAt(0));
		return StringReverse.reverse(prefix.toString());
	}
}

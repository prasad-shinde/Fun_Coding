package trees.binarytree;

public class TestTrie {
	public static void main(String[] args) {
		Trie tree = new Trie();
		
		tree.addString("zebra");
		tree.addString("dog");
		tree.addString("duck");
		tree.addString("dove");
		
		System.out.print("Unique Prefix : " + tree.uniquePrefix("zebra"));
	}
}

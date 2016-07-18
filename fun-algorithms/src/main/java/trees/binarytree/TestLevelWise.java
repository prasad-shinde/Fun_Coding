package trees.binarytree;

public class TestLevelWise {
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
		System.out.println(bst.inorder());
		bst.levelOrder();
		System.out.println("Vertical paths : ");
		bst.printVerticalPaths();
		System.out.println("has path sum : " + bst.hasPathSum(41));
		System.out.println("is bst : " + bst.isBST());
	}
}

package zillow.tree;

import java.util.ArrayList;
import java.util.List;

public class ZTestTrinaryTree {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(5);
		list.add(4);
		list.add(9);
		list.add(5);
		list.add(7);
		list.add(2);
		list.add(2);
		ZTrinaryTree tree = new ZTrinaryTree();
		tree.addAll(list);
		System.out.print("\n after adding 5,4,9,5,7,2 and 2");
		System.out.print("\n Inorder traversal");
		tree.printInorder();
		System.out.print("\n after deleting 4");
		tree.delete(4);
		System.out.print("\n Inorder traversal");
		tree.printInorder();
		
		// You can also check the @TestZTrinaryTree file where I have test cases for add and delete
		/* Please run the program to check for yourself
		 * Output :
		 *  after adding 5,4,9,5,7,2 and 2
 			Inorder traversalInorder : 2245579

 			after deleting 4
 			Inorder traversalInorder : 225579
		 * 
		 * */
	}
}

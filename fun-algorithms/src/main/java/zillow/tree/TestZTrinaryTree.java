package zillow.tree;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestZTrinaryTree {
	private ZTrinaryTree tree;
	
	public void pre() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(5);
		list.add(4);
		list.add(9);
		list.add(5);
		list.add(7);
		list.add(2);
		list.add(2);
		tree = new ZTrinaryTree();
		tree.addAll(list);
	}
	
	@Test
	public void testAdd() {
		pre(); // use the JUnit function of initialization instead of this.
		assertEquals("Inorder output", "2245579", tree.inorder());
	}

	@Test
	public void testDelete() {
		pre();
		tree.delete(4);
		assertEquals("Inorder output", "225579", tree.inorder());
	}
}

package zillow.tree;

import java.util.NoSuchElementException;

/**
 * @author Prasad
 * 
 * This is Zillow Null node class. I have used singleton pattern to have just one instance 
 * of the Null node. This helps in saving a lot of memory.
 */
public class ZNullNode extends ZNode {
	public static ZNullNode instance = null;
	
	/**
	 * private constructor so that no one can create a object of this class.
	 */
	private ZNullNode() {
	}
	
	/**
	 * returns the singleton of the ZNullNode class.
	 * @return instance its the unique instance of this class.
	 */
	public static ZNullNode getInstance() {
		if(instance == null) {
			instance = new ZNullNode();
		}
		return instance;
	}
	
	@Override
	public void insert(int value) {
	}

	/* (non-Javadoc)
	 * @see zillow.tree.ZNode#delete(int)
	 * 
	 * if we reach the null nodes while deleting.
	 * it means there is no such value in the tree. We can ignore it or throw an exception.
	 * I have thrown an exception in this case.
	 */
	@Override
	public void delete(int value) {
		throw new NoSuchElementException("The value " + value + " is not present in the tree");
	}

	@Override
	public int value() {
		return 0;
	}

	@Override
	public boolean isNull() {
		return true;
	}

	/* (non-Javadoc)
	 * @see zillow.tree.ZNode#inorder()
	 * 
	 * The inorder traversal of a null node returns a blank string.
	 * This helps us achieve polymorphism and avoid if statements in the Ternary node class.
	 */
	@Override
	public String inorder() {
		return "";
	}

	@Override
	public ZNode left() {
		return null;
	}

	@Override
	public ZNode right() {
		return null;
	}

	@Override
	public ZNode mid() {
		return null;
	}

	@Override
	public void setLeft(ZNode node) {
	}

	@Override
	public void setRight(ZNode node) {
	}

	@Override
	public ZNode parent() {
		return null;
	}

	@Override
	public void updateParent(ZNode node) {
	}
}

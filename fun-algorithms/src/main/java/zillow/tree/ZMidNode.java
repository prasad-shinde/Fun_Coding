package zillow.tree;

/**
 * @author Prasad
 * This node deals with the mid node. 
 * The mid node does not have any links from it. It can be considered as a leaf node. 
 */
public class ZMidNode extends ZNode {
	private int value;
	private ZNode parent;

	public ZMidNode(int val,ZNode parent) {
		value = val;
		this.parent = parent;
	}
	
	@Override
	public void insert(int value) {
	}

	@Override
	public void delete(int value) {
		
	}

	@Override
	public int value() {
		return value;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	/* (non-Javadoc)
	 * @see zillow.tree.ZNode#inorder()
	 * 
	 * Inorder of this node would be its value.
	 * as there are no outgoing links from this node.
	 */
	@Override
	public String inorder() {
		return toString();
	}
	
	public String toString() {
		return value+"";
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
		return parent;
	}

	@Override
	public void updateParent(ZNode node) {
		parent = node;
	}
}

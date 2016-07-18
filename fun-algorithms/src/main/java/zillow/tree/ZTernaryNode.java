package zillow.tree;

/**
 * @author Prasad
 * The Ternary node. Each of this node has a data value and 3 pointers :
 * left, right and mid.
 */
public class ZTernaryNode extends ZNode {
	private int value;
	private ZNode left,right,mid;
	private ZNode parent;
	
	public ZTernaryNode(int val) {
		value = val;
		left = right = mid = ZNullNode.getInstance();	// use Null Design pattern and singleton design pattern
		parent = ZNullNode.getInstance();				// save a lot of space
	}
	
	/**
	 * @param val
	 * @param parent
	 * 
	 * creates a ternary node and assigns a parent to it.
	 */
	public ZTernaryNode(int val,ZNode parent) {
		value = val;
		left = right = mid = ZNullNode.getInstance();	// use Null Design pattern
		this.parent = parent;
	}
	
	/* (non-Javadoc)
	 * @see zillow.tree.ZNode#insert(int)
	 * 
	 * inserts into the ternary tree.
	 * 1. if value to be inserted is same -> then its inserted at mid node
	 * 2. if its less -> it goes in the left subtree
	 * 3. if its more -> it goes into the right subtree
	 */
	@Override
	public void insert(int value) {
		if(value ==  this.value) {
			mid = new ZMidNode(value,this);
		}
		else if(value < this.value) {
			if(left.isNull()) {
				left = new ZTernaryNode(value,this);
			} else {
				left.insert(value);
			}
		} else {
			if(right.isNull()) {
				right = new ZTernaryNode(value,this);
			} else {
				right.insert(value);
			}
		}
	}

	/* (non-Javadoc)
	 * @see zillow.tree.ZNode#delete(int)
	 * 
	 * deletes a node from the tree if its value equals the give value. 
	 * The mid link for that particular node is also deleted.
	 */
	@Override
	public void delete(int value) {
		if(value == this.value) {	// if matches delete this node
			ZNode zleft = left;
			ZNode zright = right;
			
			boolean isLeft;
			if(this == parent.left()) {	// check if its a left or right child
				isLeft = true;
			} else {
				isLeft = false;
			}
			
			if(isLeft) {	// delete the node and replace by its left child
				parent.setLeft(zleft);
			} else {
				parent.setRight(zleft);
			}
			zleft.updateParent(parent);
			
			while(!zleft.right().isNull()) {	//update rest of the pointers
				zleft = zleft.left();
			}
			zleft.setRight(zright);
			zright.updateParent(zleft);
		} else if (value < this.value) {
			left.delete(value);
		} else {
			right.delete(value);
		}
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
	 * The null pattern helps us avoid the if loops here in the in order traversal.
	 * for the null node the inorder just returns a blank string.
	 */
	@Override
	public String inorder() {
		return left.inorder() + toString() + mid.inorder() + right.inorder();
	}
	
	public String toString() {
		return value+"";
	}

	@Override
	public ZNode left() {
		return left;
	}

	@Override
	public ZNode right() {
		return right;
	}

	@Override
	public ZNode mid() {
		return mid;
	}

	@Override
	public void setLeft(ZNode node) {
		left = node;
	}

	@Override
	public void setRight(ZNode node) {
		right = node;		
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

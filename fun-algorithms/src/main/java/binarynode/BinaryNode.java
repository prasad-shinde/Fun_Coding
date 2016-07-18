package binarynode;

public class BinaryNode extends Node {
	protected int data;
	protected Node left;
	protected Node right;
	protected Node parent;
	
	public BinaryNode(int value,Node parent) {
		data = value;
		left = new NullNode(this,true);
		right = new NullNode(this,false);
		this.parent = parent;
	}
	
	public int value() {
		return data;
	}
	
	@Override
	public Node left() {
		return left;
	}

	@Override
	public Node right() {
		return right;
	}

	@Override
	public Node parent() {
		return parent;
	}

	@Override
	public int height() {
		return 1 + Math.max(left.height(), right.height());
	}

	@Override
	public boolean insert(int value) {
		if(data > value)
			return left.insert(value);
		else
			return right.insert(value);
	}

	@Override
	public boolean insertLeft(int value) {
		left = new BinaryNode(value,this);
		return true;
	}

	@Override
	public boolean insertRight(int value) {
		right = new BinaryNode(value,this);
		return true;
	}
	
	public String inorder() {
		return (left.inorder() + " " + data + " " + right.inorder() + " ");
	}

	@Override
	public String preorder() {
		return (data + " " + left.preorder() + " " + right.preorder() + " ");
	}

	@Override
	public String postorder() {
		return (left.postorder() + " "+ right.postorder() + " " + data + " ");
	}

	@Override
	public String data() {
		return data+"";
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public boolean insertLeft(Node value) {
		left = value;
		return true;
	}

	@Override
	public boolean insertRight(Node value) {
		right = value;
		return true;
	}

	@Override
	public boolean modifyparent(Node value) {
		parent = value;
		return true;
	}

	@Override
	public void mirror() {
		Node temp = left;
		left = right;
		right = temp;
		left.mirror();
		right.mirror();
	}

	@Override
	public boolean isLeaf() {
		return left.isNull() && right.isNull();
	}

	@Override
	public int subTreeSum() {
		return data + left.subTreeSum() + right.subTreeSum();
	}
}

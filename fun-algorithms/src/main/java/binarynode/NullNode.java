package binarynode;

public class NullNode extends Node {
	Node parent;
	boolean isLeft;
	
	public NullNode(Node parent,boolean isLeft) {
		this.parent = parent;
		this.isLeft = isLeft;
	}

	@Override
	public Node left() {
		return null;
	}

	@Override
	public Node right() {
		return null;
	}

	@Override
	public Node parent() {
		return parent;
	}

	@Override
	public int height() {
		return 0;
	}

	@Override
	public boolean insert(int value) {
		if(isLeft)
			return this.insertLeft(value);
		else
			return this.insertRight(value);
	}

	@Override
	public boolean insertLeft(int value) {
		return parent.insertLeft(value);
	}

	@Override
	public boolean insertRight(int value) {
		return parent.insertRight(value);
	}

	@Override
	public String inorder() {
		return "";
	}

	@Override
	public String preorder() {
		return "";
	}

	@Override
	public String postorder() {
		return "";
	}
	
	@Override
	public String data() {
		return "";
	}

	@Override
	public boolean isNull() {
		return true;
	}

	@Override
	public boolean insertLeft(Node value) {
		parent.insertLeft(value);
		return true;
	}

	@Override
	public boolean insertRight(Node value) {
		parent.insertRight(value);
		return true;
	}

	@Override
	public boolean modifyparent(Node value) {
		parent = value;
		return true;
	}

	@Override
	public void mirror() {
		
	}

	@Override
	public boolean isLeaf() {
		return false;
	}

	@Override
	public int subTreeSum() {
		return 0;
	}

	@Override
	public int value() {
		return 0;
	}
}

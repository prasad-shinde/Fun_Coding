package binarynode;

public class AVLNode extends BinaryNode {
	private int bf;
	public AVLNode(int value, Node parent) {
		super(value, parent);
		bf = 0;
	}

	public int balanceFactor() {
		bf = left.height() - right.height();
		return bf;
	}
	
	@Override
	public boolean insert(int value) {
		if(data > value)
			return left.insert(value);
		else
			return right.insert(value);
	}
	
	public void normalize() throws Exception {
		balanceFactor();
		if(bf >= 2) {
			int lbf = ((AVLNode)left).balanceFactor();
			if(lbf == -1) {
				((AVLNode)left).rr_rotate();
			}
			else if(lbf == 1) {
				ll_rotate();
			}
			else
				throw new Exception("Invalid Balance factor : " + lbf);
		}
		else if(bf <= -2) {
			int lbf = ((AVLNode)right).balanceFactor();
			if(lbf == -1) {
				rr_rotate();
			}
			else if(lbf == 1) {
				((AVLNode)right).ll_rotate();
			}
			else
				throw new Exception("Invalid Balance factor : " + lbf);
		}
	}
	
	private void ll_rotate() {
		this.modifyparent(left);
		this.insertLeft(left.right());
		left.insertRight(this);
	}
	
	private void rr_rotate() {
		this.modifyparent(right);
		this.insertRight(right.left());
		right.insertLeft(this);
	}

	@Override
	public boolean insertLeft(int value) {
		left = new AVLNode(value,this);
		this.balanceFactor();
		try {
			((AVLNode)parent).normalize();
		} catch (Exception e) {
			System.out.println("Error while inserting left");
		}
		return true;
	}

	@Override
	public boolean insertRight(int value) {
		right = new AVLNode(value,this);
		this.balanceFactor();
		try {
			((AVLNode)parent).normalize();
		} catch (Exception e) {
			System.out.println("Error while inserting right");
		}
		return true;
	}
	
	
}

package mulesoft.robotblock;

public class Block {
	private int blockNo;
	private int stackNo;
	
	public Block(int position) {
		blockNo = position;
		stackNo = position;
	}
	
	public int onStack() {
		return stackNo;
	}
	
	public int blockNumber() {
		return blockNo;
	}
	
	public void updateStack(int newStack) {
		stackNo = newStack;
	}
	
	public String toString() {
		return blockNo + "";
	}
}

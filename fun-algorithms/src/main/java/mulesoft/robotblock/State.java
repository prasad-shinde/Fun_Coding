package mulesoft.robotblock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class State {
	private List<Stack<Block>> list;
	private int n;
	
	// initializes the state for the size n
	public State(int n) {
		BlockFactory blockFactory = BlockFactory.getInstance();
		list = new ArrayList<Stack<Block>>();
		this.n = n;
		
		for(int i=0;i<n;i++) {
			Stack<Block> s = new Stack<Block>();
			Block b = blockFactory.getBlock(i);
			s.push(b);
			list.add(s);
		}
	}
	
	public void move(int blockA,int blockB) {
		if(!isValidMove(blockA,blockB))
			return;
		
		BlockFactory blockFactory = BlockFactory.getInstance();
		Block a = blockFactory.getBlock(blockA);
		Block b = blockFactory.getBlock(blockB);
		
		Stack<Block> stackA = list.get(a.onStack());
		Stack<Block> stackB = list.get(b.onStack());
		
		adjustStack(stackA,blockA);
		adjustStack(stackB,blockB);
		
		Block top = stackA.pop();
		top.updateStack(b.onStack());
		stackB.push(top);
	}
	
	private void adjustStack(Stack<Block> s,int blockNumber) {
		while(s.peek().blockNumber() != blockNumber) {
			Block currentBlock = s.pop();
			int blockIndex = currentBlock.blockNumber();
			list.get(blockIndex).push(currentBlock);
			currentBlock.updateStack(blockIndex);
		}
	}
	
	private boolean isValidMove(int a,int b) {
		if(a == b)
			return false;
		
		BlockFactory blockFactory = BlockFactory.getInstance();
		
		Block blockA = blockFactory.getBlock(a);
		Block blockB = blockFactory.getBlock(b);
		
		if(blockA.onStack() == blockB.onStack())
			return false;
		return true;
	}
	
	public void quit() {
		// may be exit(0);
	}
	
	public String toString() {
		int i = 0;
		StringBuffer sb = new StringBuffer();
		
		for(Stack<Block> s:list) {
			sb.append(i + ": ");
			Iterator<Block> it = s.iterator();
			while(it.hasNext()) {
				sb.append(it.next().toString() + " ");
			}
			sb.append("\n");
			i++;
		}
		
		return sb.toString();
	}
}

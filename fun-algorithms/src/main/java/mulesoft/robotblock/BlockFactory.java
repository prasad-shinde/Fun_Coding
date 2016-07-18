package mulesoft.robotblock;

import java.util.HashMap;
import java.util.Map;

public class BlockFactory {
	private Map<Integer,Block> factory;
	private static BlockFactory instance = null;
	
	private BlockFactory() {
		factory = new HashMap<Integer,Block>();
	}
	
	public static BlockFactory getInstance() {
		if(instance == null)
			instance = new BlockFactory();
		return instance;
	}
	
	public Block getBlock(int blockNumber) {
		if(factory.containsKey(blockNumber)) {
			return factory.get(blockNumber);
		} else {
			Block b = new Block(blockNumber);
			factory.put(blockNumber, b);
			return b;
		}
	}
}

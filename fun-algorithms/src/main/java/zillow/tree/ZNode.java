package zillow.tree;

/**
 * @author Prasad
 * 
 * Abstract class for the Zillow challenge. It helps us achieve polymorphism.
 */
public abstract class ZNode {
	public abstract void insert(int value);
	public abstract void delete(int value);
	public abstract int value();
	public abstract boolean isNull();
	public abstract String inorder();
	public abstract ZNode left();
	public abstract ZNode right();
	public abstract ZNode mid();
	public abstract ZNode parent();
	public abstract void setLeft(ZNode node);
	public abstract void setRight(ZNode node);
	public abstract void updateParent(ZNode node);
}

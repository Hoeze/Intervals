

public class IntervalTreeNode<T extends DiscreteIntervalImpl> {
	
	public static final boolean BLACK = true;
	public static final boolean RED = false;
	
	T interval;
	IntervalTreeNode<T> parent;
	IntervalTreeNode<T> rightChild;
	IntervalTreeNode<T> leftChild;
	boolean colour;
	int maxEndpoint;
	

}

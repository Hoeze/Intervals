

public interface Interval<T extends Number> {
	
	
	
	/**
	 * @param other Interval to compareTo
	 * @return	1, if this Interval is right of the other interval
	 * 			0, if they are overlapping
	 * 			-1, if this is left of the other interval
	 * 			-99, if the status could not be determined
	 */
	short getRelationTo(Interval<T> other);
	
	default boolean overlapsWith(Interval<T> other){
		return this.getRelationTo(other) == 0;
	}
	
	default boolean isRightOf(Interval<T> other){
		return this.getRelationTo(other) == 1;
	}
	
	default boolean isLeftOf(Interval<T> other){
		return this.getRelationTo(other) == -1;
	}
	
	public T getStart();
	
	public T getEnd();
	
	public T getLength();
	
	/**
	 * @param other Interval
	 * @return the Overlap between this and the other interval as a new interval
	 * 		   null, if there is no overlap
	 */
	public Interval<T> getIntersection(Interval<T> other);
	
	/**
	 * @param other Interval
	 * @return the union between this and the other interval as a new interval
	 * 		   null, if there is no overlap and they are not adjacent
	 */
	public Interval<T> getUnion(Interval<T> other);

}



public class DiscreteIntervalImpl implements Interval<Integer>{
	
	private int start;
	private int end;
	
	public  DiscreteIntervalImpl(int start, int end, boolean zeroBased) {
		if(zeroBased){
			this.start = start;
			this.end = end;
		}else{
			this.start = start - 1;
			this.end = end - 1;
		}
	}

	@Override
	public Integer getStart() {
		return this.start;
	}

	@Override
	public Integer getEnd() {
		return this.end;
	}

	@Override
	public Integer getLength() {
		return end - start + 1;
	}

	@Override
	public String toString() {
		return this.start + ":" + this.end;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + end;
		result = prime * result + start;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DiscreteIntervalImpl){
			DiscreteIntervalImpl otherInterval = (DiscreteIntervalImpl) obj;
			return this.start == otherInterval.start && this.end == otherInterval.end;
		}else{
			return false;
		}
	}

	@Override
	public short getRelationTo(Interval other) {
		if(other instanceof Interval){
			Interval otherInterval = (Interval) other;
			if(this.getEnd() < otherInterval.getStart().intValue()){
				return -1;
			}else if(otherInterval.getEnd().intValue() < this.getStart()){
				return 1;
			}else{
				return 0;
			}
		}else{
			return -99;
		}
	}

	@Override
	public Interval getIntersection(Interval other) {
		if(this.overlapsWith(other)){
			int overlapStart = 0;
			int overlapEnd = 0;
			// gets rightmost start
			if(this.getStart() < other.getStart().intValue()){
				overlapStart = other.getStart().intValue();
			}else{
				overlapStart = this.getStart();
			}
			
			// gets leftmost end
			if(this.getEnd() < other.getEnd().intValue()){
				overlapEnd = this.getEnd();
			}else{
				overlapEnd = other.getEnd().intValue();
			}
			
			return new DiscreteIntervalImpl(overlapStart, overlapEnd, true);
		}else{
			return null;
		}
	}

	@Override
	public Interval getUnion(Interval other) {
		if(this.overlapsWith(other)){
			int overlapStart = 0;
			int overlapEnd = 0;
			// gets leftmost start
			if(this.getStart() < other.getStart().intValue()){
				overlapStart = this.getStart();
			}else{
				overlapStart = other.getStart().intValue();
			}
			
			// gets rightmost end
			if(this.getEnd() < other.getEnd().intValue()){
				overlapEnd = other.getEnd().intValue();
			}else{
				overlapEnd = this.getEnd();
			}
			
			return new DiscreteIntervalImpl(overlapStart, overlapEnd, true);
		}else if(this.isLeftAdjacent(other)){
			return new DiscreteIntervalImpl(this.getStart(), other.getStart().intValue(), true);
		}else if(this.isRightAdjacent(other)){
			return new DiscreteIntervalImpl(other.getStart().intValue(), this.getStart(), true);
		}else{
			return null;
		}
	}

	private boolean isRightAdjacent(Interval other) {
		return other.getEnd().intValue() + 1 == this.getStart();
	}

	private boolean isLeftAdjacent(Interval other) {
		return this.getEnd() + 1 == other.getStart().intValue();
	}
	
}

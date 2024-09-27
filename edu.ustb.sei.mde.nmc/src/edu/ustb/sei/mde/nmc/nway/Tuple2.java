package edu.ustb.sei.mde.nmc.nway;

public class Tuple2<F, S> {
	
	public final F first;	
	public final S second;
	
	public Tuple2(F first, S second) {
		super();
		this.first = first;
		this.second = second;
	}

	public F getFirst() {
		return first;
	}

	public S getSecond() {
		return second;
	}

	@Override
	public String toString() {
		return "Tuple2["
				+ "\n分支=" + first + ", "
				+ "\n对象=" + second + "]";
	}
		
}

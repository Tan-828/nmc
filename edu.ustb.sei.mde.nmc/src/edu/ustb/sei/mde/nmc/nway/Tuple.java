package edu.ustb.sei.mde.nmc.nway;

//��Ԫ��
public class Tuple<F, S> {
	
	public final F first;	
	// lyt-Ϊ�˼��ǿ����Ļ���ͻ����ʱȥ��final���η�
	public S second;
	
	public Tuple(F first, S second) {
		super();
		this.first = first;
		this.second = second;
	}
	
	static public <F,S> Tuple<F,S> make(F f, S s) {
		return new Tuple<F, S>(f, s);
	}
	
	@Override
	public int hashCode() {
		int firstCode = first==null ? 0 : first.hashCode();
		int secondCode = second==null ? 0 : second.hashCode();
		return ((firstCode&0xFFFF)<<16) & (secondCode&0xFFFF);
	}
	
	//�ж�����Ԫ����ȵķ���<a,b>=<c, d>���ҽ���a=c��b=d
	@SuppressWarnings("rawtypes")
	public boolean equals(Object o) {
		if(o==null || ! (o instanceof Tuple))
			return false;
		else return ((first!=null && first.equals(((Tuple)o).first)) || (first==null && ((Tuple)o).first==null))
				&& ((second!=null && second.equals(((Tuple)o).second)) || (second==null && ((Tuple)o).second==null));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	static private final Tuple EMPTY = new Tuple(null, null);
	
	@SuppressWarnings("unchecked")
	static public <X,Y> Tuple<X,Y> emptyTuple() {
		return (Tuple<X,Y>)EMPTY;
	}

	
	public String toString() {
		return "<"+first+","+second+">";
	}
	
	public Tuple<F,S> replaceFirst(F f) {
		return Tuple.make(f, second);
	}
	
	// ע��replaceSecond�Ƿ���һ���¶���
	public Tuple<F,S> replaceSecond(S s) {
		return Tuple.make(first, s);
	}
}

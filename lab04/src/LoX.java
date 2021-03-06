// lab04
// http://www.ccs.neu.edu/course/cs2510su13-1/labs/lab4.html
import tester.Tester;

// Ex 04

interface ILoX <X> {
	// RETURNS: a reversion of this list
	ILoX<X> reverse();
	
	// RETURNS: a concatenation of the reversion of this list and the given one
	ILoX<X> reverseAcc(ILoX<X> that);
	
	// RETURNS: appendation of this and that list
	ILoX<X> append(ILoX<X> that);
}

abstract class LoX <X> implements ILoX<X>  {
	// RETURNS: a reversion of this list
	public ILoX<X> reverse() {
		return this.reverseAcc(new MtX<X>());
	}

	// RETURNS: a concatenation of the reversion of this list and the given one
	public abstract ILoX<X> reverseAcc(ILoX<X> that);
	
	// RETURNS: appendation of this and that list
	public abstract ILoX<X> append(ILoX<X> that);
}

class MtX <X> extends LoX<X> {
	MtX() {}
	
	// RETURNS: a concatenation of the reversion of this list and the given one
	public ILoX<X> reverseAcc(ILoX<X> that) {
		return that;
	}
	
	// RETURNS: appendation of this and that list
	public ILoX<X> append(ILoX<X> that) {
		return that;
	}
}

class ConsX <X> extends LoX<X> {
	X fst;
	ILoX<X> rst;
	
	ConsX(X fst, ILoX<X> rst) {
		this.fst = fst;
		this.rst = rst;
	}
	
	// RETURNS: a concatenation of the reversion of this list and the given one
	public ILoX<X> reverseAcc(ILoX<X> that) {
		return this.rst.reverseAcc(new ConsX<X>(this.fst, that));
	}
	
	// RETURNS: appendation of this and that list
	public ILoX<X> append(ILoX<X> that) {
		return new ConsX<X>(this.fst, this.rst.append(that));
	}
}

class LoXExamples {
	// tests for method reverse()
	boolean testReverse(Tester t) {
		ILoX<Integer> mt = new MtX<Integer>();
		ILoX<Integer> l1 = new ConsX<Integer>(1, mt);
		ILoX<Integer> l2 = new ConsX<Integer>(2, l1);
		ILoX<Integer> l3 = new ConsX<Integer>(2, mt);
		ILoX<Integer> l4 = new ConsX<Integer>(1, l3);
		return
		t.checkExpect(mt.reverse(), mt)&&
		t.checkExpect(l2.reverse(), l4);
	}

	
	// tests for method append()
	boolean testAppend(Tester t) {
		ILoX<Integer> mt = new MtX<Integer>();
		ILoX<Integer> l1 = new ConsX<Integer>(1, mt);
		ILoX<Integer> l2 = new ConsX<Integer>(2, l1);
		ILoX<Integer> l3 = new ConsX<Integer>(2, mt);
		ILoX<Integer> l4 = new ConsX<Integer>(1, l3);
		return
		t.checkExpect(l1.append(mt), l1) &&
		t.checkExpect(l2.append(mt), l2) &&
		t.checkExpect(l1.append(l3), l4);
	}
}
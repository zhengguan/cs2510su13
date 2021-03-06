// lab04
// http://www.ccs.neu.edu/course/cs2510su13-1/labs/lab4.html
import tester.Tester;

// Ex 01~03

// REPRESENTS: a list of ints
interface ILoI{
	// RETURNS: a reversion of this list
	ILoI reverse();
	
	// RETURNS: a concatenation of the reversion of this list and the given one
	ILoI reverseAcc(ILoI rev);
	
	// RETURNS: the sum of all elements in this list
	int sum();
	
	// RETURNS: the sum of all elements in this list and the given value;
	// ACCUM: prev represents the sum of elements we seen so far
	int sumAcc(int prev);
	
	// RETURNS: appendation of this and that list
	ILoI append(ILoI that);

}

abstract class LoI implements ILoI {
	// RETURNS: a reversion of this list
	public ILoI reverse() {
		return reverseAcc(new MT());
	}
	
	// RETURNS: a concatenation of the reversion of this list and the given one
	public abstract ILoI reverseAcc(ILoI rev);
	
	// RETURNS: the sum of all elements in this list
	public int sum() {
		return this.sumAcc(0);
	}
	
	// RETRNS: the sum of all elements in this list and the given value;
	// ACCUM: prev represents the sum of elements we seen so far
	public abstract int sumAcc(int prev);
	
	// RETURNS: appendation of this and that list
	public abstract ILoI append(ILoI that);
}

// REPRESENTS: an empty list
class MT extends LoI {
	MT(){}
	
	// RETURNS: a concatenation of the reversion of this list and the given one
	public ILoI reverseAcc(ILoI rev) {
		return rev;
	}
	
	// RETRNS: the sum of all elements in this list and the given value;
	// ACCUM: prev represents the sum of elements we seen so far
	public int sumAcc(int prev) {
		return prev;
	}
	
	// RETURNS: appendation of this and that list
	public ILoI append(ILoI that) {
		return that;
	}
}

// REPRESENTS: a non-empty list
class Cons extends LoI {
	int fst;
	ILoI rst;
	
	Cons(int fst, ILoI rst) {
		this.fst = fst;
		this.rst = rst;
	}
	
	// RETURNS: a concatenation of the reversion of this list and the given one
	public ILoI reverseAcc(ILoI rev) {
		return this.rst.reverseAcc(new Cons(this.fst, rev));
	}
	
	// RETRNS: the sum of all elements in this list and the given value;
	// ACCUM: prev represents the sum of elements we seen so far
	public int sumAcc(int prev) {
		return this.rst.sumAcc(prev + this.fst);
	}
	
	// RETURNS: appendation of this and that list
	public ILoI append(ILoI that) {
		return new Cons(this.fst, this.rst.append(that));
	}
}

class LoIExamples {
	// tests for method reverse()
	boolean testReverse(Tester t) {
		ILoI mt = new MT();
		ILoI l1 = new Cons(1, mt);
		ILoI l2 = new Cons(2, l1);
		ILoI l3 = new Cons(2, mt);
		ILoI l4 = new Cons(1, l3);
		return
		t.checkExpect(mt.reverse(), mt)&&
		t.checkExpect(l2.reverse(), l4);
	}
	
	// tests for method sum()
	boolean testSum(Tester t) {
		ILoI mt = new MT();
		ILoI l1 = new Cons(1, mt);
		ILoI l2 = new Cons(2, l1);
		return
		t.checkExpect(mt.sum(), 0) &&
		t.checkExpect(l1.sum(), 1) &&
		t.checkExpect(l2.sum(), 3);
	}
	
	// tests for method append()
	boolean testAppend(Tester t) {
		ILoI mt = new MT();
		ILoI l1 = new Cons(1, mt);
		ILoI l2 = new Cons(2, l1);
		ILoI l3 = new Cons(2, mt);
		ILoI l4 = new Cons(1, l3);
		return
		t.checkExpect(l1.append(mt), l1) &&
		t.checkExpect(l2.append(mt), l2) &&
		t.checkExpect(l1.append(l3), l4);
	}
}
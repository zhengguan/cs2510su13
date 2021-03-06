// lab05
// http://www.ccs.neu.edu/course/cs2510su13-1/labs/lab5.html

// Ex 01~02

import tester.Tester;


interface ILoX <I> {
	// RETURNS: a reversion of this list
	ILoX<I> reverse();
	
	// RETURNS: a concatenation of the reversion of this list and the given one
	ILoX<I> reverseAcc(ILoX<I> that);
	
	// RETURNS: appendation of this and that list
	ILoX<I> append(ILoX<I> that);
	
	// RETURNS: all elements in this list that satisfy p.predicate()
	boolean ormap(ILoXPredicateVisitor<I> p); 

}

// REPRESENTS: a predicate visitor for ILoX
interface ILoXPredicateVisitor<I> {
	boolean predicate(I i);
}

abstract class LoX <I> implements ILoX<I>  {
	// RETURNS: a reversion of this list
	public ILoX<I> reverse() {
		return this.reverseAcc(new MtX<I>());
	}

	// RETURNS: a concatenation of the reversion of this list and the given one
	public abstract ILoX<I> reverseAcc(ILoX<I> that);
	
	// RETURNS: appendation of this and that list
	public abstract ILoX<I> append(ILoX<I> that);
	
	// RETURNS: all elements in this list that satisfy p.predicate()
	public abstract boolean ormap(ILoXPredicateVisitor<I> p);
	
}

class MtX <I> extends LoX<I> {
	MtX() {}
	
	// RETURNS: a concatenation of the reversion of this list and the given one
	public ILoX<I> reverseAcc(ILoX<I> that) {
		return that;
	}
	
	// RETURNS: appendation of this and that list
	public ILoX<I> append(ILoX<I> that) {
		return that;
	}
	
	// RETURNS: all elements in this list that satisfy p.predicate()
	public boolean ormap(ILoXPredicateVisitor<I> p) {
		return false;
	}
}

class ConsX <I> extends LoX<I> {
	I fst;
	ILoX<I> rst;
	
	ConsX(I fst, ILoX<I> rst) {
		this.fst = fst;
		this.rst = rst;
	}
	
	// RETURNS: a concatenation of the reversion of this list and the given one
	public ILoX<I> reverseAcc(ILoX<I> that) {
		return this.rst.reverseAcc(new ConsX<I>(this.fst, that));
	}
	
	// RETURNS: appendation of this and that list
	public ILoX<I> append(ILoX<I> that) {
		return new ConsX<I>(this.fst, this.rst.append(that));
	}
	
	// RETURNS: all elements in this list that satisfy p.predicate()
	public boolean ormap(ILoXPredicateVisitor<I> p) {
		return p.predicate(this.fst) || this.rst.ormap(p);
	}
}


class LoXExamples {
	ILoX<Integer> mt = new MtX<Integer>();
	ILoX<Integer> l1 = new ConsX<Integer>(1, mt);
	ILoX<Integer> l2 = new ConsX<Integer>(2, l1);
	ILoX<Integer> l3 = new ConsX<Integer>(2, mt);
	ILoX<Integer> l4 = new ConsX<Integer>(1, l3);
	
	// tests for ormap()
	boolean testOrMap(Tester t){
		ILoXPredicateVisitor<Integer> isOdd = new ILoXPredicateVisitor<Integer>() {
			public boolean predicate(Integer i) {
				return i % 2 == 0;
			}
		};
		return
		t.checkExpect(mt.ormap(isOdd), false) &&
		t.checkExpect(l1.ormap(isOdd), false) &&
		t.checkExpect(l3.ormap(isOdd), true);
	}
}
import tester.Tester;

// lab05
// http://www.ccs.neu.edu/course/cs2510su13-1/labs/lab5.html

// Ex 03~08

// Represents a computation over a list of X, producing an R.
interface ListVisitor<X,R> {
	R visitEmpty();
	R visitCons(X first, List<X> rest);
}

// Represents a list of X
interface List<X> {
	// Accept visitor and compute.
	<R> R accept(ListVisitor<X,R> v);
}

class Empty<X> implements List<X> {
	Empty() {}

	public <R> R accept(ListVisitor<X,R> v) {
		return v.visitEmpty();
	}
}

class Cons<X> implements List<X> {
	X first;
	List<X> rest;

	Cons(X first, List<X> rest) {
		this.first = first;
		this.rest = rest;
	}


	public <R> R accept(ListVisitor<X,R> v) {
		return v.visitCons(this.first, this.rest);
	}
}

// Ex 03
// REPRESENTS: a visitor for compute the aggretate string length of a
// list of String
class StringAggregateLength implements ListVisitor<String, Integer> {
	public Integer visitEmpty() {
		return 0;
	}
	public Integer visitCons(String first, List<String> rest) {
		return first.length() + rest.accept(this);
	}
}

// Ex 04, 07
// REPRESENTS: a visitor for compute whether a list of String contains a 
// specified String
class IsContainString implements ListVisitor<String, Boolean> {
	String target;
	IsContainString(String target) {
		this.target = target;
	}
	
	public Boolean visitEmpty() {
		return false;
	}
	
	public Boolean visitCons(String fst, List<String> rst) {
		return fst.equals(this.target) ||
				rst.accept(this);
	}
}

// Ex 06, 08
// REPRESENTS: a visitor for compute whether a List<Integer> has an element
// greater than a specified value
class HasGreater implements ListVisitor<Integer, Boolean> {
	int n;
	HasGreater(int n) {
		this.n = n;
	}
	
	public Boolean visitEmpty() {
		return false;
	}
	
	public Boolean visitCons(Integer fst, List<Integer> rst) {
		return (fst > this.n) || rst.accept(this);
	}
}

// REPRESENTS: a visitor that contains all Strings in a List<String>
class ConcateString implements ListVisitor<String, String> {
	public String visitEmpty() {
		return "";	
	}
	
	public String visitCons(String fst, List<String> rst) {
		return fst + rst.accept(this);
	}
}

class ListExamples {
	// tests for class StringAggregateLength
	boolean testStrignAggregateLength(Tester t) {
		List<String> mt = new Empty<String>();
		List<String> los1 = new Cons<String>("hello", mt);
		List<String> los2 = new Cons<String>("world!", los1);
		StringAggregateLength sumLength = new StringAggregateLength();
		return
		t.checkExpect(mt.accept(sumLength), 0) &&
		t.checkExpect(los1.accept(sumLength), 5) &&
		t.checkExpect(los2.accept(sumLength), 11);
	}
	
	// tests for class IsContainString
	boolean testIsContainString(Tester t) {
		List<String> mt = new Empty<String>();
		List<String> los1 = new Cons<String>("hello", mt);
		List<String> los2 = new Cons<String>("world!", los1);
		List<String> los3 = new Cons<String>("world!", mt);
		IsContainString isContainString = new IsContainString("hello");
		return
		t.checkExpect(mt.accept(isContainString), false) &&
		t.checkExpect(los1.accept(isContainString), true) &&
		t.checkExpect(los2.accept(isContainString), true) &&
		t.checkExpect(los3.accept(isContainString), false);
	}
	
	// tests for class ConcateString
	boolean testConcateString(Tester t) {
		List<String> mt = new Empty<String>();
		List<String> los1 = new Cons<String>("hello", mt);
		List<String> los3 = new Cons<String>("world!", mt);
		List<String> los4 = new Cons<String>("hello", los3);
		ConcateString concate = new ConcateString();
		return
		t.checkExpect(mt.accept(concate), "") &&
		t.checkExpect(los1.accept(concate), "hello") &&
		t.checkExpect(los4.accept(concate), "helloworld!");
	}
	
	// tests for class HasGreater
	boolean testHasGreater(Tester t) {
		List<Integer> mt = new Empty<Integer>();
		List<Integer> loi1 = new Cons<Integer>(1, mt);
		List<Integer> loi2 = new Cons<Integer>(2, loi1);
		HasGreater hasGreater = new HasGreater(1);
		return
		t.checkExpect(mt.accept(hasGreater), false) &&
		t.checkExpect(loi1.accept(hasGreater), false) &&
		t.checkExpect(loi2.accept(hasGreater), true);
	}
}
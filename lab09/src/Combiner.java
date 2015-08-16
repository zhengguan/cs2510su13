// lab 08


import java.util.*;

import tester.Tester;

// Ex 03~05
interface Combiner<A, B> {
	// Combine an A and B into a B
	B combine(A a, B b);
}

class Operation<A, B> {
	// RETURNS: a combination of all elements in is with base, from right to left
	public B foldr(ArrayList<A> is, B base, Combiner<A, B> f) {
		return this.foldrHelper(is, 0, base, f);
	}
	
	// RETURNS: a combination of all elements in is[start, is.size()) with base,
	// from right to left
	private B foldrHelper(ArrayList<A> is, int start, B base, Combiner<A, B> f) {
		if(start == is.size()) {
			return base;
		}
		else {
			return 
				f.combine(is.get(start), this.foldrHelper(is, start + 1, base, f));
		}
	}
	
	// RETURNS: a combination of all elements in is with base, from left to right
	public B foldl(ArrayList<A> is, B base, Combiner<A, B> f) {
		return this.foldlHelper(is, 0, base, f);
	}
	
	// RETURNS:
	private B foldlHelper(ArrayList<A> is, int start, B base, Combiner<A, B> f) {
		if (start == is.size()) {
			return base;
		}
		else {
			return 
				foldlHelper(is, start + 1, f.combine(is.get(start),base) , f);
		}
	}
}

class ListOp {
	Operation<Integer, Integer> op = new Operation<Integer, Integer>();
	
	public int sum(ArrayList<Integer> l) {
		return op.foldl(l, 0, new Combiner<Integer, Integer>() {
			public Integer combine(Integer a, Integer b) {
				return a + b;
			}
		});
	}
	
	public int prod(ArrayList<Integer> l) {
		return op.foldr(l, 1, new Combiner<Integer, Integer>() {
			public Integer combine(Integer a, Integer b) {
				return a * b;
			}
		});
	}
}

class OperationExamples {
	Combiner<Integer, Integer> comb;	
	Operation<Integer, Integer> op;
	ArrayList<Integer> mt;
	ArrayList<Integer> l1;
	ListOp lop;
	
	void reset() {
		comb = new Combiner<Integer, Integer>(){
			public Integer combine(Integer a, Integer b) {
				return a + b;
			}
		};
		
		op = new Operation<Integer, Integer>();
		mt = new ArrayList<Integer>();
		l1 = new ArrayList<Integer>();
		l1.addAll(Arrays.asList(new Integer[]{1, 2}));
		lop = new ListOp();
	}
	
	// tests for foldr
	public void testFoldr(Tester t) {
		reset();
		t.checkExpect(this.op.foldr(this.mt, 0, this.comb), 0);
		t.checkExpect(this.op.foldr(l1, 0, comb), 3);
		reset();
	}
	
	// tests for foldl
	public void testFoldl(Tester t) {
		reset();
		t.checkExpect(this.op.foldl(this.mt, 0, this.comb), 0);
		t.checkExpect(this.op.foldl(l1, 0, comb), 3);
		reset();
	}
}

class ListOpExamples {
	ArrayList<Integer> mt;
	ArrayList<Integer> l1;
	ListOp lop;
	
	void reset() {
		mt = new ArrayList<Integer>();
		l1 = new ArrayList<Integer>();
		l1.addAll(Arrays.asList(new Integer[]{1, 2}));
		lop = new ListOp();
	}
	
	// tests for sum
	public void testSum(Tester t) {
		reset();
		t.checkExpect(this.lop.sum(this.mt), 0);
		t.checkExpect(this.lop.sum(this.l1), 3);
		reset();
	}
	
	// tests for prod	
	public void testProd(Tester t) {
		reset();
		t.checkExpect(this.lop.prod(this.mt), 1);
		t.checkExpect(this.lop.prod(this.l1), 2);
		reset();
	}
}


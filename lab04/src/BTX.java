// lab04
// http://www.ccs.neu.edu/course/cs2510su13-1/labs/lab4.html
import tester.Tester;

// Ex 05~07

// REPRESENTS: a binary tree
interface IBT<X> {
	// RETURNS: the height of this tree
	int height();
	
	// RETURNS: the number of elements in this tree
	int size();
	
	// RETURNS: an mirror image of this tree
	IBT<X> mirror();
}

// REPRESENTS: an empty binary tree
class MtBT<X> implements IBT<X> {
	MtBT(){}
	
	// RETURNS: the height of this tree
	public int height() {
		return 0;
	}
	
	// RETURNS: the number of elements in this tree
	public int size() {
		return 0;
	}
	
	// RETURNS: an mirror image of this tree
	public IBT<X> mirror() {
		return this;
	}
}

// REPRESENTS: a non-empty binary tree
class NMtBT<X> implements IBT<X> {
	X val;
	IBT<X> left;
	IBT<X> right;
	
	NMtBT(X val, IBT<X> left, IBT<X> right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
	
	// RETURNS: the height of this tree
	public int height() {
		return 1 + Math.max(this.left.height(), this.right.height());
	}	
	
	// RETURNS: the number of elements in this tree
	public int size() {
		return 1 + this.left.size() + this.right.size();
	}
	
	// RETURNS: an mirror image of this tree
	public IBT<X> mirror() {
		return new NMtBT<X>(this.val, this.right.mirror(), this.left.mirror());
	}
}

class BTExamples {
	IBT<Integer> mt = new MtBT<Integer>();
	IBT<Integer> bt1 = new NMtBT<Integer>(1, mt, mt);
	IBT<Integer> bt2 = new NMtBT<Integer>(2, bt1, mt);
	IBT<Integer> bt3 = new NMtBT<Integer>(2, mt, bt1);
	
	// tests for method height()
	boolean testHeight(Tester t) {
		return
		t.checkExpect(mt.height(), 0) &&
		t.checkExpect(bt1.height(), 1) &&
		t.checkExpect(bt2.height(), 2);
	}
	
	// tests for method size()
	boolean testSize(Tester t) {
		return
		t.checkExpect(mt.size(), 0) &&
		t.checkExpect(bt1.size(), 1) &&
		t.checkExpect(bt2.size(), 2);		
	}
	
	//  tests for method mirror()
	boolean testMirror(Tester t) {
		return
		t.checkExpect(mt.mirror(), mt) &&
		t.checkExpect(bt2.mirror(), bt3);
	}
}
import java.util.*;

import tester.Tester;

// lab 09

// Ex 01, 02
public class ListOperation {
	ListOperation() {}
	
	// RETURNS: the sum of all elements in the given list
	public Integer sumUp(ArrayList<Integer> l) {
		return this.sumUpi(l, 0);
	}
	
	// RETURNS: the sum of elements in l with index in [n, l.size())
	private Integer sumUpi(ArrayList<Integer> l, int n) {
		if (n == l.size()) {
			return 0;
		}
		else {
			return l.get(n) + this.sumUpi(l, n + 1);
		}
	}
	
	// RETURNS: the sum of all elements in the given list
	public Integer sumDown(ArrayList<Integer> l) {
		return this.sumDowni(l, l.size() - 1);
	}
	
	// RETURNS: the sum of elements in l with index in [0, n]
	// WHERE: -1 <= n;
	private Integer sumDowni(ArrayList<Integer> l, int n) {
		if (n < 0) {
			return 0;
		}
		else {
			return l.get(n) + this.sumDowni(l, n - 1);
		}
	}
}

// Ex1

class ListOperationExamples {
	public ListOperationExamples() { }
	
	ArrayList<Integer> mtl;
	ArrayList<Integer> l1;
	ListOperation op;
	
	private ArrayList<Integer> arrayToArrayList(Integer[] nums) {
		ArrayList<Integer> l = new ArrayList<Integer>();
		for(int i = 0; i < nums.length; i++) {
			l.add(nums[i]);
		}
		return l;
	}
	
	private void reset() {
		this.mtl = new ArrayList<Integer>();
		this.l1 = this.arrayToArrayList(new Integer[]{1, 2});
		this.op = new ListOperation();
	}
	
	// tests for method sumUp
	public void testSumUp(Tester t) {
		reset();
		t.checkExpect(op.sumUp(mtl), 0);
		t.checkExpect(op.sumUp(l1), 3);
		reset();
	}
	
	// test for method sumDown
	public void testSumDown(Tester t) {
		reset();
		t.checkExpect(op.sumDown(mtl), 0);
		t.checkExpect(op.sumDown(l1), 3);	
		reset();
	}
}
import tester.Tester;

// assignment 2

// 2

// REPRESENTS: list of numbers
interface ILoNum {
	// RETURNS: the sum of all elements in this list
	int sum();
	
	// RETURNS: the sum of squares of all elements in this list
	int sumSqrs();
	
}


// REPRESENTS: empty list of numbers
class MTLoNum implements ILoNum {
	// RETURNS: the sum of all elements in this list
	public int sum() {
		return 0;
	}
	
	// RETURNS: the sum of squares of all elements in this list
	public int sumSqrs() {
		return 0;
	}
	
}

// REPRESENTS: non empty list of number
class ConsLoNum implements ILoNum {
	int fst;
	ILoNum rst;
	
	ConsLoNum(int fst, ILoNum rst) {
		this.fst = fst;
		this.rst = rst;
	}
	
	// RETURNS: the sum of all elements in this list
	public int sum() {
		return this.fst + this.rst.sum();
	}
	
	// RETURNS: the sum of squares of all elements in this list
	public int sumSqrs() {
		return this.fst * this.fst + this.rst.sumSqrs();
	}
}


class LoNumExamples {
	ILoNum mtlon = new MTLoNum();
	ILoNum lon1 = new ConsLoNum(1, mtlon);
	ILoNum lon2 = new ConsLoNum(2, lon1);
	
	// tests for sum()
	boolean testSum(Tester t) {
		return
		t.checkExpect(mtlon.sum(), 0) &&
		t.checkExpect(lon2.sum(), 3);
	}
	
	// tests for sumSqrt()
	boolean testSumSqrt(Tester t) {
		return
		t.checkExpect(mtlon.sumSqrs(), 0) &&
		t.checkExpect(lon1.sumSqrs(), 1) &&
		t.checkExpect(lon2.sumSqrs(), 5);
	}
}
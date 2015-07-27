import tester.Tester;


//List

interface IList<I> {
	// RETURNS: a list that contains all elements get from applying f.process()
	// to elements in this list
	<R> IList<R> map(IFun<I, R> f);
	
	// RETURNS: the result by applying f.combine() to each element start from 
	// the end of the list   
	<R> R fold(ICombine<I, R> f, R rst);
	
	// traverse this list
	<R> R traverse(IListVisitor<I, R> v);
}

interface IListVisitor<I, R> {
	R processMT(MT<I> mt);
	R processCons(Cons<I> cons);
}

class MT<I> implements IList<I> {
	MT() {}
	
	// RETURNS: a list that contains all elements get from applying f.process()
	// to elements in this list
	public <R> IList<R> map(IFun<I, R> f) {
		return new MT<R>();
	}
	
	// RETURNS: the result by applying f.combine() to each element start from 
	// the end of the list   
	public <R> R fold(ICombine<I, R> f, R rst) {
		return rst;
	}
	
	// traverse this list
	public <R> R traverse(IListVisitor<I, R> v) {
		return v.processMT(this);
	}
}

class Cons<I> implements IList<I> {
	I fst;
	IList<I> rst;
	Cons(I fst, IList<I> rst) {
		this.fst = fst;
		this.rst = rst;
	}
	
	// RETURNS: a list that contains all elements get from applying f.process()
	// to elements in this list
	public <R> IList<R> map(IFun<I, R> f) {
		return new Cons<R>(f.process(this.fst), this.rst.map(f));
	}
	
	// RETURNS: the result by applying f.combine() to each element start from 
	// the end of the list   
	public <R> R fold(ICombine<I, R> f, R rst) {
		return f.combine(this.fst, this.rst.fold(f, rst));
	}
	
	// traverse this list
	public <R> R traverse(IListVisitor<I, R> v) {
		return v.processCons(this);
	}
	
	public I firstOf() {
		return this.fst;	
	}
	
	public IList<I> restOf() {
		return this.rst;
	}
}

interface ICombine<I, R> {
	R combine(I item, R rst);
}

interface IFun<I, R> {
	// RETURNS: a new I from the given item
	R process(I item);
}

class RelativeToAbsolute implements IListVisitor<Integer, IList<Integer>> {
	int n;
	public RelativeToAbsolute(int n) {
		this.n = n;
	}
	
	public IList<Integer> processMT(MT<Integer> mt) {
		return mt;
	}
	public IList<Integer> processCons(Cons<Integer> cons) {
		int currentAbsolute = this.n + cons.firstOf();
		RelativeToAbsolute newVisitor = 
				new RelativeToAbsolute(currentAbsolute);
		return new Cons<Integer>(cons.firstOf() + n, 
				cons.restOf().traverse(newVisitor));
	}
}

class RToAExamples {
	// tests for visitor RelativeToAbsolute
	boolean testRelativeToAbsolute(Tester t) {
		IList<Integer> mtlon = new MT<Integer>();
		IList<Integer> l1 = new Cons<Integer>(1, mtlon);
		IList<Integer> l2 = new Cons<Integer>(2, l1);
		IList<Integer> l3 = new Cons<Integer>(3, l1);
		return
		t.checkExpect(l2.traverse(new RelativeToAbsolute(0)), l3);
	}
}
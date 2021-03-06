import java.util.*;

import tester.Tester;

// lab 06 
// http://www.ccs.neu.edu/course/cs2510su13-1/labs/lab6.html

// Ex 01

// REPRESENTS: a list
interface IList <X> {
	// RETURNS: a list like this one, but with the given elements added
	IList<X> add(X elem);
	
	// RETURNS: a list like this one, but with elements equal to the given one
	// removed
	IList<X> sub(X elem);
	
	// RETURNS: true iff this list contains at least an element equal to the given
	// one
	boolean contains(X elem);
	
	// RETURNS: true iff this list contains all elements in that list
	boolean containsList(IList<X> that);
	
	// RETURNS: true iff this list is empty
	boolean empty();
	
	// RETURNS: the first element of this list
	X first();
	
	// RETURNS: a list like this one, but with the first element removed
	IList<X> rest();
	
	// RETURNS: the result of traversal this list
	<R> R traversal(IListVisitor<X, R> v);
	
	// RETURNS: true iff this list is sorted by the given comparator
	boolean isSorted(Comparator<X> comparator);
}


class Mt <X> implements IList<X> {
	Mt() {}
	
	// RETURNS: a list like this one, but with the given elements added
	public IList<X> add(X elem) {
		return new Cons<X>(elem, this); 
	}
	
	// RETURNS: a list like this one, but with elements equal to the given one
	// removed
	public IList<X> sub(X elem) {
		return this;
	}
	
	// RETURNS: true iff this list contains at least an element equal to the given
	// one
	public boolean contains(X elem) {
		return false;
	}
	
	// RETURNS: the result of traversal this list
	public <R> R traversal(IListVisitor<X, R> v) {
		return v.processMt(this);
	}
	
	// RETURNS: true iff this list contains all elements in that list
	public boolean containsList(IList<X> that) {
		return false;
	}
	
	// RETURNS: true iff this list is empty
	public boolean empty() {
		return true;
	}
	
	
	// RETURNS: the first element of this list
	public X first() {
		throw new RuntimeException("Mt<X>.first()");
	}
	
	// RETURNS: a list like this one, but with the first element removed
	public IList<X> rest() {
		throw new RuntimeException("Mt<X>.rest()");
	}
	
	// RETURNS: true iff this list is sorted by the given comparator
	public boolean isSorted(Comparator<X> comparator) {
		return true;
	}
}

class Cons <X> implements IList<X> {
	private X fst;
	private IList<X> rst;
	
	Cons(X fst, IList<X> rst) {
		this.fst = fst;
		this.rst = rst;
	}
	
	// RETURNS: a list like this one, but with the given elements added
	public IList<X> add(X elem) {
		return new Cons<X>(elem, this);
	}
	
	// RETURNS: a list like this one, but with elements equal to the given one
	// removed
	public IList<X> sub(X elem) {
		if(this.fst.equals(elem)) {
			return this.rst.sub(elem);
		}
		else {
			return new Cons<X>(this.fst, this.rst.sub(elem));
		}
	}
	
	// RETURNS: true iff this list contains at least an element equal to the given
	// one
	public boolean contains(X elem) {
		return this.fst.equals(elem) || this.rst.contains(elem);
	}
	
	// RETURNS: the result of traversal this list
	public <R> R traversal(IListVisitor<X, R> v) {
		return v.processCons(this.fst, this.rst);
	}
	
	// RETURNS: true iff this list contains all elements in that list
	public boolean containsList(IList<X> that) {
		if(that.empty()) {
			return true;
		}
		else {
			return this.contains(that.first()) && this.containsList(that.rest());
		}
	}
	
	// RETURNS: true iff this list is empty
	public boolean empty() {
		return false;
	}
	
	// RETURNS: the first element of this list
	public X first() {
		return this.fst;
	}
	
	// RETURNS: a list like this one, but with the first element removed
	public IList<X> rest() {
		return this.rst;
	}
	
	// RETURNS: true iff this list is sorted by the given comparator
	public boolean isSorted(Comparator<X> comparator) {
		if(this.rst.empty()) {
			return true;
		}
		else {
			return (comparator.compare(this.first(), this.rst.first()) <= 0) &&
					this.rst.isSorted(comparator);
		}
	}
	
}

interface IListVisitor<I, R>  {
	R processMt(Mt<I> mt);
	R processCons(I fst, IList<I> rst);
}

/*
// REPRESENTS: a set
interface ISet1<X>{}



class MtSet1<X> implements ISet1<X> {
	
}

class ConsSet1<X> implements ISet1<X> {
	
}
*/


class Set1<X> {
	private IList<X> elements;
	
	
	public Set1(IList<X> elements) {
		this.elements = elements;
	}
	
	public Set1() {
		this.elements = new Mt<X>();
	}
	
	// RETURNS: a set like this one, but elem added  
	public Set1<X> add(X elem) {
		return new Set1<X>(this.elements.add(elem));
	}
	
	// RETURNS: a set like this one, but with elements equal to the given one
	// removed
	public Set1<X> sub(X elem) {
		return new Set1<X>(this.elements.sub(elem));
	}
	
	// RETURNS: the intersect of this Set1 and that Set1
	public Set1<X> intersect(Set1<X> that) {
		return intersectWithList(that.elements);
	}
	
	// RETURNS: the intersect of this Set1 and the Set1 represented by
	// that IList
	private Set1<X> intersectWithList(IList<X> l) {
		if(l.empty()) {
			return new Set1<X>(new Mt<X>());
		}
		else {
			if(this.member(l.first())) {
				return this.intersectWithList(l.rest()).add(l.first());
			}
			else {
				return this.intersectWithList(l.rest());
			}
		}
	}
	
	// RETURNS: true iff this Set1 contains that Set1
	public boolean contains(Set1<X> that) {
		return 
		this.containsList(that.elements);
	}
	
	// RETURNS: true iff this Set1 contains all elements in the given list
	private boolean containsList(IList<X> l) {
		if(l.empty()) {
			return true;
		}
		else {
			return this.member(l.first()) && this.containsList(l.rest());
		}
	}
	
	// RETURNS: true iff this set contains a element that is equal to the 
	// given one
	public boolean member(X elem) {
		return this.elements.contains(elem);
	}
	
	// RETURNS: true iff this Set1 equals to that Set1
	public boolean equals(Set1<X> that) {
		return this.contains(that) && that.contains(this);
	}
}


class Set1Examples {
	Set1<Integer> arrayToSet(int[] nums) {
		IList<Integer> l = new Mt<Integer>();
		for(int i = 0; i < nums.length; i++) {
			l = l.add(nums[i]);
		}
		return new Set1<Integer>(l);
	}
	
	IList<Integer> mt = new Mt<Integer>();
	Set1<Integer> mts = new Set1<Integer>();
	Set1<Integer> s2 = this.arrayToSet(new int[]{1});
	Set1<Integer> s3 = this.arrayToSet(new int[]{1, 2});
	Set1<Integer> s4 = this.arrayToSet(new int[]{1, 2, 1});
	Set1<Integer> s5 = this.arrayToSet(new int[]{3}); 
	Set1<Integer> s6 = this.arrayToSet(new int[]{2}); 
	
	// tests for method member()
	boolean testMember(Tester t) {
		return
		t.checkExpect(s3.member(1), true) &&
		t.checkExpect(s3.member(3), false);
	}
	
	// tests for method contains()
	boolean testConatains(Tester t) {
		return
		t.checkExpect(s4.contains(mts), true) &&
		t.checkExpect(mts.contains(s4), false) &&
		t.checkExpect(s4.contains(s3), true) &&
		t.checkExpect(s3.contains(s4), true) &&
		t.checkExpect(s2.contains(s3), false);
	}	
	
	// tests for method equals()
	boolean testEquals(Tester t) {
		return
		t.checkExpect(mts.equals(s2), false) &&
		t.checkExpect(s2.equals(mts), false) &&
		t.checkExpect(s3.equals(s4), true);
	}
	
	// tests for method intersect()
	boolean testIntersect(Tester t) {
		return
		t.checkExpect(mts.intersect(s3).equals(mts)) &&
		t.checkExpect(s3.intersect(mts).equals(mts)) &&
		t.checkExpect(s3.intersect(s4).equals(s3)) &&
		t.checkExpect(s4.intersect(s5).equals(mts));
	}
	
	
	// tests for method sub()
	boolean testSub(Tester t) {
		return
		t.checkExpect(mts.sub(1).equals(mts)) &&
		t.checkExpect(s4.sub(1).equals(s6));
	}
	
	// tests for method add()
	boolean testAdd(Tester t) {
		return
		t.checkExpect(mts.add(1).equals(s2)) &&
		t.checkExpect(s2.add(2).equals(s3)) &&
		t.checkExpect(s3.add(1).equals(s3));
	}
}

/*****************************************************************************/

// Ex 02
class Set2<X> {
	private IList<X> elements;
	
	
	public Set2(IList<X> elements) {
		if(this.hasDuplicate(elements)) {
			throw new RuntimeException(
					"new Set2<X>(elements): elements has duplicates");
		}
		else {
			this.elements = elements;
		}
	}
	
	// RETURNS: true iff the given list has duplicate
	private boolean hasDuplicate(IList<X> l) {
		if(l.empty()) {
			return false;
		}
		else {
			return l.rest().contains(l.first()) || this.hasDuplicate(l.rest());
		}
	}
	
	public Set2() {
		this.elements = new Mt<X>();
	}
	
	// RETURNS: a set like this one, but elem added  
	public Set2<X> add(X elem) {
		if(this.elements.contains(elem)) {
			return this;
		}
		else {
			return new Set2<X>(this.elements.add(elem));
		}
	}
	
	// RETURNS: a set like this one, but with elements equal to the given one
	// removed
	public Set2<X> sub(X elem) {
		return new Set2<X>(this.elements.sub(elem));
	}
	
	// RETURNS: the intersect of this Set1 and that Set1
	public Set2<X> intersect(Set2<X> that) {
		return intersectWithList(that.elements);
	}
	
	// RETURNS: the intersect of this Set1 and the Set1 represented by
	// that IList
	private Set2<X> intersectWithList(IList<X> l) {
		if(l.empty()) {
			return new Set2<X>(new Mt<X>());
		}
		else {
			if(this.member(l.first())) {
				return this.intersectWithList(l.rest()).add(l.first());
			}
			else {
				return this.intersectWithList(l.rest());
			}
		}
	}
	
	// RETURNS: true iff this Set1 contains that Set1
	public boolean contains(Set2<X> that) {
		return 
		this.containsList(that.elements);
	}
	
	// RETURNS: true iff this Set1 contains all elements in the given list
	private boolean containsList(IList<X> l) {
		if(l.empty()) {
			return true;
		}
		else {
			return this.member(l.first()) && this.containsList(l.rest());
		}
	}
	
	// RETURNS: true iff this set contains a element that is equal to the 
	// given one
	public boolean member(X elem) {
		return this.elements.contains(elem);
	}
	
	// RETURNS: true iff this Set1 equals to that Set1
	public boolean equals(Set2<X> that) {
		return this.contains(that) && that.contains(this);
	}
}


class Set2Example {
	
	// RETURNS: a corresponding Set2 of the given array
	Set2<Integer> arrayToSet(int[] nums) {
		return new Set2<Integer>(this.arrayToList(nums));
	}
	
	// RETURNS: a corresponding list of the given array
	IList<Integer> arrayToList(int[] nums) {
		IList<Integer> l = new Mt<Integer>();
		for(int i = 0; i < nums.length; i++) {
			l = l.add(nums[i]);
		}
		return l;
	}
	
	IList<Integer> mt = new Mt<Integer>();
	Set2<Integer> mts = new Set2<Integer>();
	Set2<Integer> s2 = this.arrayToSet(new int[]{1});
	Set2<Integer> s3 = this.arrayToSet(new int[]{1, 2});
	Set2<Integer> s4 = this.arrayToSet(new int[]{2, 1});
	Set2<Integer> s5 = this.arrayToSet(new int[]{3}); 
	Set2<Integer> s6 = this.arrayToSet(new int[]{2}); 
	
	// tests for constructor
	boolean testConstructor(Tester t) {
		return
		t.checkConstructorException(
				new RuntimeException("new Set2<X>(elements): elements has duplicates"), 
				"Set2", this.arrayToList(new int[]{1, 2, 1}));
	}
	
	// tests for method member()
	boolean testMember(Tester t) {
		return
		t.checkExpect(mts.member(1), false) &&
		t.checkExpect(s2.member(1), true) &&
		t.checkExpect(s3.member(2), true);
	}
	
	// tests for method contains()
	boolean testContains(Tester t) {
		return
		t.checkExpect(mts.contains(s2), false) &&
		t.checkExpect(mts.contains(mts), true) &&
		t.checkExpect(s3.contains(s2), true) &&
		t.checkExpect(s3.contains(s5), false);
	}
	
	// tests for method equals()
	boolean testEquals(Tester t) {
		return
		t.checkExpect(mts.equals(mts), true) &&
		t.checkExpect(s3.equals(s2), false) &&
		t.checkExpect(s3.equals(s4), true);
	}
	
	// tests for method intersect()
	boolean testIntersect(Tester t) {
		return
		t.checkExpect(mts.intersect(s3).equals(mts), true) &&
		t.checkExpect(s3.intersect(mts).equals(mts), true) &&
		t.checkExpect(s3.intersect(s2).equals(s2), true) &&
		t.checkExpect(s3.intersect(s4).equals(s3), true);
	}
	
	// tests for method add()
	boolean testAdd(Tester t) {
		return
		t.checkExpect(mts.add(1).equals(s2), true) &&
		t.checkExpect(s3.add(1).equals(s3), true);
	}
	
	// tests for method sub()
	boolean testSub(Tester t) {
		return
		t.checkExpect(mts.sub(1).equals(mts)) &&
		t.checkExpect(s3.sub(2).equals(s2));
	}
}

/*****************************************************************************/

//Ex 03
class Set3<X> {
	private IList<X> elements;
	private Comparator<X> comparator;	
	
	public Set3(Comparator<X> comparator) {
		this.elements = new Mt<X>();
		this.comparator = comparator;
	}
	
	public Set3(IList<X> elements, Comparator<X> comparator) {
		if(this.hasDuplicate(elements)) {
			throw new RuntimeException(
					"new Set3<X>(elements): elements has duplicates");
		}
		else if(!elements.isSorted(comparator)) {
			throw new RuntimeException(
					"new Set3<X>(elements): elements is not sorted");
		}
		else {
			this.elements = elements;
			this.comparator = comparator;
		}
	}
	
	// RETURNS: true iff the given list has duplicate
	private boolean hasDuplicate(IList<X> l) {
		if(l.empty()) {
			return false;
		}
		else {
			return l.rest().contains(l.first()) || this.hasDuplicate(l.rest());
		}
	}

	
	// RETURNS: a set like this one, but elem added  
	public Set3<X> add(X elem) {
		return new Set3<X>(this.insertToSortedList(this.elements, elem),
				this.comparator);
	}
	
	// WHERE: l is sorted
	// RETURNS: a list equal to l iff l contains an element equal to elem,
	// ortherwise return a sorted list with elem inserted into l
	private IList<X> insertToSortedList(IList<X> l, X elem) {
		if(l.empty()) {
			return new Cons<X>(elem, l);
		}
		else {
			X fst = l.first();
			int result = this.comparator.compare(fst, elem);
			if(result == 0) {
				return l;
			}
			if(result < 0) {
				return new Cons<X>(fst, this.insertToSortedList(l.rest(), elem));
			}
			else {
				return new Cons<X>(elem, l);
			}
		}
	}
	
	// RETURNS: a set like this one, but with elements equal to the given one
	// removed
	public Set3<X> sub(X elem) {
		return new Set3<X>(
				this.removeElementFromSortedList(this.elements, elem),
				this.comparator);
	}
	
	// WHERE: l is a sorted list with no duplicates
	// RETURNS: a list like l but with element equal to elem removed
	private IList<X> removeElementFromSortedList(IList<X> l, X elem) {
		if(l.empty()) {
			return l;
		}
		else {
			X fst = l.first();
			int result = this.comparator.compare(fst, elem);
			if(result < 0) {
				return new Cons<X>(fst, 
						this.removeElementFromSortedList(l.rest(), elem));
			}
			else if(result == 0) {
				return l.rest();
			}
			else {
				return l;
			}
		}
	}
	
	// RETURNS: the intersect of this Set1 and that Set1
	public Set3<X> intersect(Set3<X> that) {
		return 
			new Set3<X>(
					this.listIntersectWithList(this.elements,
							that.elements),
					this.comparator);
	}
	
	// WHERE: l1 and l2 are sorted
	// RETURNS: the intersect of l1 and l2 in sorted order
	private IList<X> listIntersectWithList(IList<X> l1, IList<X> l2) {
		if(l1.empty() || l2.empty()) {
			return new Mt<X>();
		}
		else {
			X fst1 = l1.first();
			X fst2 = l2.first();
			int result = this.comparator.compare(fst1, fst2);
			if(result < 0) {
				return this.listIntersectWithList(l1.rest(), l2);
			}
			else if(result == 0) {
				return
					new Cons<X>(fst1,
							this.listIntersectWithList(l1.rest(), l2.rest()));
			}
			else {
				return this.listIntersectWithList(l1, l2.rest());
			}
		}
	}
	
	
	
	// RETURNS: true iff this Set1 contains that Set1
	public boolean contains(Set3<X> that) {
		return 
		this.listContainsList(this.elements, that.elements);
	}
	
	// WHERE: both l1 and l2 are sorted
	// RETURNS: true iff l1 contains all elements in l2
	private boolean listContainsList(IList<X> l1, IList<X> l2) {
		if(l2.empty()) {
			return true;
		}
		else if(l1.empty()) {
			return false;
		}
		else {
			X e1 = l1.first();
			X e2 = l2.first();
			int result = this.comparator.compare(e1, e2);
			if(result == 0) {
				return this.listContainsList(l1.rest(), l2.rest());
			}
			else if(result > 0) {
				return false;
			}
			else {
				return this.listContainsList(l1.rest(), l2);
			}
		}
	}
	
	
	// RETURNS: true iff this set contains a element that is equal to the 
	// given one
	public boolean member(X elem) {
		return this.memberOfSortedList(this.elements, elem);
	}
	
	// RETURNS: true iff l contains an element equal to elem
	private boolean memberOfSortedList(IList<X> l, X elem) {
		if(l.empty()) {
			return false;			
		}
		else {
			if(l.first().equals(elem)) {
				return true;
			}
			else if(this.comparator.compare(l.first(), elem) > 0) {
				return false;
			}
			else {
				return this.memberOfSortedList(l.rest(), elem);
			}
		}
	}
	
	// RETURNS: true iff this Set1 equals to that Set1
	public boolean equals(Set3<X> that) {
		return this.contains(that) && that.contains(this);
	}
}

class IntComparator implements Comparator<Integer> {
	public int compare(Integer i1, Integer i2) {
		return i1 - i2;
	}
}

class Set3Examples {	
	IntComparator comparator = new IntComparator();	
	IList<Integer> mt = new Mt<Integer>();
	Set3<Integer> mts = new Set3<Integer>(this.comparator);
	Set3<Integer> s2 = this.arrayToSet(new int[]{1});
	Set3<Integer> s3 = this.arrayToSet(new int[]{1, 2});
//	Set3<Integer> s4 = this.arrayToSet(new int[]{2, 1});
	Set3<Integer> s5 = this.arrayToSet(new int[]{2, 3}); 
	Set3<Integer> s6 = this.arrayToSet(new int[]{2});

	
	// RETURNS: a corresponding Set3 of the given array
	Set3<Integer> arrayToSet(int[] nums) {
		return new Set3<Integer>(this.arrayToList(nums), this.comparator);
	}
	
	// RETURNS: a corresponding list of the given array
	IList<Integer> arrayToList(int[] nums) {
		IList<Integer> l = new Mt<Integer>();
		for(int i = nums.length - 1; i >= 0; i--) {
			l = l.add(nums[i]);
		}
		return l;
	}
	
	// tests for IList.isSorted()
	boolean testIsSorted(Tester t) {
		return
		t.checkExpect(
				this.arrayToList(new int[] {}).isSorted(this.comparator), 
				true) &&
		t.checkExpect(
				this.arrayToList(new int[] {1}).isSorted(this.comparator), 
				true) &&		
		t.checkExpect(
				this.arrayToList(new int[] {1, 2}).isSorted(this.comparator), 
				true) &&
		t.checkExpect(
				this.arrayToList(new int[] {2, 1}).isSorted(this.comparator), 
				false);
	}
	
	// tests for constructor
	boolean testConstructor(Tester t) {
		return 
		t.checkConstructorException(
				new RuntimeException(
						"new Set3<X>(elements): elements is not sorted"),
				"Set3", 
				this.arrayToList(new int[]{2, 1}),this.comparator); 
	}
	
	// tests for method member()
	boolean testMember(Tester t) {
		return
		t.checkExpect(mts.member(1), false) &&
		t.checkExpect(s3.member(1), true) &&
		t.checkExpect(s3.member(3), false);
	}
	
	// tests for method contains()
	boolean testContains(Tester t) {
		return
		t.checkExpect(mts.contains(s2), false) &&
		t.checkExpect(s2.contains(mts), true) &&
		t.checkExpect(s3.contains(s2), true);
	}
	
	// tests for method equals()
	boolean testEquals(Tester t) {
		return
		t.checkExpect(this.mts.equals(this.s2), false) &&
		t.checkExpect(this.s2.equals(this.mts), false) &&
		t.checkExpect(this.s3.equals(this.s2), false) &&
		t.checkExpect(this.s3.equals(this.s3), true);
	}
	
	// tests for method intersects()
	boolean testIntersect(Tester t) {
		return
		t.checkExpect(mts.intersect(s3), mts) &&
		t.checkExpect(s3.intersect(s5).equals(s6), true);
	}
	
	// tests for method add()
	boolean testAdd(Tester t) {
		return
		t.checkExpect(this.mts.add(1).equals(s2)) &&
		t.checkExpect(this.s2.add(2).equals(s3)) &&
		t.checkExpect(this.s6.add(1).equals(s3));
	}
	
	// tests for method sub()
	boolean testSub(Tester t) {
		return
		t.checkExpect(mts.sub(1).equals(mts)) &&
		t.checkExpect(s2.sub(1).equals(mts)) &&
		t.checkExpect(s3.sub(2).equals(s2));
	}
}

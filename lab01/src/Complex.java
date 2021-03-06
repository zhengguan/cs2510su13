import tester.Tester;

// EX 4

// REPRESENTS: complex number 
class Complex {
	int real; // real part
	int imag; // imagine part
	
	Complex(int real, int imag) {
		this.real = real;
		this.imag = imag;
	}
	
	// RETURNS: the sum of this with that
	Complex sum(Complex that) {
		return new Complex(this.real + that.real, this.imag + that.imag);
	}
}

/******************************************************************************/
// EX 5
// REPRESNETS: coordinate 
class Coordinate {
	int x; 
	int y;
	
	Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

// REPRESENTS: a shape of point
class Point {
	Coordinate center;
	
	Point(Coordinate center) {
		this.center = center;
	}
}

// REPRESNETS: a shape of a rectangle
class Rectangle {
	Coordinate center;
	int width; // in pixels
	int height; // in pixels	
	
	Rectangle(Coordinate center, int width, int height) {
		this.center = center;
		this.width = width;
		this.height = height;
	}
}

// REPRESNETS: a circle
class Circle {
	Coordinate center;
	int radius; // in pixels
	
	Circle(Coordinate center, int radius) {
		this.center = center;
		this.radius = radius;
	}
}

/******************************************************************************/

// EX 6
class Examples {
	Examples() {}
	Complex cmp1 = new Complex(3, 4);
	Complex cmp2 = new Complex(5, 6);
	
	Coordinate cd1 = new Coordinate(100, 200);
	Point p1 = new Point(cd1);
	Rectangle r1 = new Rectangle(cd1, 20, 30);
	Circle c1 = new Circle(cd1, 10);
	
	// test for method Complex.sum()
	boolean testSum(Tester t) {
//		System.out.println("hello".toUpperCase());
		return
		t.checkExpect(cmp1.sum(cmp2), new Complex(8,  10));
	}
}

/******************************************************************************/
// EX 7

// REPRESENTS: a list of integers
interface ILoInt {
	// RETURNS: the sum of all values in this list
	int sum();
	
}

// REPRESENTS: a empty list of integers
class MTLoInt implements ILoInt {
	MTLoInt() {}
	
	// RETURNS: the sum of all values in this list
	public int sum() {
		return 0;
	}
}

// REPRESENTS: a non-empty list of integers
class ConsLoInt implements ILoInt {
	int fst;  // first integer
	ILoInt rst; // rest integers
	
	ConsLoInt(int fst, ILoInt rst) {
		this.fst = fst;
		this.rst = rst; 
	}
	
	// RETURNS: the sum of all values in this list
	public int sum() {
		return this.fst + this.rst.sum();
	}
}

class LoIntExamples {
	ILoInt empty = new MTLoInt();
	ILoInt loi1 = new ConsLoInt(1, empty);
	ILoInt loi2 = new ConsLoInt(2, loi1);
	
	
	// tests for method sum
	boolean testSum(Tester t) {
		return
		t.checkExpect(empty.sum(), 0) &&
		t.checkExpect(loi1.sum(), 1) &&
		t.checkExpect(loi2.sum(), 3);
	}
}

/******************************************************************************/
//EX 8

// REPRESENTS: a list of complex numbers
interface ILoComplex {
	// RETURNS: a the sum of Complex numbers in this list 
	Complex sum();
}

// REPRESENTS: a empty list of complex numbers
class MTLoComplex implements ILoComplex {
	MTLoComplex(){}
	
	// RETURNS: a the sum of Complex numbers in this list 
	public Complex sum() {
		return new Complex(0, 0);
	}
}

// REPRESENTS: a non-empty list of complex numbers
class ConsLoComplex implements ILoComplex {
	Complex fst;
	ILoComplex rst;
	
	ConsLoComplex(Complex fst, ILoComplex rst) {
		this.fst = fst;
		this.rst = rst;
	}
	
	// RETURNS: a the sum of Complex numbers in this list 
	public Complex sum() {
		return this.fst.sum(this.rst.sum());
	}
}

class LoComplexExamples {
	Complex cp1 = new Complex(3, 2);
	Complex cp2 = new Complex(4, 5);
	
	ILoComplex empty = new MTLoComplex();
	ILoComplex lc1 = new ConsLoComplex(cp1, empty);
	ILoComplex lc2 = new ConsLoComplex(cp2, lc1);
	
	// tests for method sum()
	boolean testSum(Tester t) {
		return
		t.checkExpect(empty.sum(), (new Complex(0, 0))) &&
		t.checkExpect(lc1.sum(), cp1) &&
		t.checkExpect(lc2.sum(), (new Complex(7, 7)));
	}
}

/******************************************************************************/
//EX 9, 10
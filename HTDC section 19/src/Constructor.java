import tester.Tester;

// some tests on overloading constructor

class AClass {
	int x;
	int y;
	
	AClass() {
		this.x = 0;
		this.y = 0;
	}
	
	private AClass(int x, int y) {
		this.x = x;
		this.y = x;
	}
	
//	AClass(Integer x, Integer y) {
//		this.x = y;
//		this.y = y;
//	}
}

class BClass{
	boolean flag;
	protected AClass a;
	
	BClass(boolean flag, AClass a) {
		this.flag = flag;
		this.a = a;
	}	
	
	public AClass getA() {
		return a;
	}
	
	public String compare(BClass that) {
		return "Bclass";
	}
}

class CClass extends BClass {
	CClass(boolean flag, AClass a) {
		super(flag, a);
		this.x = 100;
	}
	
	public String compare(CClass that) {
		return "CClass";
	}
	
	int x;
	boolean test = (x == 100);
	
}



class ConstructorExamples{
	boolean test(Tester t) {
		AClass a1 = new AClass();
		BClass b1 = new BClass(true, a1);
		b1.getA().x = 1;
		b1.a = null;
		CClass c1 = new CClass(true, a1);
		System.out.println(c1.test);
		return true;
		
	}
}
import javalib.funworld.*;
import javalib.worldimages.*;
import javalib.colors.*;
import tester.Tester;



//the world of a single dropping block
class BlockWorld extends World {
	IColor BACKGROUND = new Blue();
	DrpBlock block;
	int WIDTH = 500; // canvas width
	int HEIGHT = 500; // canvas height
	BlockWorld(DrpBlock block) {
		this.block = block;
	}
	//drop the block in this world
	public World onTick() {
		return new BlockWorld(this.block.drop());
	}

	//move the block in this world acoording the key input
	public World onKeyEvent(String ke) {
		return new BlockWorld(this.block.steer(ke));
	}
	


	//draw this world��s block into the canvas
//	boolean draw() {
//		return this.drawBackground()
//				&& this.block.draw(this.theCanvas);
//	}
	
	//draw this world��s block into the canvas
	public WorldImage makeImage() {
		return new OverlayImagesXY(this.drawBackground(), this.block.draw(), 
				this.block.x, this.block.y);
	}

	//paint the entire canvas BACKGROUND
	WorldImage drawBackground() {
		return new RectangleImage(new Posn(this.WIDTH / 2, this.HEIGHT / 2),
				this.WIDTH, this.HEIGHT, this.BACKGROUND);
	}
}

class DrpBlock {
	int x;
	int y;
	int deltY = 3;
	int deltX = 3;
	
	DrpBlock(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public DrpBlock drop() {
		return new DrpBlock(this.x, this.y + this.deltY); 
	}
	
	public WorldImage draw() {
		return new RectangleImage(new Posn(x, y), 20, 20, new Red());
	}
	
	// RETURNS: a drop block moved according to the input key event
	public DrpBlock steer(String ke) {
		if(ke.equals("left")) {
			return new DrpBlock(x - deltX, y);
		}
		else if(ke.equals("right")) {
			return new DrpBlock(x + deltX, y);
		} 
		else {
			return this;
		}
	}
}

class BlockWorldExamples {
	public static void main(String[] args) {
		DrpBlock db = new DrpBlock(250, 250);
		BlockWorld world = new BlockWorld(db);
		world.bigBang(1000, 1000, 0.1);
		return;
	}
}

/******************************************************************************/
// List

interface IList<I> {
	// RETURNS: a list that contains all elements get from applying f.process()
	// to elements in this list
	<R> IList<R> map(IFun<I, R> f);
	
	// RETURNS: the result by applying f.combine() to each element start from 
	// the end of the list   
	<R> R fold(ICombine<I, R> f, R rst);
	
	
}

class MT<I> implements IList<I> {
	MT() {		
	}
	
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
}

interface ICombine<I, R> {
	R combine(I item, R rst);
}

interface IFun<I, R> {
	// RETURNS: a new I from the given item
	R process(I item);
}

class Add1 implements IFun<Integer, Integer> {
	public Integer process(Integer item) {
		return item + 1;
	}
}

class Sub1 implements IFun<Integer, Integer> {
	public Integer process(Integer item) {
		return item - 1;
	}
}

class IsOdd implements IFun<Integer, Boolean> {
	public Boolean process(Integer item) {
		return item % 2 == 1;
	}
}

class Sum implements ICombine<Integer, Integer> {
	public Integer combine(Integer i1, Integer i2) {
		return i1 + i2;
	}
}

class ListExamples {
	IList<Integer> mtlon = new MT<Integer>();
	IList<Integer> lon1 = new Cons<Integer>(0, mtlon);
	IList<Integer> lon2 = new Cons<Integer>(2, lon1);
	IList<Integer> lon4 = new Cons<Integer>(1, mtlon);
	IList<Integer> lon5 = new Cons<Integer>(3, lon4);
	
	IList<Boolean> mtlob = new MT<Boolean>();
	IList<Boolean> lob1 = new Cons<Boolean>(true, mtlob);
	IList<Boolean> lob2 = new Cons<Boolean>(true, lob1);
	
	// tests for map()
	boolean testMap(Tester t) {
		return
		t.checkExpect(this.mtlon.map(new Add1()), mtlon) &&
		t.checkExpect(this.mtlon.map(new Sub1()), mtlon) &&
		t.checkExpect(this.lon2.map(new Add1()), lon5) &&
		t.checkExpect(this.lon5.map(new Sub1()), lon2) &&
		t.checkExpect(this.lon5.map(new IsOdd()), lob2);
	}
	
	// tests for fold()
	boolean testFold(Tester t) {
		return
		t.checkExpect(this.mtlon.fold(new Sum(), 0), 0) &&
		t.checkExpect(this.lon5.fold(new Sum(), 0), 4);
	}
}


/******************************************************************************/
// section 36
// visitor
interface IExpression {
	<R> R traversal(IProc<R> f);
}

class Num implements IExpression {
	private int value;
	
	public Num(int value) {
		this.value = value;
	}
	
	public <R> R traversal(IProc<R> f) {
		return f.processNum(this);
	}
	
	public int valueOf() {
		return this.value;
	}
}

class Var implements IExpression {
	private String name;
	
	public Var(String name) {
		this.name = name;
	}
	
	public <R> R traversal(IProc<R> f) {
		return f.processVar(this);
	}
	
	public String nameOf() {
		return this.name;
	}
}

class Pls implements IExpression {
	private IExpression left;
	private IExpression right;
	
	public Pls(IExpression left, IExpression right) {
		this.left = left;
		this.right = right;
	}
	
	public <R> R traversal(IProc<R> f) {
		return f.processPls(this);
	}
	
	// RETURNS: the left expression of this expression
	public IExpression leftOf() {
		return this.left;
	}
	
	// RETURNS: the right expression of this expression
	public IExpression rightOf() {
		return this.right;
	}
}

// REPRESENTS: visitor for IExpression
interface IProc<R> {
	R processNum(Num n);
	R processVar(Var v);
	R processPls(Pls pls);
}

// REPRESENTS: a visitor to judge whether an IExpression is variable free
class VariableFree implements IProc<Boolean>{
	public Boolean processNum(Num n) {
		return true;
	}
	
	public Boolean processVar(Var v) {
		return false;
	}
	
	public Boolean processPls(Pls pls) {
		return pls.leftOf().traversal(this) && pls.rightOf().traversal(this);
	}
}

// REPRESENTS: a visitor that calculate the value of an IExpression
class ValueOf implements IProc<Integer> {
	public Integer processNum(Num n) {
		return n.valueOf();
	}
	
	public Integer processVar(Var v) {
		throw new RuntimeException("calculate ValueOf Var"); 
	}
	
	public Integer processPls(Pls pls) {
		return pls.leftOf().traversal(this) + pls.rightOf().traversal(this);
	}
	
}



// Ex 36.25

// REPRESENTS: a visitor that collect all variable names in an IExpression
class CollectName implements IProc<String> {
	public String processNum(Num n) {
		return "";
	}
	
	public String processVar(Var v) {
		return v.nameOf();
	}
	
	public String processPls(Pls pls) {
		String leftName = pls.leftOf().traversal(this);
		String rightName = pls.rightOf().traversal(this);
		if(leftName.length() != 0 && rightName.length() != 0) {	
			return leftName + " " + rightName;
		}
		else {
			return leftName + rightName;
		}
	}
}


// Ex 36.26

// REPRESENTS: a visitor that calculate the maximum depth of an IExpression
class MaxDepth implements IProc<Integer> {
	public Integer processNum(Num n) {
		return 0;
	}
	public Integer processVar(Var v) {
		return 0;
	}
	public Integer processPls(Pls pls) {
		return 1 + Math.max(pls.leftOf().traversal(this),
				pls.rightOf().traversal(this));
	}
}


class ExpressionExamples {
	IExpression n1 = new Num(1);
	IExpression n2 = new Num(2);
	IExpression v1 = new Var("v1");
	IExpression v2 = new Var("v2");
	IExpression pl1 = new Pls(n1, n2);
	IExpression pl2 = new Pls(n1, pl1);
	IExpression pl3 = new Pls(v1, pl2);
	IExpression pl4 = new Pls(v2, pl3);
	
	// tests for class VariableFree 
	boolean testTraversal(Tester t) {
		return
		t.checkExpect(n1.traversal(new VariableFree()), true) &&
		t.checkExpect(v1.traversal(new VariableFree()), false) &&
		t.checkExpect(pl2.traversal(new VariableFree()), true) &&
		t.checkExpect(pl3.traversal(new VariableFree()), false);
	}
	
	// tests for class ValueOf
	boolean testValueOf(Tester t) {
		ValueOf valueOf = new ValueOf();
		return
		t.checkExpect(n1.traversal(valueOf), 1) &&
		t.checkExpect(pl1.traversal(valueOf), 3) &&
		t.checkException(new RuntimeException("calculate ValueOf Var"), pl3, 
				"traversal", valueOf);
	}
	
	// tests for class CollectName
	boolean testCollectName(Tester t) {
		CollectName collector = new CollectName();
		return
		t.checkExpect(n1.traversal(collector), "") &&
		t.checkExpect(v1.traversal(collector), "v1") &&
		t.checkExpect(pl3.traversal(collector), "v1") &&
		t.checkExpect(pl4.traversal(collector), "v2 v1");
	}
	
	// tests for class MaxDepth
	boolean testMaxDepth(Tester t) {
		MaxDepth maxDepth = new MaxDepth();
		return
		t.checkExpect(n1.traversal(maxDepth), 0) &&
		t.checkExpect(v1.traversal(maxDepth), 0) &&
		t.checkExpect(pl1.traversal(maxDepth), 1) &&
		t.checkExpect(pl4.traversal(maxDepth), 4);
	}
}



import tester.*;


// runs tests for the buddies problem
public class ExamplesBuddies{
	Person ann  = new Person("Ann");
	Person bob = new Person("Bob");
	Person cole = new Person("Cole");
	Person dan = new Person("Dan");
	Person ed = new Person("Ed");
	Person fay = new Person("Fay");
	Person gabi = new Person("Gabi");
	Person hank = new Person("Hank");
	Person jan = new Person("Jan");
	Person kim = new Person("Kim");
	Person len = new Person("Len");

	public ExamplesBuddies(){
		this.ann.addBuddy(this.bob);
		this.ann.addBuddy(this.cole);
		this.bob.addBuddy(this.ann);
		this.bob.addBuddy(this.ed);
		this.bob.addBuddy(this.hank);
		this.cole.addBuddy(this.dan);
		this.dan.addBuddy(this.cole);
		this.ed.addBuddy(this.fay);
		this.fay.addBuddy(this.ed);
		this.fay.addBuddy(this.gabi);
		this.gabi.addBuddy(this.ed);
		this.gabi.addBuddy(this.fay);
		this.jan.addBuddy(this.kim);
		this.jan.addBuddy(this.len);
		this.kim.addBuddy(this.jan);
		this.kim.addBuddy(this.len);
		this.len.addBuddy(this.jan);
		this.len.addBuddy(this.kim);	  
	};

	// test for method hasDirectBuddy()
	boolean testHasDirectBuddy(Tester t) {
		return
		t.checkExpect(this.ann.hasDirectBuddy(this.cole), true) &&
		t.checkExpect(this.hank.hasDirectBuddy(this.ann), false);
	}

	// tests for method countCommonBuddies()
	boolean testCountCommonBuddies(Tester t) {
		return
		t.checkExpect(this.kim.countCommonBuddies(this.len), 1) &&
		t.checkExpect(this.hank.countCommonBuddies(this.len), 0);
	}

	// tests for method hasDistantBuddy()
	boolean testHasDistantBuddy(Tester t) {
		return
		t.checkExpect(this.ann.hasDistantBuddy(this.fay), true) &&
		t.checkExpect(this.ann.hasDistantBuddy(this.kim), false);
	}
	
	// tests for method partyCount()
	boolean testPartyCount(Tester t) {
		return
		t.checkExpect(this.ann.partyCount(), 8) &&
		t.checkExpect(this.jan.partyCount(), 3);
	}
}
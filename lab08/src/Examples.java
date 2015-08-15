import tester.*;

// Bank Account Examples and Tests
public class Examples {

    public Examples(){ reset(); }
    
    Account check1;
    Account savings1;
    Account credit1;
    Bank mt;
    Bank b1;
    
    
    // Initializes accounts to use for testing with effects.
    // We place inside reset() so we can "reuse" the accounts
    public void reset(){
        
        // Initialize the account examples
        check1 = new Checking(1, 100, "First Checking Account", 20);
        savings1 = new Savings(4, 200, "First Savings Account", 2.5);
        credit1 = new Credit(3, 100, "Guess", 50, 0.04);
        this.mt = new Bank("NongYe");
        this.b1 = new Bank("NongYe");
        this.b1.add(check1);
    }
    
    // Tests the exceptions we expect to be thrown when
    //   performing an "illegal" action.
    public void testExceptions(Tester t){
        reset();
        t.checkException("Test for invalid Checking withdraw",
                         new RuntimeException("1000 is not available"),
                         this.check1,
                         "withdraw",
                         1000);
        t.checkException("Tests for invalid Savings withdraw", 
				 new RuntimeException("1000 is not available"),
				 this.savings1,
				 "withdraw", 
				 1000);        
        t.checkException("Tests for invalid Credit withdraw", 
        				 new RuntimeException("1000 is not available"),
        				 this.credit1,
        				 "withdraw", 
        				 1000);
    }
    
    // Test the withdraw method(s)
    public void testWithdraw(Tester t) {
    	reset();
    	t.checkExpect(check1.withdraw(10), 90);
    	t.checkExpect(check1, new Checking(1, 90, "First Checking Account", 20));
    	
    	t.checkExpect(savings1.withdraw(10), 190);
    	t.checkExpect(savings1, new Savings(4, 190, "First Savings Account", 2.5));
    	
    	t.checkExpect(credit1.withdraw(10), 90);
    	t.checkExpect(credit1, new Credit(3, 90, "Guess", 50, 0.04));
    }
    
    // Test the deposit method(s)
    public void testDeposit(Tester t){
        reset();
        t.checkExpect(check1.deposit(25), 125);
        t.checkExpect(check1, new Checking(1, 125, "First Checking Account", 20));

    	t.checkExpect(savings1.deposit(10), 210);
    	t.checkExpect(savings1, new Savings(4, 210, "First Savings Account", 2.5));
    	
    	t.checkExpect(credit1.deposit(10), 110);
    	t.checkExpect(credit1, new Credit(3, 110, "Guess", 50, 0.04));
        
        reset();
    }
    
    // Test the Bank.add method
    public void testDesposit(Tester t) {
    	reset();
    	Bank b1 = new Bank("NoneYe");
    	b1.add(credit1);
    	t.checkExpect(b1.getAccount(), new ConsLoA(credit1, new MtLoA()));    	
    }
    
    // Test the depositTo method
    public void testDepositTo(Tester t) {
    	reset();
    	t.checkException("Test for deposit to non-exist account", 
    					 new RuntimeException("MtLoA.getAccount: No account 2"),
    					 this.b1,
    					 "depositTo",
    					 2,
    					 100);
    	this.b1.depositTo(1, 100);
    	this.mt.add(new Checking(1, 200, "First Checking Account", 20));
    	t.checkExpect(this.b1, mt);
    	reset();
    }
    
    // Test the withdrawFrom method
    public void testWithdrawFrom(Tester t) {
    	reset();
    	t.checkException("Test for withdraw from non-exist account",
    					 new RuntimeException("MtLoA.getAccount: No account 2"), 
    					 this.b1, 
    					 "withdrawFrom", 
    					 2,
    					 100);
    	t.checkException("Test for invalid withdraw from account",
				 new RuntimeException("200 is not available"), 
				 this.b1, 
				 "withdrawFrom", 
				 1,
				 200);
    	this.b1.withdrawFrom(1, 50);
    	this.mt.add(new Checking(1, 50, "First Checking Account", 20));
    	t.checkExpect(b1, mt);
    	reset();
    }
    
    // Test the removeAccount method
    public void testRemoveAccount(Tester t) {
    	reset();
    	t.checkException("Test for remove non-exist account", 
    					 new RuntimeException("MtLoA.remove: No account 10"), 
    					 this.b1, 
    					 "removeAccount",
    					 10);
    	this.b1.removeAccount(1);
    	t.checkExpect(this.b1, this.mt);
    }
}

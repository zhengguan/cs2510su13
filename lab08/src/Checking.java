
// Represents a checking account
public class Checking extends Account{

    int minimum; // The minimum account balance allowed

    public Checking(int accountNum, int balance, String name, int minimum){
        super(accountNum, balance, name);
        this.minimum = minimum;
    }
    
    // EFFECT: Withdraw the given amount
    // Return the new balance
    public int withdraw(int amount) {
    	if (this.balance - amount < minimum) {
    		throw new RuntimeException(amount + " is not available");
    	}
    	else {
    		this.balance = this.balance - amount;
    		return this.balance;
    	}
    }
}

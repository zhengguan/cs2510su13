
// Represents a savings account
public class Savings extends Account{

    double interest; // The interest rate

    public Savings(int accountNum, int balance, String name, double interest){
        super(accountNum, balance, name);
        this.interest = interest;
    }
    
    // EFFECT: Withdraw the given amount
    // Return the new balance
    public int withdraw(int amount) {
    	if (this.balance - amount < 0) {
    		throw new RuntimeException(amount + " is not available");
    	}
    	else {
    		this.balance = this.balance - amount;
    		return this.balance;
    	}
    }
}


// Represents a credit line account
public class Credit extends Account{

    int creditLine;  // Maximum amount accessible
    double interest; // The interest rate charged
    
    public Credit(int accountNum, int balance, String name, int creditLine, double interest){
        super(accountNum, balance, name);
        this.creditLine = creditLine;
        this.interest = interest;
    }
    
    // EFFECT: Withdraw the given amount
    // Return the new balance
    public int withdraw(int amount) {
    	if (this.balance + this.creditLine - amount < 0) {
    		throw new RuntimeException(amount + " is not available");
    	}
    	else {
    		this.balance = this.balance - amount;
    		return this.balance;
    	}
    }
}

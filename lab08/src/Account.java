
// Represents a bank account
public abstract class Account {

    int accountNum;  // Must be unique
    int balance;     // Must remain above zero (others Accts have more restrictions)
    String name;     // Name on the account

    public Account(int accountNum, int balance, String name){
        this.accountNum = accountNum;
        this.balance = balance;
        this.name = name;
    }
    
    // EFFECT: Withdraw the given amount
    // Return the new balance
    abstract int withdraw(int amount);
    
    // EFFECT: Deposit the given funds into this account
    // Return the new balance
    public int deposit(int funds) {
    	this.balance = this.balance + funds;
    	return this.balance;
    }
    
    // RETURNS: true iff the number of this account equal to the given number
    public boolean isAccount(int accountNum) {
    	return this.accountNum == accountNum;
    }
}

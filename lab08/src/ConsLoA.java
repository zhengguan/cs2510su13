
// Represents a non-empty List of Accounts...
public class ConsLoA implements ILoA{

    Account first;
    ILoA rest;

    public ConsLoA(Account first, ILoA rest){
        this.first = first;
        this.rest = rest;
    }
    
    /* Template
     *  Fields:
     *    ... this.first ...         --- Account
     *    ... this.rest ...          --- ILoA
     *
     *  Methods:
     *
     *  Methods for Fields:
     *
     */
      
    // RETURNS: the account in this list whose account number is equal to the
    // given one.
    public Account getAccount(int accountNum) {
    	if(this.first.isAccount(accountNum)) {
    		return this.first;
    	}
    	else {
    		return this.rest.getAccount(accountNum);
    	}
    }
    
    // RETURN: a list of accounts like this one, but with all accounts whose
    // account number equal to the given one removed.
    public ILoA remove(int accountNum) {
    	if (this.first.isAccount(accountNum)) {
    		return this.rest;    		
    	}
    	else {
    		return new ConsLoA(this.first, this.rest.remove(accountNum));
    	}
    }
}
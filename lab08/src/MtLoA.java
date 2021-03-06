
// Represents the empty List of Accounts
public class MtLoA implements ILoA{
    
    MtLoA(){}
    
    // RETURNS: the account in this list whose account number is equal to the
    // given one.
    public Account getAccount(int accountNum) {
    	throw new RuntimeException(
    			"MtLoA.getAccount: No account " + accountNum);
    }
    
    // EFFECT: remove account in this list whose account number equal to the 
    // given one
    public ILoA remove(int accountNum) {
    	throw new RuntimeException(
    			"MtLoA.remove: No account " + accountNum);
    }
}


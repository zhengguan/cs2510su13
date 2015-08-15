
// Represents a List of Accounts
public interface ILoA{
    // RETURNS: the account in this list whose account number is equal to the
    // given one.
    Account getAccount(int accountNum);
    
    // RETURN: a list of accounts like this one, but with all accounts whose
    // account number equal to the given one removed.
    ILoA remove(int accountNum);
}


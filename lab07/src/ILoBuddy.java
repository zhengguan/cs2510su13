
// represents a list of Person's buddies
interface ILoBuddy {
	// RETURNS: true iff this list contains the given person
	boolean contains(Person p);
	
	// RETURNS: the number of common buddies in this list and that list
	int countCommonBuddies(ILoBuddy that);

	// GIVEN: a person and a list of person that are not 
	// RETURNS: true iff this list contains the given buddy or a distant buddy 
	// of the given one
//	boolean hasDistanBuddy(Person p);
	
	// GIVEN: this list represents buddies that will be invited to the party,
	// newst contains buddies that are the farthest buddies of the host in this 
	// list
	// RETURNS: true iff p will be invited to the party
	boolean willInvite(ILoBuddy newst, Person p);
	
	// RETURNS: a list of all direct buddies of persons in this list 
	// (without replicates)
	ILoBuddy allDirectBuddies();
	
	// RETURNS: a list like this one, but with all elements in that list 
	// removed
	ILoBuddy minus(ILoBuddy that);
	
	// RETURNS: true iff this list is empty
	boolean empty();
	
	// RETURNS: a list that contains all persons in this list and that list
	// (without replicates)
	ILoBuddy union(ILoBuddy that);
	
	// RETURNS: the number of person that will show up at the party participated
	// by all people in this list
	int partyCount();
	
	// RETURNS: the number of buddies in this list
	int count();
}

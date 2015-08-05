
// represents an empty list of Person's bussies
class MTLoBuddy implements ILoBuddy {
	MTLoBuddy() {}
 
	// RETURNS: true iff this list contains the given person
	public boolean contains(Person p) {
		return false;
	}
	
	// RETURNS: the number of common buddies in this list and that list
	public int countCommonBuddies(ILoBuddy that) {
		return 0;
	}
	
	// RETURNS: true iff this list contains the given buddy or a distant buddy 
	// of the given one
//	public boolean hasDistanBuddy(Person p) {
//		return false;
//	}
	
	// GIVEN: this list represents buddies that will be invited to the party,
	// newst contains buddies that are the farthest buddies of the host in this 
	// list
	// RETURNS: true iff p will be invited to the party
	public boolean willInvite(ILoBuddy newst, Person p) {
		return false;
	}
	
	// RETURNS: a list of all direct buddies of persons in this list 
	// (without replicates)
	public ILoBuddy allDirectBuddies() {
		return new MTLoBuddy();
	}
	
	// RETURNS: a list like this one, but with all elements in that list 
	// removed
	public ILoBuddy minus(ILoBuddy that) {
		return this;
	}
	
	// RETURNS: true iff this list is empty
	public boolean empty() {
		return true;
	}
	
	// RETURNS: a list that contains all persons in this list and that list
	// (without replicates)
	public ILoBuddy union(ILoBuddy that) {
		return that;
	}
	
	// RETURNS: the number of person that will show up at the party participated
	// by all people in this list
	public int partyCount() {
		return 0;
	}
	
	// RETURNS: the number of buddies in this list
	public int count() {
		return 0;
	}
}

// represents a list of Person's buddies
class ConsLoBuddy implements ILoBuddy {

  Person first;
  ILoBuddy rest;
  
  ConsLoBuddy(Person first, ILoBuddy rest) {
    this.first = first;
    this.rest = rest;
  }
    
	// RETURNS: true iff this list contains the given person
	public boolean contains(Person p) {
		return (this.first.equals(p)) || this.rest.contains(p);
	}
	
	// RETURNS: the number of common buddies in this list and that list
	public int countCommonBuddies(ILoBuddy that) {
		if(that.contains(this.first)) {
			return 1 + this.rest.countCommonBuddies(that);
		}
		else {
			return this.rest.countCommonBuddies(that);
		}
	}
	


	// GIVEN: this list represents buddies that will be invited to the party,
	// newst contains buddies that are the farthest buddies of the host in this 
	// list
	// RETURNS: true iff p will be invited to the party
	public boolean willInvite(ILoBuddy newst, Person p) {
		if(newst.contains(p)) {
			return true;
		}
		else {
			ILoBuddy newGuests = newst.allDirectBuddies().minus(this);
			if(newGuests.empty()) {
				return false;
			}
			else {
				return this.union(newGuests).willInvite(newGuests, p);
			}
		}
	}
	
	// RETURNS: a list of all direct buddies of persons in this list 
	// (without replicates)
	public ILoBuddy allDirectBuddies() {
		return this.first.buddies.union(this.rest.allDirectBuddies());
	}
	
	// RETURNS: a list like this one, but with all elements in that list 
	// removed
	public ILoBuddy minus(ILoBuddy that) {
		if(that.contains(this.first)) {
			return this.rest.minus(that);
		}
		else {
			return new ConsLoBuddy(this.first, this.rest.minus(that));
		}
	}
	
	// RETURNS: true iff this list is empty
	public boolean empty() {
		return false;
	}
	
	// RETURNS: a list that contains all persons in this list and that list
	// (without replicates)
	public ILoBuddy union(ILoBuddy that) {
		if(that.contains(this.first)) {
			return this.rest.union(that);
		}
		else {
			return new ConsLoBuddy(this.first, this.rest.union(that));
		}
	}
	
	// RETURNS: the number of person that will show up at the party participated
	// by all people in this list
	public int partyCount() {
		ILoBuddy directBuddies = this.allDirectBuddies();
		ILoBuddy newBuddies = directBuddies.minus(this);
		if(newBuddies.empty()) {
			return this.count();
		}
		else {
			return this.union(directBuddies).partyCount();
		}
	}
	
	// RETURNS: the number of buddies in this list
	public int count() {
		return 1 + this.rest.count();
	}
}

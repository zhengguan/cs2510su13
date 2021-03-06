
// represents a Person with a user name and a list of buddies
class Person {

	String username;
	ILoBuddy buddies;

	Person(String username) {
		this.username = username;
		this.buddies = new MTLoBuddy();
	}

	// returns true if this Person has that as a direct buddy
	boolean hasDirectBuddy(Person that) {
		return this.buddies.contains(that);
	}

	// returns the number of people who will show up at the party 
	// given by this person
	int partyCount(){
		ConsLoBuddy participant = new ConsLoBuddy(this, new MTLoBuddy());
		return participant.partyCount();
	}

	// RETURNS: the number of people that are direct buddies 
	// of both this and that person
	int countCommonBuddies(Person that) {
		return this.buddies.countCommonBuddies(that.buddies);
	}

	// will the given person be invited to a party 
	// organized by this person?
	boolean hasDistantBuddy(Person that) {
		if(this.equals(that)) {
			return true;
		}
		else {
			ConsLoBuddy buddies = new ConsLoBuddy(this, new MTLoBuddy());
			return buddies.willInvite(buddies, that);
		}
	}

	// EFFECT: add the given buddy to this Person's list of buddies
	public void addBuddy(Person buddy) {
		this.buddies = new ConsLoBuddy(buddy, this.buddies);
	}
}

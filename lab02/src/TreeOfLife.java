// My solution for lab02 of cs2510 su13

import tester.Tester;

interface ILife {	
	// RETURNS: this life after a choice
	ILife choose(boolean choice);

	// RETURNS: the minimum number of decisions can make before dying 
	int minDicisionsBeforeDying();
	
	// RETURNS: the maximum number of decisions can make before dying of this 
	// life
	int maxDicisionsBeforeDying(); 
	
	// RETURNS: the number of dicisions can make if always answers yes
	int dicisionsAlwaysAnswerYes();
	
	// RETURNS: the number of dicisions can make if always answers no
	int dicisionsAlwaysAnswerNo();	
}

abstract class Life implements ILife{
	// RETURNS: the minimum number of decisions can make before dying 
	public int minDicisionsBeforeDying() {
		return 1 + 
				Math.min(this.choose(true).maxDicisionsBeforeDying(),
						 this.choose(false).maxDicisionsBeforeDying());
	}
	
	// RETURNS: the maximum number of decisions can make before dying of this 
	// life
	public int maxDicisionsBeforeDying() {
		return 1 + 
				Math.max(this.choose(true).maxDicisionsBeforeDying(),
						 this.choose(false).maxDicisionsBeforeDying());
	}
	
	// RETURNS: the number of dicisions can make if always answers yes
	public int dicisionsAlwaysAnswerYes() {
		return 1 + this.choose(true).dicisionsAlwaysAnswerYes();
	}
	
	// RETURNS: the number of dicisions can make if always answers no
	public int dicisionsAlwaysAnswerNo() {
		return 1 + this.choose(false).dicisionsAlwaysAnswerNo();
	}
	
	// RETURNS: the number of dicisions can make if always answers the given 
	// value
//	public int dicisionsAlwaysAnswer(boolean answer) {
//		return 1 + this.dicisionsAlwaysAnswer(answer);
//	}
	
}

// REPRESENTS: the state of die
class Die extends Life {
	// RETURNS: this life after a choice
	public ILife choose(boolean choice) {
		return this;
	}
	
	// RETURNS: the minimum number of decisions can make before dying 
	public int minDicisionsBeforeDying() {
		return 0;
	}
	
	// RETURNS: the maximum number of decisions can make before dying of this 
	// life
	public int maxDicisionsBeforeDying() {
		return 0;
	}
	
	// RETURNS: the number of dicisions can make if always answers yes
	public int dicisionsAlwaysAnswerYes() {
		return 0;
	}
	
	// RETURNS: the number of dicisions can make if always answers no
	public int dicisionsAlwaysAnswerNo() {
		return 0;
	}
}

// REPRESENTS: the state of on train
class OnTrack extends Life {
	// RETURNS: this life after chose jump or not
	public ILife choose(boolean choice) {
		return new Die();
	}
	
}

// REPRESENTS: the state of texting while driving
class TextingWhileDriving extends Life {
	// RETURNS: this life after chose whether stop
	public ILife choose(boolean choice) {
		if(choice == true) {
			return new AtFriendHouse();
		}
		else {
			return new Die();
		}
	}
}

// REPRESENTS: the state of arrive at friend's house
class AtFriendHouse extends Life {
	// RETURNS: this life after chose whether go in or not
	public ILife choose(boolean choice) {
		if(choice == true) {
			return new OfferedPizza();
		}
		else {
			return new Die();
		}
	}	
}

// REPRESENTS: the state of at friend's house and offered pizza
class OfferedPizza extends Life{
	// RETURNS: this life after chose whether eat pizza or not
	public ILife choose(boolean choice) {
		return new Die();
	}
}


class ILifeExamples {
	// Ex 06
	ILife die = new Die();
	ILife onTrack = new OnTrack();
	ILife textingWhileDriving = new TextingWhileDriving();
	ILife atFriendHouse = new AtFriendHouse();
	
	
	// tests for method maxDicisionsBeforeDying()
	boolean testMaxDicisionsBeforeDying(Tester t) {
		return
		t.checkExpect(new Die().maxDicisionsBeforeDying(), 0) &&
		t.checkExpect(onTrack.maxDicisionsBeforeDying(), 1) &&
		t.checkExpect(textingWhileDriving.maxDicisionsBeforeDying(), 3);
	}
	
	// tests for method minDicisionsBeforeDying()
	boolean testMinDicisionsBeforeDying(Tester t) {
		return
		t.checkExpect(die.minDicisionsBeforeDying(), 0) &&
		t.checkExpect(onTrack.minDicisionsBeforeDying(), 1) &&
		t.checkExpect(textingWhileDriving.minDicisionsBeforeDying(), 1);
	}
	
	// tests for method dicisionsAlwayAnswersYes()
	boolean testDicisionsAlswaysAnswerYes(Tester t) {
		return
		t.checkExpect(die.dicisionsAlwaysAnswerYes(), 0) &&
		t.checkExpect(onTrack.dicisionsAlwaysAnswerYes(), 1) &&
		t.checkExpect(textingWhileDriving.dicisionsAlwaysAnswerYes(), 3);
	}
	
	// tests for method dicisionsAlwaysAnswerNo()
	boolean testDicisionsAlwaysAnswerNo(Tester t) {
		return
		t.checkExpect(die.dicisionsAlwaysAnswerNo(), 0) &&
		t.checkExpect(onTrack.dicisionsAlwaysAnswerNo(), 1) &&
		t.checkExpect(textingWhileDriving.dicisionsAlwaysAnswerNo(), 1);
	}
}
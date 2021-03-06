// My solution for lab02 of cs2510 su13

import tester.Tester;

interface ILife {	
	// RETURNS: this life after a choice
	ILife choose(boolean choice);

	// RETURNS: the minimum number of decisions one can make before dying 
	int minDicisionsBeforeDying();
	
	// RETURNS: the maximum number of decisions one can make before dying of this 
	// life
	int maxDicisionsBeforeDying(); 
	
	// RETURNS: the number of dicisions can make if always answers yes
	int dicisionsAlwaysAnswerYes();
	
	// RETURNS: the number of dicisions can make if always answers the given 
	// value
	public int dicisionsAlwaysAnswer(boolean answer);
	
	// RETURNS: the number of dicisions can make if always answers no
	int dicisionsAlwaysAnswerNo();	
	
	// RETURNS: the number of dicisions can make if answers alternating 
	// between "yes" and "no" (start with yes)
	// STRATEGY: Template and hook pattern
	int dicisionsAlternateAnswer(boolean choice);
	
	// RETURNS: the list of questions one will face if always answer "yes"
	ILoQuestion questionAnswerYes();
	
	// RETURNS: the list questions on will face if always alternate between
	// "yes" and "no" (start from yes);
	ILoQuestion questionAlternateAnswer(boolean choice);
}

abstract class Life implements ILife{
	// RETURNS: the minimum number of decisions one can make before dying 
	public int minDicisionsBeforeDying() {
		return 1 + 
				Math.min(this.choose(true).maxDicisionsBeforeDying(),
						 this.choose(false).maxDicisionsBeforeDying());
	}
	
	// RETURNS: the maximum number of decisions one can make before dying of this 
	// life
	public int maxDicisionsBeforeDying() {
		return 1 + 
				Math.max(this.choose(true).maxDicisionsBeforeDying(),
						 this.choose(false).maxDicisionsBeforeDying());
	}
	
	// RETURNS: the number of dicisions can make if always answers yes
	public int dicisionsAlwaysAnswerYes() {
		return this.dicisionsAlwaysAnswer(true);
	}
	
	// RETURNS: the number of dicisions can make if always answers no
	public int dicisionsAlwaysAnswerNo() {
		return this.dicisionsAlwaysAnswer(false);
	}
	
	// RETURNS: the number of dicisions can make if always answers the given 
	// value
	public int dicisionsAlwaysAnswer(boolean answer) {
		return 1 + this.choose(answer).dicisionsAlwaysAnswer(answer);
	}
	
	// RETURNS: the number of dicisions can make if alternate 
	// between "yes" and "no" (start with yes)
	public int dicisionsAlternateAnswer(boolean choice) {
		return 1 + this.choose(choice).dicisionsAlternateAnswer(!choice);
	}
	
	// RETURNS: the list of questions one will face if always answer yes
	public ILoQuestion questionAnswerYes() {
		return new ConsLoQuestion(this.question(), this.choose(true).questionAnswerYes());
	}
	
	// RETURNS: question of this life
	abstract String question();
	
	// RETURNS: the list questions on will face if always alternate between
	// "yes" and "no" (start from yes);
	public ILoQuestion questionAlternateAnswer(boolean choice) {
		return new ConsLoQuestion(this.question(), 
				this.choose(choice).questionAlternateAnswer(!choice));
	}
}

// REPRESENTS: the state of die
class Die extends Life {
	// RETURNS: this life after a choice
	public ILife choose(boolean choice) {
		return this;
	}
	
	// RETURNS: the minimum number of decisions one can make before dying 
	public int minDicisionsBeforeDying() {
		return 0;
	}
	
	// RETURNS: the maximum number of decisions one can make before dying of this 
	// life
	public int maxDicisionsBeforeDying() {
		return 0;
	}
	
	// RETURNS: the number of decisions one can make if always answers the given 
	// value
	public int dicisionsAlwaysAnswer(boolean answer) {
		return 0;
	}
	
	// RETURNS: the number of decisions one can make if answers alternating 
	// between "yes" and "no" (start with yes)
	public int dicisionsAlternateAnswer(boolean choice) {
		return 0;
	}	
	
	// RETURNS: the list of questions one will face if always answer yes
	public ILoQuestion questionAnswerYes() {
		return new MTLoQuestion();
	}
	
	
	// RETURNS: question of this life
	// this method should never been called
	public String question() {
		return "";
	}	
	
	// RETURNS: the list questions one will face if always alternate between
	// "yes" and "no" (start from yes);
	public ILoQuestion questionAlternateAnswer(boolean choice) {
		return new MTLoQuestion();
	}
}

// REPRESENTS: the state of on train
class OnTrack extends Life {
	// RETURNS: this life after chose jump or not
	public ILife choose(boolean choice) {
		return new Die();
	}
	
	// RETURNS: question of this life
	public String question() {
		return "Do you jump into the street?";
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
	
	// RETURNS: question of this life
	public String question() {
		return "Do you stop?";
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
	
	// RETURNS: question of this life
	public String question() {
		return "Do you go in?";
	}
}

// REPRESENTS: the state of at friend's house and offered pizza
class OfferedPizza extends Life{
	// RETURNS: this life after chose whether eat pizza or not
	public ILife choose(boolean choice) {
		return new Die();
	}
	
	// RETURNS: question of this life
	public String question() {
		return "Do you eat some?";
	}
}

// REPRESENTS: a list of questions 
interface ILoQuestion {}

// REPRESENTS: an empty list of questions
class MTLoQuestion implements ILoQuestion {
	MTLoQuestion() {}
}

class ConsLoQuestion implements ILoQuestion {
	String fst; // first question
	ILoQuestion rst; // rest question
	
	ConsLoQuestion(String fst, ILoQuestion rst) {
		this.fst = fst;
		this.rst = rst;
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
	
	// tests for method dicisionsAlternateAnswer()
	boolean testDicisionAlternateAnswer(Tester t) {
		return
		t.checkExpect(die.dicisionsAlternateAnswer(true), 0) &&
		t.checkExpect(onTrack.dicisionsAlternateAnswer(true), 1) &&
		t.checkExpect(textingWhileDriving.dicisionsAlternateAnswer(true), 2);
		
	}
	
	// tests for method questionAnswerYes()j
	boolean testQuestionAnswerYes(Tester t) {
		MTLoQuestion mtloq = new MTLoQuestion();
		ConsLoQuestion loq1 = 
				new ConsLoQuestion("Do you jump into the street?", mtloq);
		ConsLoQuestion loq2 = 
				new ConsLoQuestion("Do you eat some?", mtloq);
		ConsLoQuestion loq3 = 
				new ConsLoQuestion("Do you go in?", loq2);
		ConsLoQuestion loq4 = 
				new ConsLoQuestion("Do you stop?", loq3);
		return
		t.checkExpect(onTrack.questionAnswerYes(), loq1) &&
		t.checkExpect(textingWhileDriving.questionAnswerYes(), loq4);
	}
	
	// tests for method questionAlternateAnswer()
	boolean testQuestionAlternateAnswer(Tester t) {
		MTLoQuestion mtloq = new MTLoQuestion();
		ConsLoQuestion loq1 = 
				new ConsLoQuestion("Do you jump into the street?", mtloq);
		ConsLoQuestion loq2 = 
				new ConsLoQuestion("Do you go in?", mtloq);
		ConsLoQuestion loq3 = 
				new ConsLoQuestion("Do you stop?", loq2);
		return
		t.checkExpect(onTrack.questionAlternateAnswer(true), loq1) &&
		t.checkExpect(textingWhileDriving.questionAlternateAnswer(true), loq3);
	}
}
// My solution for lab02 of cs2510 su13

import tester.Tester;

interface ILife {	
}

class Die implements ILife {
	
}

class OnTrain implements ILife {
//	ILife next(boolean jump) {
//		
//	}
}

class TextingWhileDriving implements ILife {
	
}

class AtFriendHouse extends Life {
	
}

abstract class Life implements ILife {
	
}

class ILifeExamples {
	// Ex 06
	ILife die = new Die();
	ILife onTrain = new OnTrain();
	ILife textingWhileDriving = new TextingWhileDriving();
	ILife atFriendHouse = new AtFriendHouse();
	Life life = new AtFriendHouse();
	
	// Ex 07
	// RETURNS: the least number of decisions one can make before die
	int leastDecision() {
		
	}
}
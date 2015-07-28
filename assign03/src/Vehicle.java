// assign 03
// http://www.ccs.neu.edu/course/cs2510su13-1/assignments/hw3.html
// Ex 18.3 of HTDC

// REPRESENT: a taxi vehicle 
interface ITaxiVehicle {}

abstract class ATaxiVehicle implements ITaxiVehicle {
	int idNum;
	int passengers;
	int pricePerMile;
}

class Cab extends ATaxiVehicle {
	
}

class Limo extends ATaxiVehicle {
	int minRental;
}

class Van extends ATaxiVehicle {
	boolean access;
}
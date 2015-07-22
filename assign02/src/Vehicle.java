import tester.Tester;

// assignment 2

// 1

// REPRESENTS: a vehicle
interface IVehicle {
	// RETURNS: the maximum distance this vehicle can travel on one tank of fuel
	double distanceTravelOnOneTank();
}

abstract class Vehicle implements IVehicle {
	int size; // size of fuel tank in gallons
	double gasMileage; // gas consumption in miles per gallon

	Vehicle(int size, double gasMileage) {
		this.size = size;
		this.gasMileage = gasMileage;
	}
	
	// RETURNS: the maximum distance this vehicle can travel on one tank of fuel
	public double distanceTravelOnOneTank() {
		return this.size * this.gasMileage; 
	}
}

class Car extends Vehicle {
	boolean pullTrailer; // whether pull a trailer
	
	Car(int size, double gasMileage, boolean pullTrailer) {
		super(size, gasMileage);
		this.pullTrailer = pullTrailer;
	}	
}

class Truck extends Vehicle {
	int maxLoad; // in kg
	
	Truck(int size, double gasMileage, int maxLoad) {
		super(size, gasMileage);
		this.maxLoad = maxLoad;
	}
}

class Bus extends Vehicle {
	int capacity; // in person	
	
	Bus(int size, double gasMileage, int capacity) {
		super(size, gasMileage);
		this.capacity = capacity;
	}
}


class VehicleExamples {
	IVehicle car = new Car(100, 1, true);
	IVehicle truck = new Truck(200, 1.5, 3000);
	IVehicle bus = new Bus(150, 1.2, 25);
	
	// tests for distanceTravelOnOneTank()
	boolean testDistanceTravelOnOneTank(Tester t) {
		return
		t.checkInexact(this.car.distanceTravelOnOneTank(), 100.0, 0.1) &&
		t.checkInexact(this.truck.distanceTravelOnOneTank(), 300.0, 0.1) &&
		t.checkInexact(this.bus.distanceTravelOnOneTank(), 180.0, 0.1);
	}
	
}
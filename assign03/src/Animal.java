// assign 03
// http://www.ccs.neu.edu/course/cs2510su13-1/assignments/hw3.html
// Ex 18.2 of HTDC

// REPRESENTS: a animal
interface IZooAnimal {}

abstract class AZooAnimalAnimal implements IZooAnimal {
	String name;
	int weight;
}

class Lion extends AZooAnimalAnimal {
	int meat;
}

class Snake extends AZooAnimalAnimal {
	int length;
}

class Monkey extends AZooAnimalAnimal {
	String food;
}
// lab 03
// http://www.ccs.neu.edu/course/cs2510su13-1/labs/lab3.html
// Ex 01

import tester.Tester;

// REPRESENTS: a book
interface IBook{
	
	// GIVEN: the number that represents today in the library's date-recording 
	// system
	// RETURNS: the number of days that this book is overdue
	int daysOverdue(int today);
	
	// GIVEN: the number that represents a day in the library's date-recording
	// system
	// RETURNS: true iff this book is overdue at that day
	boolean isOverdue(int day);
	
	// RETURNS: the fine of this book in cents if it is returned at the given 
	// day
	int computeFine(int day);
	
}

// REPRESENTS: a book
abstract class ABook implements IBook {
	String title;

	// day that this book is taken out, counted from the day that this library 
	// opened
	int dayTaken;   // the day that this book was taken out
	int overdueFee; // overdue fee in cents
//	int daysCanTakenOut; // the number of days that this book can be taken out
	
	ABook(String title, int dayTaken, int overdueFee) {
		this.title = title;
		this.dayTaken = dayTaken;
		this.overdueFee = overdueFee;
//		this.daysCanTakenOut = this.daysTakenOut();
	}

	// RETURNS: the number of days that this book can be taken out
	public int daysCanTakenOut() {
		return 14;
	}
	
	// GIVEN: the number that represents today in the library's date-recording 
	// system
	// RETURNS: the number of days that this book is overdue
	public int daysOverdue(int today) {
		return today - (this.dayTaken + this.daysCanTakenOut());
	}
	
	// GIVEN: the number that represents a day in the library's date-recording
	// system
	// RETURNS: true iff this book is overdue at that day
	public boolean isOverdue(int day) {
		return this.daysOverdue(day) > 0;
	}
	
	// RETURNS: the fine of this book in cents if it is returned at the given 
	// day
	public int computeFine(int day) {
		if (this.isOverdue(day)) {
			return this.overdueFee * this.daysOverdue(day);
		}
		else {
			return 0;
		}
	}
}


// REPRESENTS: a regular book
class Book extends ABook {
	String author;
	
	Book(String title, int dayTaken, int overdueFee, String author) {
		super(title, dayTaken, overdueFee);
		this.author = author;
	}
}

// REPRESENTS: a reference book
class RefBook extends ABook {
	RefBook(String title, int dayTaken, int overdueFee) {
		super(title, dayTaken, overdueFee);
	}
	
	// RETURNS: the number of days that this book can be taken out
	public int daysCanTakenOut() {
		return 2;
	}
}

// REPRESENTS: a audio book
class AudioBook extends ABook {
	String author;
	
	AudioBook(String title, int dayTaken, int overdueFee, String author) {
		super(title, dayTaken, overdueFee);
		this.author = author;
	}
}

class IBookExamples {
	IBook b1 = new Book("HTDP", 5400, 1, "Matthias Felleisen");
	IBook b2 = new RefBook("Webster dictionary", 5410, 1);
	IBook b3 = new AudioBook("Willpower", 5420, 1, "Kelly");
	
	// tests for method daysOverdue()
	boolean testDaysOverdue(Tester t) {
		return
		t.checkExpect(b1.daysOverdue(5414), 0) &&
		t.checkExpect(b2.daysOverdue(5411), -1) &&
		t.checkExpect(b3.daysOverdue(5440), 6);
	}
	
	// tests for method isOverdue()
	boolean testIsOverdue(Tester t) {
		return
		t.checkExpect(b1.isOverdue(5414), false) &&
		t.checkExpect(b2.isOverdue(5413), true) &&
		t.checkExpect(b3.isOverdue(5420), false);
	}
	
	// tests for method computeFine()
	boolean computeFine(Tester t) {
		return
		t.checkExpect(b1.computeFine(5414), 0) &&
		t.checkExpect(b2.computeFine(5414), 2) &&
		t.checkExpect(b3.computeFine(5434), 0);
	}
}
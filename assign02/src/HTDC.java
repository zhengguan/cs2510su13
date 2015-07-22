// assignment 02
// Ex 03


import tester.Tester;

import java.util.*;

// HTDC Ex 3.1

// REPRESENTS: a house record
class House {
	String kind; 
	int room; // number of rooms
	Address address;
	int price;
	
	public House(String kind, int room, 
				Address address, int price) {
		this.kind = kind;
		this.room = room;
		this.address = address;
		this.price = price;
	}
	
	// RETURNS: true iff this House has more rooms than that House
	boolean isBigger(House that) {
		return this.room > that.room;
	}
	
	// RETURNS: true iff this House is in the given city
	boolean thisCity(String city) {
		return this.address.thisCity(city);
	}
	
	// RETURNS: true iff this House is in the same city as that House
	boolean sameCity(House that) {
		return this.address.sameCity(that.address);
	}
}

class Address {
	int number; // street number
	String name; // street name
	String city; 
	
	Address(int number, String name, String city) {
		this.number = number;
		this.name = name;
		this.city = city;
	}
	
	// RETURNS: true iff this Address is in the given city
	boolean thisCity(String city) {
		return this.city.equals(city);
	}
	
	// RETURNS: true iff this Address is in the same city as that Address
	boolean sameCity(Address that) {
		return this.city.equals(that.city);
	}
}

// HTDC Ex 5.3 

// REPRESENTS: a list of real estate records
interface ILoRecord {}

// REPRESENTS: an empty list of real estate records
class MTLoRecord implements ILoRecord{
	public MTLoRecord() {}
}

// REPRESENTS: an non-empty list of real estate records
class ConsLoRecord implements ILoRecord{
	House fst;
	ILoRecord rst;
	
	public ConsLoRecord(House fst, ILoRecord rst) {
		this.fst = fst;
		this.rst = rst;
	}
}

class HouseExamples {
	Address ad1 = new Address(23, "Maple Street", "Brookline");
	Address ad2 = new Address(5, "Joye Road", "Newton");
	Address ad3 = new Address(83, "Winslow Road", "Waltham");
	
	House h1 = new House("Ranch", 7, ad1, 375000);
	House h2 = new House("Colonial", 9, ad2, 450000);
	House h3 = new House("Cape", 6, ad3, 235000);

	ILoRecord mtLoRecord = new MTLoRecord();
	ILoRecord lor1 = new ConsLoRecord(h1, mtLoRecord);
	ILoRecord lor2 = new ConsLoRecord(h2, lor1);
	ILoRecord lor3 = new ConsLoRecord(h2, lor2);
	
	// tests for method isBigger()
	boolean testIsBigger(Tester t) {
		return
		t.checkExpect(h1.isBigger(h2), false) &&
		t.checkExpect(h2.isBigger(h3), true);
	}
	
	
	// tests for method thisCity()
	boolean testThisCity(Tester t) {
		return
		t.checkExpect(h1.thisCity("Brookline"), true) &&
		t.checkExpect(h2.thisCity("Waltham"), false);
	}
	
	// tests for method sameCity()
	boolean testSameCity(Tester t) {
		return
		t.checkExpect(h1.sameCity(h1), true) &&
		t.checkExpect(h1.sameCity(h2), false);
	}
}

/******************************************************************************/

// HTDC Ex 4.4

// REPRESENTS: a bank account
interface IAccount {}

abstract class Account implements IAccount {
	String id; // id number
	String name; // customer's name
	int balance; // in dollar
	
	Account(String id, String name, int balance) {
		this.id = id;
		this.name = name;
		this.balance = balance;
	}
}

// REPRESENTS: a checking account
class CheckingAccount extends Account {
	int minBalance; // minimum balance in dollar
	
	public CheckingAccount(String id, String name, 
			int balance, int minBalance) {
		super(id, name, balance);
		this.minBalance = minBalance;
	}
}

// REPRESENTS: a savings account 
class SavingsAcount extends Account {
	int interestRate; // actual interest rate multiply 1000
	
	public SavingsAcount(String id, String name, 
			int balance, int interestRate) {
		super(id, name, balance);
		this.interestRate = interestRate;
	}
}

// REPRESENTS: a certificate of deposit
class CD extends Account {
	int interestRate; // actual interest rate multiply 1000
	Date maturityDate;
	
	CD(String id, String name, int balance,
			int interestRate, Date maturityDate) {
		super(id, name, balance);
		this.interestRate = interestRate;
		this.maturityDate = maturityDate;
	}
}

// REPRESENTS: a date
class Date {
	int year;
	int month;
	int day;
	
	Date(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
}

class AccountExamples {
	Date d1 = new Date(2005, 6, 1);
	
	IAccount earl = new CheckingAccount("1729", "Earl Gray", 1250, 500);
	IAccount ima = new CD("4104", "Ima flatt", 10123, 40, d1);
	IAccount annie = new SavingsAcount("2992", "Annie", 800, 25);
}

/******************************************************************************/

//HTDC Ex 10.2




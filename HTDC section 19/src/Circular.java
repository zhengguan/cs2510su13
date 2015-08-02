// Section 24 of HTDC

// REPRESENTS: a list of books
interface IBooks {}

// REPRESENTS: am empty list of books
class MtBooks implements IBooks {
	
}

// REPRESENTS: a non-empty list of books
class ConsBooks implements IBooks {
	Book fst;
	IBooks rst;
	
	ConsBooks(Book fst, IBooks rst) {
		this.fst = fst;
		this.rst = rst;
	}
}

class Book {
	String title;
	int price;
	int quantity;
	IAuthors ath;
	
	Book(String title, int price, int quantity, IAuthors ath) {
		this.title = title;
		this.price = price;
		this.quantity = quantity;
		this.ath = ath;
		
		ath.addBookToAll(this);
	}
	
}


// REPRESENTS: a list of authors
interface IAuthors {
	// EFFECT: add the book to all the authors on this list
	void addBookToAll(Book bk);
	
}

// REPRESENTS: an empty list of authors
class MtAuthros implements IAuthors{
	// EFFECT: add the book to all the authors on this list
	public void addBookToAll(Book bk) {
		return;
	}
}

// REPRESENTS: an non-empty list of authors
class ConsAuthors implements IAuthors {
	Author fst;
	IAuthors rst;
	
	ConsAuthors(Author fst, IAuthors rst) {
		this.fst = fst;
		this.rst = rst;
	}
	
	// EFFECT: add the book to all the authors on this list
	public void addBookToAll(Book bk) {
		this.fst.addBook(bk);
		this.rst.addBookToAll(bk);
		return;
	}
}

// REPRESENTS: an author
class Author {
	String fst;
	String lst;
	int dob;
	IBooks bk = new MtBooks();
	
	Author(String fst, String lst, int dob) {
		this.fst = fst;
		this.lst = lst;
		this.dob = dob;
	}
	
	void addBook(Book bk) {
		this.bk = new ConsBooks(bk, this.bk);
	}
}

class BookAuthorExamples {
	Author mf = new Author("Matthias", "Felleisen", 0);
	Author rb = new Author("Robert", "B. Findler", 0);
	Author sk = new Author("Shriram", "Krishnamurthi", 0);
	Author df = new Author("Daniel", "P. Friedman", 0);
	IAuthors mta = new MtAuthros();
	IAuthors loa1 = new ConsAuthors(mf, 
						new ConsAuthors(rb,
								new ConsAuthors(sk, mta)));
	IAuthors loa2 = new ConsAuthors(mf, 
						new ConsAuthors(df, mta));
	Book htdp = new Book("How to design program", 20, 1000, loa1);
	Book tll = new Book("The little lisper", 10, 100, loa2);
}
package business;

import java.util.*;
import model.*;
import dataaccess.Auth;
import dataaccess.DataAccessFacade;

public class AdminController implements LibraryMemberInterface, BookInterface, LibrarianInterface {

	DataAccessFacade df = new DataAccessFacade();

	@Override
	public void saveLibraryMember(String firstName, String lastName, String telephone, String street, String city,
			String state, String zip) {
		LibraryMember libraryMember = new LibraryMember(firstName, lastName, telephone,
				new Address(street, city, state, zip));
		df.saveNewMember(libraryMember);
	}

	@Override
	public void updateLibraryMember(String firstName, String lastName, String telephone, String street, String city,
			String state, String zip) {
		LibraryMember libraryMember = new LibraryMember(firstName, lastName, telephone,
				new Address(street, city, state, zip));
		df.updateLibraryMember(libraryMember);
	}

	@Override
	public List<LibraryMember> getAllLibraryMembers() {
		return new ArrayList<LibraryMember>(df.loadMemberMap().values());
	}

	@Override
	public List<Book> getAllBooks() {
		HashMap<String, Book> books = df.loadBookMap();
		return new ArrayList<Book>(books.values());
	}

	// TODO Handle list of authors
	@Override
	public void saveBook(String isbn, String title, int maxCheckoutLength, String fName, String lName) {
		Book book = new Book(isbn, title, maxCheckoutLength);
		book.addAuthor(new Author(fName, lName));
		df.saveNewBook(book);
	}

	@Override
	public void updateBook(String isbn, String title, int maxCheckoutLength, String fName, String lName) {
		Book book = new Book(isbn, title, maxCheckoutLength);
		book.addAuthor(new Author(fName, lName));
		df.updateBook(book);
	}

	@Override
	public boolean isBookAvailable(String isbn) {
		return df.isBookAvailable(isbn);
	}

	@Override
	public void addBookAuthor(String isbn, String fName, String lName) {
		Book book = df.getBook(isbn);
		book.addAuthor(new Author(fName, lName));
		df.updateBook(book);
	}

	@Override
	public List<Librarian> getAllLibrarians() {
		HashMap<Integer, Librarian> librarians = df.loadLibrarianMap();
		return new ArrayList<Librarian>(librarians.values());
	}

	@Override
	public void saveLibrarian(String fName, String lName, String telephone, String street, String city, String state,
			String zip, String username, String password) {
		Librarian librarian = new Librarian(fName, lName, telephone,
				new Address(street, city, state, zip), username, password, Auth.LIBRARIAN);
		df.saveLibrarian(librarian);
	}

	@Override
	public void updateLibrarian(int librarianId, String fName, String lName, String telephone, String street, String city, String state,
			String zip) {
		Librarian librarian = df.getLibrarianById(librarianId);
		librarian.setAddress(new Address(street, city, state, zip));
		librarian.setFirstName(fName);
		librarian.setLastName(lName);
		librarian.setTelephone(telephone);
		df.updateLibrarian(librarian);
	}
	
	@Override
	public void saveAdmin(String fName, String lName, String telephone, String street, String city, String state,
			String zip, String username, String password) {
		Admin admin = new Admin(fName, lName, telephone,
				new Address(street, city, state, zip), username, password, Auth.ADMIN);
		//df.sav(admin);
	}
}
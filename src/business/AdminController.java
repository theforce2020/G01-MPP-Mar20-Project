package business;

import java.util.*;
import model.*;
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
		df.updateBook(libraryMember);
	}

	@Override
	public List<LibraryMember> getAllLibraryMembers() {
		return df.getAllLibraryMembers();
	}

	@Override
	public List<Book> getAllBooks() {
		return df.getAllBooks();
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
		return df.getAllLibrarians();
	}

	@Override
	public void saveLibrarian(String fName, String lName, String telephone, String street, String city, String state,
			String zip) {
		Librarian librarian = new Librarian(fName, lName, telephone,
				new Address(street, city, state, zip));
		df.saveNewLibrarian(librarian);
	}

	@Override
	public void updateLibrarian(int librarianId, String fName, String lName, String telephone, String street, String city, String state,
			String zip) {
		Librarian librarian = df.getLibrarian(librarianId);
		librarian.setAddress(new Address(street, city, state, zip));
		librarian.setFirstName(fName);
		librarian.setLastName(lName);
		librarian.setTelephone(telephone);
		df.saveNewLibrarian(librarian);
	}
}
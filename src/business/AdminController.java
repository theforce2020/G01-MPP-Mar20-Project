package business;

import java.util.*;
import model.*;
import dataaccess.DataAccessFacade;

public class AdminController implements LibraryMemberInterface, BookInterface {

	DataAccessFacade df = new DataAccessFacade();

	public void addMember(LibraryMember member) {
		df.saveNewMember(member);
	}

	public void addLibrarian(Librarian librarian) {

	}

	public void addBook(Book bk) {

	}

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
	public List<LibraryMember> allLibraryMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> allBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveBook(String isbn, String title, int maxCheckoutLength, String fName, String lName, String telephone,
			String bio, String street, String city, String state, String zip) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBook(String isbn, String title, int maxCheckoutLength, String fName, String lName, String telephone,
			String bio, String street, String city, String state, String zip) {
		// TODO Auto-generated method stub
		df.updateBook(book);
	}

	@Override
	public boolean isBookAvailable(String isbn) {
		// TODO Auto-generated method stub
		return false;
	}
}

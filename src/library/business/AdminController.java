package library.business;

import library.model.*;
import library.dataaccess.Auth;
import library.dataaccess.DataAccessFacade;
import library.exceptions.UsernameInUseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminController implements LibraryMemberInterface, BookInterface, LibrarianInterface, AdminInterface {

	DataAccessFacade dataFacade = new DataAccessFacade();

	@Override
	public void saveLibraryMember(String memberId, String firstName, String lastName, String telephone, String street, String city,
			String state, String zip) {
		LibraryMember libraryMember = new LibraryMember(memberId, firstName, lastName, telephone,
				new Address(street, city, state, zip));
		dataFacade.saveNewMember(libraryMember);
	}

	@Override
	public void updateLibraryMember(String memberId, String firstName, String lastName, String telephone, String street, String city,
			String state, String zip) {
		LibraryMember libraryMember = new LibraryMember(memberId, firstName, lastName, telephone,
				new Address(street, city, state, zip));
		dataFacade.updateLibraryMember(libraryMember);
	}

	@Override
	public List<LibraryMember> getAllLibraryMembers() {
		return new ArrayList<LibraryMember>(dataFacade.loadMemberMap().values());
	}

	@Override
	public List<Book> getAllBooks() {
		HashMap<String, Book> books = dataFacade.loadBookMap();
		return new ArrayList<Book>(books.values());
	}

	// TODO Handle list of authors
	@Override
	public void saveBook(String isbn, String title, int maxCheckoutLength, String fName, String lName) {
		Book book = new Book(isbn, title, maxCheckoutLength);
		book.addAuthor(new Author(fName, lName));
		dataFacade.saveNewBook(book);
	}

	@Override
	public void updateBook(Book book) {
		dataFacade.updateBook(book);
	}

	@Override
	public boolean isBookAvailable(String isbn) {
		return dataFacade.isBookAvailable(isbn);
	}

	@Override
	public void addBookAuthor(String isbn, String fName, String lName) {
		Book book = dataFacade.getBook(isbn);
		book.addAuthor(new Author(fName, lName));
		dataFacade.updateBook(book);
	}

	public List<SystemUser> getAllSystemUsers() {
		HashMap<String, SystemUser> librarians = dataFacade.loadUserMap();
		return new ArrayList<SystemUser>(librarians.values());
	}

	@Override
	public void saveLibrarian(String fName, String lName, String telephone, String street, String city, String state,
			String zip, String username, String password) throws UsernameInUseException {
		SystemUser systemUser = new SystemUser(username, password, Auth.LIBRARIAN);
		int userId = dataFacade.saveSystemUser(systemUser);
		
		Librarian librarian = new Librarian(userId, fName, lName, telephone,
				new Address(street, city, state, zip));
		dataFacade.saveLibrarian(librarian);
	}

	@Override
	public void updateLibrarian(int librarianId, String fName, String lName, String telephone, String street, String city, String state,
			String zip) {
		Librarian librarian = dataFacade.getLibrarianById(librarianId);
		librarian.setAddress(new Address(street, city, state, zip));
		librarian.setFirstName(fName);
		librarian.setLastName(lName);
		librarian.setTelephone(telephone);
		dataFacade.updateLibrarian(librarian);
	}
	
	@Override
	public void saveAdmin(String fName, String lName, String telephone, String street, String city, String state,
			String zip, String username, String password) throws UsernameInUseException {
		SystemUser systemUser = new SystemUser(username, password, Auth.ADMIN);
		int userId = dataFacade.saveSystemUser(systemUser);
		
		Admin admin = new Admin(userId, fName, lName, telephone,
				new Address(street, city, state, zip));
		dataFacade.saveAdmin(admin);
	}

	@Override
	public void updateAdmin(int adminId, String fName, String lName, String telephone, String street, String city,
			String state, String zip) {
		Admin admin = dataFacade.getAdminById(adminId);
		admin.setAddress(new Address(street, city, state, zip));
		admin.setFirstName(fName);
		admin.setLastName(lName);
		admin.setTelephone(telephone);
		dataFacade.updateAdmin(admin);
	}

	@Override
	public List<Admin> getAllAdmins() {
		HashMap<Integer, Admin> admins = dataFacade.loadAdminMap();
		return new ArrayList<Admin>(admins.values());
	}

	@Override
	public List<Librarian> getAllLibrarians() {
		HashMap<Integer, Librarian> librarians = dataFacade.loadLibrarianMap();
		return new ArrayList<Librarian>(librarians.values());
	}

	@Override
	public boolean doesMemberExist(String memberId) {
		return dataFacade.doesMemberExist(memberId);
	}

	@Override
	public void deleteMember(String memberId) {
		dataFacade.deleteMember(memberId);
	}

	@Override
	public Book getBook(String isbn) {
		return dataFacade.getBook(isbn);
	}
}
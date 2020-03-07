package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess1;
import dataaccess.DataAccess1;
import dataaccess.TestData;
import dataaccess.User;
import exceptions.LoginException;
import model.Address;
import model.Author;
import model.Book;
import model.CheckoutRecord;
import model.LibraryMember;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	
	public void login(String id, String password) throws LoginException {
		DataAccess1 da = new DataAccessFacade1();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
	}
	@Override
	public List<String> allMemberIds() {
		DataAccess1 da = new DataAccessFacade1();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess1 da = new DataAccessFacade1();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}
	
	public List<String> allAuthors(){
		DataAccess1 da = new DataAccessFacade1();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readUserMap().keySet());
		return retval;
	}
	
	public Author getAuthor(String aName) {
		TestData td = new TestData();
		List<Author> aList =td.allAuthors;
		Author objA = null;
		for(Author a: aList) {
			if(a.getFirstName().equals(aName))
				objA = a;
		}
		return objA;
	}
		
	public void addBook(Book b) {
		System.out.println(b);
	}
	
	@Override
	public HashMap<String, CheckoutRecord> getCheckoutRecords(){
		DataAccess1 da = new DataAccessFacade1();
    	HashMap<String, CheckoutRecord> map = da.readCheckoutRecordMap();
    	return map;
	}
	public void addCheckoutRecord(String recordId, Book book, int copyNum, LocalDate checkoutDate, LocalDate dueDate, LibraryMember member) {
		//CheckoutRecordEntry entryRecord=new CheckoutRecordEntry(recordId, book, copyNum, checkoutDate, dueDate);
		CheckoutRecord record=new CheckoutRecord(recordId, book, copyNum, checkoutDate, dueDate, member);
		DataAccessFacade1 da=new DataAccessFacade1();
		//da.addCheckoutRecord(recordId, book, copyNum, checkoutDate, dueDate);
		da.addCheckoutRecord(record);
	}
	
	
	@Override
	public HashMap<String, LibraryMember> allLibraryMember() {
		DataAccess1 da = new DataAccessFacade1();
    	HashMap<String, LibraryMember> map = da.readMemberMap();
		return map;
	}
	@Override
	public void addNewMember(String memid, String fname, String lname, String tel, String street, String city,
			String state, String zip) {
		Address ad=new Address(street, city,state, zip);
		LibraryMember member=new LibraryMember(memid, fname, lname, tel, ad);
		DataAccessFacade1 da=new DataAccessFacade1();
		da.saveNewMember(member);
	}
	
	
	
	
	@Override
	public List<Author> getAuthors() {
		TestData td = new TestData();
		return td.allAuthors;
	}
	@Override
	public void addBook(String tfBookIsbn, String tfBookTitle, int maxCheckoutLength, List<Author> authors) {
		DataAccessFacade1 da=new DataAccessFacade1();
		Book b=new Book(tfBookIsbn, tfBookTitle, maxCheckoutLength, authors);
		da.updateBook(b);
	}
	@Override
	public HashMap<String, Book> getBooks() {
		DataAccess1 da = new DataAccessFacade1();
    	HashMap<String, Book> map = da.readBooksMap();
    	return map;
	}
	@Override
	public void addBookCopy(Book b) {
		DataAccess1 da = new DataAccessFacade1();
		da.addBookCopy(b);
		
	}
	@Override
	public Book getBook(String isbn) {
		DataAccess1 da = new DataAccessFacade1();    	
    	return da.getBook(isbn);
	}
	@Override
	public LibraryMember getMember(String memberId) {
		DataAccess1 da = new DataAccessFacade1();    	
    	return da.getMember(memberId);
	}
	@Override
	public void updateBook(Book book) {
		DataAccessFacade1 da=new DataAccessFacade1();
		da.updateBook(book);
	}
	
	@Override
	public CheckoutRecord getCheckoutRecordById(String recordId) {
		DataAccess1 da = new DataAccessFacade1();    	
    	return da.getCheckoutRecordById(recordId);
	}
}

	
	
	
	
	
	


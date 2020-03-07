package business;

import java.util.List;


	import java.time.LocalDate;
	import java.util.HashMap;
	import java.util.List;

import model.Author;
import model.Book;
import model.CheckoutRecord;
import dataaccess.DataAccess;
	importdataaccess.DataAccessFacade;
import exceptions.LoginException;
import model.LibraryMember;
    
	public interface ControllerInterface {
		public void login(String id, String password) throws LoginException;
		public List<String> allMemberIds();
		public List<String> allBookIds();
		
		public HashMap<String, LibraryMember> allLibraryMember();
		public void addNewMember(String memid,String fname,String lname,String tel,String street,String city,String state, String zip);
		public List<Author> getAuthors();
		public void addBook(String tfBookIsbn,String tfBookTitle, int maxCheckoutLength, List<Author> authors);
		public HashMap<String, Book> getBooks();
		public void addBookCopy(Book b);
		public HashMap<String, CheckoutRecord> getCheckoutRecords();
		public void addCheckoutRecord(String recordId, Book book, int copyNum, LocalDate checkoutDate, LocalDate dueDate, LibraryMember member);
		public Book getBook(String isbn);
		public LibraryMember getMember(String memberId);
		public void updateBook(Book book);
		public CheckoutRecord getCheckoutRecordById(String recordId);
		
	}

	
	
	
	
	


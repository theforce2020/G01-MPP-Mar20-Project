package dataaccess;



import java.util.HashMap;

import model.Book;
import model.CheckoutRecord;
import model.LibraryMember;

public interface DataAccess { 
	public void saveNewMember(LibraryMember member); 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public HashMap<String,User> readUserMap();

	
	public void updateBook(Book book);
	public void addBookCopy(Book book);
	public void addCheckoutRecord(CheckoutRecord chR);
	//public void addCheckoutRecord(String recordId, Book book, int copyNum, LocalDate checkoutDate, LocalDate dueDate);
	public HashMap<String, CheckoutRecord> readCheckoutRecordMap();
	public  Book getBook(String isbn);
	public LibraryMember getMember(String memberId);
	public  CheckoutRecord getCheckoutRecordById(String recordId);
}

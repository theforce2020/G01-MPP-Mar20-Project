package dataaccess;

import java.util.HashMap;

import model.Book;
import model.CheckoutRecord;
import model.LibraryMember;

public interface DataAccess { 
	
	public HashMap<String, Book> readBooksMap();
	
	public HashMap<String, User> readUserMap();
	
	public HashMap<String, LibraryMember> readMemberMap();
	
	public void saveNewMember(LibraryMember member);
	
	public void saveNewBook(Book book); 
	
	public HashMap<String, CheckoutRecord> readCheckoutRecordMap();
	
	public void saveNewCheckoutRecord(CheckoutRecord checkoutRecord);
}
package library.dataaccess;

import java.util.HashMap;

import model.Admin;
import model.Book;
import model.CheckoutRecord;
import model.Librarian;
import model.LibraryMember;
import model.SystemUser;

public interface DataAccess { 
	
	public HashMap<String, LibraryMember> loadMemberMap();
	
	public void saveNewMember(LibraryMember member);
	
	public void saveNewBook(Book book); 
	
	public HashMap<String, CheckoutRecord> loadCheckoutRecordMap();
	
	public void saveNewCheckoutRecord(CheckoutRecord checkoutRecord);
	
	public Book getBook(String isbn);
	
	public void updateBook(Book bk);
	
	public Librarian getLibrarianById(int librarianId);
	
	public boolean isBookAvailable(String isbn);
	
	public void saveLibrarian(Librarian librarian);
	
	public void updateLibrarian(Librarian librarian);
	
	public HashMap<String, Book> loadBookMap();
	
	public void updateLibraryMember(LibraryMember member);
	
	public Admin getAdminById(int adminId);
	
	public SystemUser getSystemUser(String username);
}
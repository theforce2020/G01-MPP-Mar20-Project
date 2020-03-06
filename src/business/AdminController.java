package business;
import java.util.*;
import model.*;
import dataaccess.DataAccessFacade;

public class AdminController implements MemberInterface, BookInterface {
	
	DataAccessFacade df = new DataAccessFacade();

	public void addMember(LibraryMember member) {
		df.saveNewMember(member);
	}
	
	public void addLibrarian(Librarian librarian) {
		
	}
	
	public void addBook(Book bk) {
		
	}

	@Override
	public void saveLibraryMember(LibraryMember libraryMember) {
		df.saveNewMember(libraryMember);
	}

	@Override
	public void updateLibraryMember(LibraryMember libraryMember) {
		
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
	public void saveBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		df.updateBook(book);
	}

	@Override
	public boolean isBookAvailable(String isbn) {
		// TODO Auto-generated method stub
		return false;
	}
}
package business;
import java.util.*;
import model.*;
import dataaccess.DataAccessFacade;

public class AdminController {
	
	DataAccessFacade df = new DataAccessFacade();

	public void addMember(LibraryMember member) {
		df.saveNewMember(member);
	}
	public void addLibrarian(Librarian librarian) {
		
	}
	public void addBook(Book bk) {
		
	}
}

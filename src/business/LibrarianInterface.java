package business;

import java.util.List;

import model.Librarian;

public interface LibrarianInterface {
	
	public void saveLibrarian(String fName, String lName, String telephone, String street, String city, String state, String zip);
	
	public void updateLibrarian(int librarianId, String fName, String lName, String telephone, String street, String city, String state, String zip);
	
	public List<Librarian> getAllLibrarians();
}
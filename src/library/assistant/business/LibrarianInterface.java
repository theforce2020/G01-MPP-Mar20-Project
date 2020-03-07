package library.assistant.business;

import java.util.List;

import library.assistant.data.model.Librarian;

public interface LibrarianInterface {
	
	public void saveLibrarian(String fName, String lName, String telephone, String street, String city, String state, String zip, String username, String password);
	
	public void updateLibrarian(int librarianId, String fName, String lName, String telephone, String street, String city, String state, String zip);
	
	public void saveAdmin(String fName, String lName, String telephone, String street, String city, String state, String zip, String username, String password);
	
	public List<Librarian> getAllLibrarians();
}
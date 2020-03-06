package business;

import java.util.List;

import model.Librarian;

public interface LibrarianInterface {
	
	public void saveLibrarian(Librarian librarian);
	
	public void updateLibrarian(Librarian librarian);
	
	public List<Librarian> allLibrarians();
}
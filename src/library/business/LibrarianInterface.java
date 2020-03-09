package library.business;

import library.exceptions.UsernameInUseException;
import library.model.Librarian;

import java.util.List;

public interface LibrarianInterface {

    void saveLibrarian(String fName, String lName, String telephone, String street, String city, String state, String zip, String username, String password) throws UsernameInUseException;

    void updateLibrarian(int librarianId, String fName, String lName, String telephone, String street, String city, String state, String zip);

    List<Librarian> getAllLibrarians();
}
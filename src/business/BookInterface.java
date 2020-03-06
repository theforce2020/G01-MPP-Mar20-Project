package business;

import java.util.List;

import model.Book;

public interface BookInterface {
	
	public List<Book> allBooks();
	
	public void saveBook(String isbn, String title, int maxCheckoutLength, String fName, String lName, String telephone, String bio, 
			String street, String city, String state, String zip);
	
	public void updateBook(String isbn, String title, int maxCheckoutLength, String fName, String lName, String telephone, String bio, 
			String street, String city, String state, String zip);
	
	public boolean isBookAvailable(String isbn);
}
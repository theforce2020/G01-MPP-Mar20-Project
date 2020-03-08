package library.business;

import java.util.List;

import library.data.model.Book;

public interface BookInterface {
	
	public List<Book> getAllBooks();
	
	public void saveBook(String isbn, String title, int maxCheckoutLength, String fName, String lName);
	
	public void updateBook(String isbn, String title, int maxCheckoutLength, String fName, String lName);
	
	public boolean isBookAvailable(String isbn);
	
	public void addBookAuthor(String isbn, String fName, String lName);
}
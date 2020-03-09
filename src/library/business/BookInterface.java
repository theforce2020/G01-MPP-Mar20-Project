package library.business;

import library.model.Book;
import library.model.BookCopy;

import java.util.List;

public interface BookInterface {
	
	public List<Book> getAllBooks();
	
	public void saveBook(String isbn, String title, int maxCheckoutLength, String fName, String lName);
	
	public void updateBook(Book book);
	
	public boolean isBookAvailable(String isbn);
	
	public void deleteBook(String isbn);
	
	public void addBookAuthor(String isbn, String fName, String lName);
	
	public Book getBook(String isbn);
	
	public BookCopy getBookCopy(String isbn, int copyNum);
}
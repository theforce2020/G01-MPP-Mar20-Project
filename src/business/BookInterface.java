package business;

import java.util.List;

import model.Book;

public interface BookInterface {
	
	public List<Book> allBooks();
	
	public void saveBook(Book book);
	
	public void updateBook(Book book);
	
	public boolean isBookAvailable(String isbn);
	
	
}
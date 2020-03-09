package library.business;

import library.exceptions.LibrarySystemException;
import library.model.Book;
import library.model.BookCopy;

import java.util.List;

public interface BookInterface {

    List<Book> getAllBooks();

    void saveBook(String isbn, String title, int maxCheckoutLength, String fName, String lName);

    void updateBook(Book book);

    boolean isBookAvailable(String isbn);

    void deleteBook(String isbn) throws LibrarySystemException;

    void addBookAuthor(String isbn, String fName, String lName);

    Book getBook(String isbn);

    BookCopy getBookCopy(String isbn, int copyNum);
}
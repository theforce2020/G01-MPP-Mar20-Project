package library.business;

import library.model.Book;

import java.util.List;

public interface BookInterface {

    List<Book> getAllBooks();

    void saveBook(String isbn, String title, int maxCheckoutLength, String fName, String lName);

    void updateBook(String isbn, String title, int maxCheckoutLength, String fName, String lName);

    boolean isBookAvailable(String isbn);

    void addBookAuthor(String isbn, String fName, String lName);
}
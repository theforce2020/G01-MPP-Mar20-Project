package library.model;

import java.io.Serializable;
import java.util.*;

/**
 *
 */
final public class Book implements Serializable {

    private static final long serialVersionUID = 6110690276685962829L;

    private List<BookCopy> copies;

    private List<Author> authors;

    private String isbn;

    private String title;

    private int maxCheckoutLength;

    public Book(String isbn, String title, int maxCheckoutLength) {
        this.isbn = isbn;
        this.title = title;
        this.maxCheckoutLength = maxCheckoutLength;
        this.authors = new ArrayList<Author>();
        this.copies = new ArrayList<>();

        addCopies(maxCheckoutLength);
    }

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
        this.maxCheckoutLength = 1;
        this.authors = new ArrayList<Author>();

        this.copies = new ArrayList<>();
        addCopies(maxCheckoutLength);
    }

    public Book(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
        this.isbn = isbn;
        this.title = title;
        this.maxCheckoutLength = maxCheckoutLength;
        this.authors = Collections.unmodifiableList(authors);

        this.copies = new ArrayList<>();
        addCopies(maxCheckoutLength);
    }

    private void addCopies(int count) {
        int id = 0;
        while (id < count) {
            BookCopy copy = new BookCopy(this, id);
            this.copies.add(copy);
            id++;
        }
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public List<Integer> getCopyNums() {
        List<Integer> retVal = new ArrayList<>();
        for (BookCopy c : copies) {
            retVal.add(c.getCopyNum());
        }
        return retVal;
    }

    public void addCopy() {
        copies.add(new BookCopy(this, copies.size() + 1));
    }

    @Override
    public boolean equals(Object ob) {
        if (ob == null) return false;
        if (ob.getClass() != getClass()) return false;
        Book b = (Book) ob;
        return b.isbn.equals(isbn);
    }

    public boolean isAvailable() {
        if (copies == null) {
            return false;
        }
        return copies.stream()
                .map(BookCopy::isAvailable)
                .reduce(false, (x, y) -> x || y);
    }

    @Override
    public String toString() {
        return "isbn: " + isbn + ", maxLength: " + maxCheckoutLength + ", available: " + isAvailable();
    }

    public int getNumCopies() {
        return copies.size();
    }

    public String getTitle() {
        return title;
    }

    public List<BookCopy> getCopies() {
        return copies;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public String getIsbn() {
        return isbn;
    }

    public BookCopy getNextAvailableCopy() {
        Optional<BookCopy> optional = copies.stream()
                .filter(x -> x.isAvailable()).findFirst();
        return optional.isPresent() ? optional.get() : null;
    }

    public BookCopy getCopy(int copyNum) {
        for (BookCopy c : copies) {
            if (copyNum == c.getCopyNum()) {
                return c;
            }
        }
        return null;
    }

    public int getMaxCheckoutLength() {
        return maxCheckoutLength;
    }

    public int getAvailableCopies() {
        return (int) copies.stream().filter(BookCopy::isAvailable).count();
    }
}
package library.model;

import java.io.Serializable;
import java.util.Date;

public class CheckoutRecordEntry implements Serializable {

	private static final long serialVersionUID = 7647940524688830424L;
	
	private Date checkoutDate;
	
	private Date dueDate;
	private Date returnDate;
	private BookCopy bookCopy;
	private String isbn;

	CheckoutRecordEntry(BookCopy bookCopy,String isbn, Date checkoutDate, Date dueDate){
		
		this.isbn = isbn;
		this.bookCopy = bookCopy;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
	}

	public BookCopy getBookCopy() {
		return bookCopy;
	}

	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public double checkFines() {
		return returnDate.after(dueDate) ? 50.0 : 0.0;
	}
	
	public boolean equals(Object ob) {
		if(ob == null) return false;
		if(ob.getClass() != getClass()) return false;
		CheckoutRecordEntry b = (CheckoutRecordEntry)ob;
		return b.isbn.equals(isbn) && b.bookCopy.getCopyNum() == bookCopy.getCopyNum();
	}
}
package model;

import java.io.Serializable;
import java.util.Date;

public class CheckoutRecordEntry implements Serializable {

	private static final long serialVersionUID = 7647940524688830424L;
	
	private Date checkoutDate;
	
	private Date dueDate;
	private Date returnDate;
	Librarian lb;
	private BookCopy bookCopy;
	
	private LibraryMember libraryMember;

	CheckoutRecordEntry(Librarian lb, CheckoutRecord checkoutRecord, LibraryMember libraryMember, BookCopy bookCopy, Date checkoutDate, Date dueDate){
		this.lb = lb;
		this.libraryMember = libraryMember;
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


	public LibraryMember getLibraryMember() {
		return libraryMember;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
}
package model;

import java.io.Serializable;
import java.util.Date;

public class CheckoutRecordEntry implements Serializable {

	private static final long serialVersionUID = 7647940524688830424L;
	
	private Date checkoutDate;
	
	private Date dueDate;
	
	private CheckoutRecord checkoutRecord;
	
	private BookCopy bookCopy;
	
	private LibraryMember libraryMember;

	CheckoutRecordEntry(CheckoutRecord checkoutRecord, LibraryMember libraryMember, BookCopy bookCopy, Date checkoutDate, Date dueDate){
		this.checkoutRecord = checkoutRecord;
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

	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}

	public LibraryMember getLibraryMember() {
		return libraryMember;
	}
}
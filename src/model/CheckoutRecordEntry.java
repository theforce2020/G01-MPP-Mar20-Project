package model;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecordEntry implements Serializable {

	private static final long serialVersionUID = 7647940524688830424L;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private BookCopy bookCopy;
	private String entryRecordId;

	public CheckoutRecordEntry(String erId, Book book, int copyNum, LocalDate chD, LocalDate dD){
		this.entryRecordId = erId;
		this.checkoutDate = chD;
		this.dueDate = dD;
	}

	public String getEntryRecordId() {
		return entryRecordId;
	}

	public BookCopy getBookCopy() {
		return bookCopy;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate ld) {
		this.dueDate = ld;
	}
	public void setCheckoutDate(LocalDate chd) {
		this.checkoutDate = chd;
	}
}
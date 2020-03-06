package model;

import java.io.Serializable;
import java.util.Date;

import util.LibraryUtil;

public class CheckoutRecord implements Serializable {
	
	private static final long serialVersionUID = 4580065230999673218L;
	
	private CheckoutRecordEntry[] checkoutRecordEntries;

	private LibraryMember libraryMember;
	
	public CheckoutRecord(Book book, int copyNum, Date checkoutDate, Date dueDate, LibraryMember libraryMember){
		this.libraryMember = libraryMember;
		this.checkoutRecordEntries = new CheckoutRecordEntry[]{new CheckoutRecordEntry(this, book.getNextAvailableCopy(), checkoutDate, dueDate)};	
	}
	
	public void addCheckoutRecordEntry(Book book) {
		CheckoutRecordEntry[] newArr = new CheckoutRecordEntry[checkoutRecordEntries.length + 1];
		System.arraycopy(checkoutRecordEntries, 0, newArr, 0, checkoutRecordEntries.length);
		Date checkoutDate = new Date();
		newArr[checkoutRecordEntries.length] = new CheckoutRecordEntry(this, book.getNextAvailableCopy(), checkoutDate, LibraryUtil.calculateDueDate(checkoutDate, book.getMaxCheckoutLength()));
		checkoutRecordEntries = newArr;
	}
	
	public LibraryMember getLibraryMember() {
		return this.libraryMember;
	}
	
	public CheckoutRecordEntry[] getCheckoutRecordEntries() {
		return checkoutRecordEntries;
	}
}
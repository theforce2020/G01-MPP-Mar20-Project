package model;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecord implements Serializable {
	
	private static final long serialVersionUID = 4580065230999673218L;
	private String checkoutRecordId;
	private CheckoutRecordEntry checkoutRecordEntry;
	private LibraryMember member;
	
	public CheckoutRecord(String recordId, Book book, int copyNum, LocalDate chD, LocalDate dD, LibraryMember m){
		//System.out.println("insde Checkout Record const");
		this.checkoutRecordId = recordId; 
		String entryId = recordId + String.valueOf(Math.random()*100);
		this.checkoutRecordEntry = new CheckoutRecordEntry(entryId, book, copyNum, chD, dD);
		//System.out.println(checkoutRecordEntry);
		this.member = m;
	}
	
	public CheckoutRecordEntry getCheckoutRecordEntry() {
		return this.checkoutRecordEntry;
	}
	
	public String getCheckoutRecordId() {
		return this.checkoutRecordId;
	}
	
	public LibraryMember getMember() {
		return this.member;
	}
	
	@Override
	public String toString() {
		return "CheckoutRecord: " + checkoutRecordId + " "+ this.checkoutRecordEntry;
	}
	
}

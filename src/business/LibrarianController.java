package business;

import java.util.Date;
import java.util.HashMap;

import dataaccess.DataAccessFacade;
import model.*;
import exceptions.*;

public class LibrarianController implements CheckInterface {

	DataAccessFacade df = new DataAccessFacade();

	@Override
	public void checkOutBook(String isbn, String memberId) throws CheckoutException {
		Book bk;
		LibraryMember lb;
		
		HashMap<String, Book> allBook= df.readBooksMap();
		HashMap<String,LibraryMember> allMember = df.readMemberMap();
		bk = allBook.get(isbn);
		lb = allMember.get(memberId);
		if(bk == null) {
			throw new CheckoutException("We dont have that book!");
		}
		if(lb == null) { 
			throw new CheckoutException("Sorry you are not a member. Let's Sign you in first!");
		}
		BookCopy bkCopi = bk.getNextAvailableCopy();
		if (bkCopi != null) {
			int copyNum = bkCopi.getCopyNum();
			CheckoutRecord cr = lb.getRecord();
			if (cr == null) {
				
				CheckoutRecord newCR = new CheckoutRecord(bk, copyNum, lb);
				lb.setRecord(newCR);
				bkCopi.changeAvailability();
			} else {
				cr.addCheckoutRecordEntry(bk,copyNum);
			}

		} else {
			throw new CheckoutException("The book is not available");
		}
	}


	@Override
	public void checkInBook(String isbn, int copyNum, String memberId) throws CheckoutException{
		Book bk;
		LibraryMember member;
		
		HashMap<String, Book> allBook= df.readBooksMap();
		HashMap<String,LibraryMember> allMember = df.readMemberMap();
		
		bk = allBook.get(isbn);
		member = allMember.get(memberId);
		
		if(bk == null) {
			throw new CheckoutException("We dont have that book!");
		}
		
		BookCopy copy = bk.getCopy(copyNum);
		if(copy == null) {
			throw new CheckoutException("This copy is not from us!");
		}
		
		if(member == null) { 
			throw new CheckoutException("Sorry you are not a member. Let's Sign you in first!");
		}
		
		CheckoutRecord cr = member.getRecord();
		CheckoutRecordEntry[] listCk = cr.getEntries();
		
		 for(CheckoutRecordEntry ce: listCk) {
			 if(ce.getBookCopy().equals(copy)) {
				 ce.setReturnDate(new Date());
				 ce.getBookCopy().changeAvailability();
			 }
		 }
	}
}

package business;

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
			CheckoutRecord cr = lb.getRecord();
			if (cr == null) {
				int copyNum = bkCopi.getCopyNum();
				CheckoutRecord newCR = new CheckoutRecord(bk, copyNum, lb);
				lb.setRecord(newCR);
				bkCopi.changeAvailability();
			} else {
				cr.addCheckoutRecordEntry(bk,lb);
			}

		} else {
			throw new CheckoutException("The book is not available");
		}
	}

	

	@Override
	public void checkInBook(String isbn, String memberId) {
		
	}
}

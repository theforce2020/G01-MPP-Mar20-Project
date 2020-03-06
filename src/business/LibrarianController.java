package business;


import dataaccess.DataAccessFacade;
import model.*;
import exceptions.*;

public class LibrarianController {

	DataAccessFacade df = new DataAccessFacade();
	
	public void checkoutBook(Book bk, LibraryMember lb) throws CheckoutException {
		BookCopy bkCopi = bk.getNextAvailableCopy();
		if(bkCopi != null) {
		CheckoutRecord cr = lb.getRecord();
		if (cr == null) {
				int copyNum = bkCopi.getCopyNum();
				CheckoutRecord newCR = new CheckoutRecord(bk,copyNum,lb);
				lb.setRecord(newCR);
				
			}else {
				cr.addCheckoutRecordEntry(bk);
			}
			
		}else {
			throw new CheckoutException("The book is not available");
		}
	}
	
}

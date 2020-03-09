package library.business;

import java.util.Date;

import library.dataaccess.DataAccessFacade;
import library.exceptions.CheckException;
import library.model.Book;
import library.model.BookCopy;
import library.model.CheckoutRecord;
import library.model.CheckoutRecordEntry;

public class LibrarianController implements CheckInterface {

	DataAccessFacade df = new DataAccessFacade();

	@Override
	public void checkOutBook(String isbn, String memberId) throws CheckException {
		if (!df.doesMemberExist(memberId))
			throw new CheckException("Sorry you are not a member. Let's Sign you in first!");

		if (!df.isBookAvailable(isbn))
			throw new CheckException("We dont have that book!");

		Book book = df.getBook(isbn);
		if (book.getAvailableNoOfCopies() == 0) 
			throw new CheckException("There are no available book copies");

		CheckoutRecord record = df.getCheckoutRecord(memberId);
		BookCopy copy = book.getAvailableCopy();

		if (copy != null) {
			if (record != null) {
				record.addCheckoutRecordEntry(book, copy.getCopyNum());
			} else {
				record = new CheckoutRecord(book, copy.getCopyNum(), memberId);
			}
			df.saveCheckoutRecord(record);
		} else {
			throw new CheckException("There are no available book copies");
		}
	}

	@Override
	public void checkInBook(String isbn, int copyNum, String memberId) throws CheckException {
		if (!df.doesMemberExist(memberId))
			throw new CheckException("Sorry you are not a member. Let's Sign you in first!");

		if (!df.isBookAvailable(isbn))
			throw new CheckException("We dont have that book!");
		
		CheckoutRecord record = df.getCheckoutRecord(memberId);
		
		if (record != null) {
			for (CheckoutRecordEntry entry : record.getEntries()) {
				if (entry.getBookCopy().getCopyNum() == copyNum) {
					entry.setReturnDate(new Date());
					entry.getBookCopy().changeAvailability();
				}
			}
			df.saveCheckoutRecord(record);
		} 
	}
}

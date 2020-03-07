package business;

import java.time.LocalDate;

import model.Book;
import model.LibraryMember;

public interface BookCheckoutInterface {
	
	public void addCheckoutRecord
	(String recordId, Book book, int copyNum, LocalDate checkoutDate, LocalDate dueDate, LibraryMember member);

}

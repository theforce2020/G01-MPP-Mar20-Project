package business;
import dataaccess.DataAccessFacade;

public class LibrarianController implements CheckInterface {
	
	DataAccessFacade df = new DataAccessFacade();

	@Override
	public void checkOutBook(String isbn, String memberId) {
		df.checkOutBook(isbn, memberId);
	}

	@Override
	public void checkInBook(String isbn, String memberId) {
		df.checkInBook(isbn, memberId);
	}
}
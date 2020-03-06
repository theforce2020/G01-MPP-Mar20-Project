package business;

import exceptions.CheckoutException;

public interface CheckInterface {
	
	public void checkOutBook(String isbn, String memberId) throws CheckoutException;
	
	public void checkInBook(String isbn, String memberId);
}
package business;

import exceptions.CheckoutException;
import model.*;

public interface CheckInterface {
	
	public void checkOutBook(String isbn, String memberId) throws CheckoutException;
	
	public void checkInBook(String isbn,int copyNum, String memberId) throws CheckoutException;
}
package library.business;

import exceptions.CheckException;

public interface CheckInterface {
	
	public void checkOutBook(String isbn, String memberId) throws CheckException;
	
	public void checkInBook(String isbn,int copyNum, String memberId) throws CheckException;
}
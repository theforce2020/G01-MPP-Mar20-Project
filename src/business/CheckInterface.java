package business;

public interface CheckInterface {
	
	public void checkOutBook(String isbn, String memberId);
	
	public void checkInBook(String isbn, String memberId);
}
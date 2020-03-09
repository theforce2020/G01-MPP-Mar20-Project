package library.business;

import library.exceptions.CheckException;
import library.model.CheckoutRecord;
import library.model.CheckoutRecordEntry;

public interface CheckInterface {

    void checkOutBook(String isbn, String memberId) throws CheckException;

    void checkInBook(String isbn, int copyNum, String memberId) throws CheckException;
    
    CheckoutRecord getCheckoutRecord(String memberId);
    
    CheckoutRecordEntry[] getCheckoutRecordEntries(String memberId);
}
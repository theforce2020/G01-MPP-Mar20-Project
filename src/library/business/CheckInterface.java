package library.business;

import library.exceptions.CheckException;

public interface CheckInterface {

    void checkOutBook(String isbn, String memberId) throws CheckException;

    void checkInBook(String isbn, int copyNum, String memberId) throws CheckException;
}
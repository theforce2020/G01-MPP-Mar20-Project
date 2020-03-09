package library.model;

import library.util.LibraryUtil;

import java.io.Serializable;
import java.util.Date;

public class CheckoutRecord implements Serializable {

    private static final long serialVersionUID = 4580065230999673218L;

    private CheckoutRecordEntry[] checkoutRecordEntries;

    private String memberId;

    public CheckoutRecord(Book book, int copyNum, String memberId) {
        setMemberId(memberId);
        Date checkoutDate = new Date();
        BookCopy cp = book.getCopy(copyNum);
        this.checkoutRecordEntries = new CheckoutRecordEntry[]{new CheckoutRecordEntry(cp, book.getIsbn(), checkoutDate, LibraryUtil.calculateDueDate(checkoutDate, book.getMaxCheckoutLength()), false)};
    }

    public void addCheckoutRecordEntry(Book book, int copyNum) {
        CheckoutRecordEntry[] newArr = new CheckoutRecordEntry[checkoutRecordEntries.length + 1];
        System.arraycopy(checkoutRecordEntries, 0, newArr, 0, checkoutRecordEntries.length);
        Date checkoutDate = new Date();
        BookCopy cp = book.getCopy(copyNum);
        newArr[checkoutRecordEntries.length] = new CheckoutRecordEntry(cp, book.getIsbn(), checkoutDate, LibraryUtil.calculateDueDate(checkoutDate, book.getMaxCheckoutLength()), false);
        checkoutRecordEntries = newArr;
    }

    public double totalFine() {
        double amount = 0;
        for (CheckoutRecordEntry ce : checkoutRecordEntries) {
            amount += ce.checkFines();
        }
        return amount;
    }

    public boolean hasBorrowedBook() {
        boolean result = false;
        for (CheckoutRecordEntry entry : checkoutRecordEntries) {
            if (!entry.isReturned()) {
                result = true;
                break;
            }
        }
        return result;
    }

    public CheckoutRecordEntry[] getEntries() {
        return this.checkoutRecordEntries;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
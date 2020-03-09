package library.model;

import library.util.LibraryUtil;

import java.io.Serializable;
import java.util.Date;

public class CheckoutRecord implements Serializable {

    private static final long serialVersionUID = 4580065230999673218L;

    private CheckoutRecordEntry[] checkoutRecordEntries;

    private LibraryMember member;

    public CheckoutRecord(Book book, int copyNum, LibraryMember libraryMember) {
        this.setMember(libraryMember);
        Date checkoutDate = new Date();
        BookCopy cp = book.getCopy(copyNum);
        this.checkoutRecordEntries = new CheckoutRecordEntry[]{new CheckoutRecordEntry(cp, book.getIsbn(), checkoutDate, LibraryUtil.calculateDueDate(checkoutDate, book.getMaxCheckoutLength()))};
    }

    public void addCheckoutRecordEntry(Book book, int copyNum) {
        CheckoutRecordEntry[] newArr = new CheckoutRecordEntry[checkoutRecordEntries.length + 1];
        System.arraycopy(checkoutRecordEntries, 0, newArr, 0, checkoutRecordEntries.length);
        Date checkoutDate = new Date();
        BookCopy cp = book.getCopy(copyNum);
        newArr[checkoutRecordEntries.length] = new CheckoutRecordEntry(cp, book.getIsbn(), checkoutDate, LibraryUtil.calculateDueDate(checkoutDate, book.getMaxCheckoutLength()));
        checkoutRecordEntries = newArr;
    }

    public LibraryMember getMember() {
        return member;
    }

    public void setMember(LibraryMember member) {
        this.member = member;
    }

    public double totalFine() {
        double amount = 0;
        for (CheckoutRecordEntry ce : checkoutRecordEntries) {
            amount += ce.checkFines();
        }
        return amount;
    }

    public CheckoutRecordEntry[] getEntries() {
        return this.checkoutRecordEntries;
    }
}
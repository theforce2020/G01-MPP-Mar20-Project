package library.model;

import java.io.Serializable;

final public class LibraryMember extends Person implements Serializable {

    private static final long serialVersionUID = -2226197306790714013L;

    private String memberId;

    private CheckoutRecord record;

    public LibraryMember(String fName, String lName, String telephone, Address address) {
        super(fName, lName, telephone, address);
    }

    public LibraryMember(String memberId, String fName, String lName, String telephone, Address address) {
        super(fName, lName, telephone, address);
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }

    @Override
    public String toString() {
        return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() +
                ", " + getTelephone() + " " + getAddress();
    }

    public CheckoutRecord getRecord() {
        return record;
    }

    public void setRecord(CheckoutRecord record) {
        this.record = record;
    }
}
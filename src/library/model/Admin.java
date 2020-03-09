package library.model;

import java.io.Serializable;

public class Admin extends Person implements Serializable {

    private static final long serialVersionUID = 4918518410164546946L;

    public Admin(String fName, String lName, String telephone, Address address) {
        super(fName, lName, telephone, address);
    }

    public Admin(int id, String fName, String lName, String telephone, Address address) {
        super(id, fName, lName, telephone, address);
    }
}
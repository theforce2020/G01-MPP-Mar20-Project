package model;

import java.io.Serializable;

public class Librarian extends Person implements Serializable {

	private String librarianId;
	
	private static final long serialVersionUID = -8117104280466495760L;

	public Librarian(String fName, String lName, String telephone, Address address) {
		super(fName,lName, telephone, address);
	}

	public String getLibrarianId() {
		return librarianId;
	}

	public void setLibrarianId(String librarianId) {
		this.librarianId = librarianId;
	}
}
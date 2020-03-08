package library.data.model;

import java.io.Serializable;

public class Librarian extends Person implements Serializable {

	private static final long serialVersionUID = -8117104280466495760L;
	
	public Librarian(String fName, String lName, String telephone, Address address) {
		super(fName,lName, telephone, address);
	}
	
	public Librarian(int id, String fName, String lName, String telephone, Address address) {
		super(id, fName,lName, telephone, address);
	}
}
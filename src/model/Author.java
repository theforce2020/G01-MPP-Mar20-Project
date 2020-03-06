package model;

import java.io.Serializable;

final public class Author extends Person implements Serializable {
	
	private static final long serialVersionUID = 7508481940058530471L;
	
	private String bio;
	
	public Author(String fName, String lName) {
		super(fName, lName);
	}
	
	public Author(String fName, String lName, String telephone, Address address, String bio) {
		super(fName, lName, telephone, address);
		this.bio = bio;
	}

	public String getBio() {
		return bio;
	}
}
package library.assistant.data.model;

import java.io.Serializable;

import dataaccess.Auth;

public class Librarian extends Person implements Serializable {

	private static final long serialVersionUID = -8117104280466495760L;

	private UserCredentials userCredentials; 
	
	public Librarian(String fName, String lName, String telephone, Address address, String username, String password, Auth authorization) {
		super(fName,lName, telephone, address);
		this.userCredentials = new UserCredentials(username, password, authorization);
	}

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}
}
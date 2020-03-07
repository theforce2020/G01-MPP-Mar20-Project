package model;

import java.io.Serializable;

import dataaccess.Auth;

public class Admin extends Person implements Serializable{

	private static final long serialVersionUID = 4918518410164546946L;
	
	private UserCredentials userCredentials; 

	public Admin(String fName, String lName, String telephone, Address address, String username, String password, Auth authorization) {
		super(fName, lName, telephone, address);
		this.userCredentials = new UserCredentials(username, password, authorization,this);
	}

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}
}
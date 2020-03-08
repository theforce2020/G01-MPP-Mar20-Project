package model;

import java.io.Serializable;

public class Person implements Serializable {
	
	private static final long serialVersionUID = 3665880920647848288L;
	
	private int id;
	
	private String firstName;
	
	private String lastName;
	
	private String telephone;
	
	private Address address;
	
	public Person(String fName, String lName) {
		this.firstName = fName;
		this.lastName = lName;
	}
	
	public Person(String fName, String lName, String telephone, Address address) {
		this.firstName = fName;
		this.lastName = lName;
		this.telephone = telephone;
		this.address = address;
	}
	
	public Person(int id, String fName, String lName, String telephone, Address address) {
		this.id = id;
		this.firstName = fName;
		this.lastName = lName;
		this.telephone = telephone;
		this.address = address;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
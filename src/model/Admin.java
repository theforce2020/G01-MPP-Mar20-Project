package model;

import java.io.Serializable;

public class Admin extends Person implements Serializable{

	private String adminId;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4918518410164546946L;

	public Admin(String fName, String lName, String telephone, Address address) {
		super(fName, lName, telephone, address);
		// TODO Auto-generated constructor stub
	}
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

}

package library.business;

import library.data.model.Admin;
import library.exceptions.UsernameInUseException;

import java.util.List;


public interface AdminInterface {
	
	public void saveAdmin(String fName, String lName, String telephone, String street, String city, String state, String zip, String username, String password) throws UsernameInUseException;
	
	public void updateAdmin(int adminId, String fName, String lName, String telephone, String street, String city, String state, String zip);
	
	public List<Admin> getAllAdmins();
}
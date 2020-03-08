package business;

import java.util.List;

import exceptions.UsernameInUseException;
import model.Admin;

public interface AdminInterface {
	
	public void saveAdmin(String fName, String lName, String telephone, String street, String city, String state, String zip, String username, String password) throws UsernameInUseException;
	
	public void updateAdmin(int adminId, String fName, String lName, String telephone, String street, String city, String state, String zip);
	
	public List<Admin> getAllAdmins();
}
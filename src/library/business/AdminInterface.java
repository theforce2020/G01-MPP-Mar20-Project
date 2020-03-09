package library.business;

import library.exceptions.UsernameInUseException;
import library.model.Admin;

import java.util.List;


public interface AdminInterface {

    void saveAdmin(String fName, String lName, String telephone, String street, String city, String state, String zip, String username, String password) throws UsernameInUseException;

    void updateAdmin(int adminId, String fName, String lName, String telephone, String street, String city, String state, String zip);

    List<Admin> getAllAdmins();
}
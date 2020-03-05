package business;

import java.util.List;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import exceptions.LoginException;
import model.Book;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	
}

package library.business;

import exceptions.LoginException;

public interface AuthenticationInterface {
	
	public void login(String username, String password) throws LoginException;	
}
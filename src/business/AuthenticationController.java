package business;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import exceptions.LoginException;
import model.SystemUser;

public class AuthenticationController implements AuthenticationInterface {
	public static Auth currentAuth = null;

	public void login(String username, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		SystemUser user = da.getSystemUser(username);
		if(user == null) {
			throw new LoginException("Username " + username + " not found");
		}
		String passwordFound = user.getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = user.getAuthorization();
	}
}
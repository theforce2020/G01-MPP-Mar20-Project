package library.assistant.business;

import java.util.ArrayList;
import java.util.List;

import dataaccess.Auth;
import library.assistant.dataaccess.DataAccess;
import library.assistant.dataaccess.DataAccessFacade;
import library.assistant.exceptions.LoginException;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	
 public void login(String id, String password) {
		/*DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.lo;
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();*/
		
	}
	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.loadMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.loadBookMap().keySet());
		return retval;
	}
	
	
}

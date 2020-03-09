package library.business;

import library.dataaccess.Auth;
import library.dataaccess.DataAccess;
import library.dataaccess.DataAccessFacade;

import java.util.ArrayList;
import java.util.List;

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

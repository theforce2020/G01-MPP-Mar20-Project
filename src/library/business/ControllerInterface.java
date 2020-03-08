package library.business;

import java.util.List;

import library.exceptions.LoginException;

public interface ControllerInterface {
	
	public void login(String id, String password) throws LoginException;
	
	public List<String> allMemberIds();
	
	public List<String> allBookIds();	
}
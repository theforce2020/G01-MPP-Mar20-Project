package library.assistant.business;

import java.util.List;

import library.assistant.exceptions.LoginException;

public interface ControllerInterface {
	
	public void login(String id, String password) throws LoginException;
	
	public List<String> allMemberIds();
	
	public List<String> allBookIds();	
}
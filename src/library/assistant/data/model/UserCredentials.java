package library.assistant.data.model;

import java.io.Serializable;

import dataaccess.Auth;

final public class UserCredentials implements Serializable {
	
	private static final long serialVersionUID = 5147265048973262104L;
	
	private String username;
	
	private String password;
	
	private Auth authorization;

	UserCredentials(String username, String password, Auth auth) {
		this.username = username;
		this.password = password;
		this.authorization = auth;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Auth getAuthorization() {
		return authorization;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "[" + authorization.toString() + "]";
	}
}
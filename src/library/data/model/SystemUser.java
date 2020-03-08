package library.data.model;

import java.io.Serializable;

import dataaccess.Auth;

final public class SystemUser implements Serializable {
	
	private static final long serialVersionUID = 5147265048973262104L;
	
	private int id;
	
	private String username;
	
	private String password;
	
	private Auth authorization;

	public SystemUser(String username, String password, Auth auth) {
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
	
	@Override
	public String toString() {
		return "[" + authorization.toString() + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
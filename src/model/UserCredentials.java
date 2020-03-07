package model;

import java.io.Serializable;

import dataaccess.Auth;

final public class UserCredentials implements Serializable {
	
	private static final long serialVersionUID = 5147265048973262104L;
	
	private int id;
	
	private String username;
	private String UserCredentialsname;
	private String password;
	private Auth authorization;
	Person me;

	UserCredentials(String UserCredentialsname, String password, Auth auth,Person prs) {
		this.UserCredentialsname = UserCredentialsname;
		this.password = password;
		this.authorization = auth;
		this.me = prs;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Auth getAuthorization() {
		return authorization;
	}

	public String getUserCredentialsname() {
		return UserCredentialsname;
	}

	public void setUserCredentialsname(String UserCredentialsname) {
		this.UserCredentialsname = UserCredentialsname;
	}
	
	public Person getMe() {
		return me;
	}
	
	public void setMe(Person pp) {
		this.me = pp;
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "[" + authorization.toString() + "]";
	}
	
	@Override
	public boolean equals(Object ob) {
		if(ob == null) return false;
		if(!(ob instanceof UserCredentials)) return false;
		UserCredentials UserCredentials = (UserCredentials)ob;
		return UserCredentials.getUsername().equals(UserCredentialsname) && UserCredentials.getPassword().equals(password) && UserCredentials.getId()==(id);
	}
	
	@Override
	public int hashCode() {
	    final int PRIME = 31;
	    int result = 1;
	    result = PRIME * result + this.id;
	    return result;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
package exceptions;

import java.io.Serializable;

public class UsernameInUseException extends Exception implements Serializable {

	public UsernameInUseException() {
		super();
	}
	public UsernameInUseException(String msg) {
		super(msg);
	}
	public UsernameInUseException(Throwable t) {
		super(t);
	}
	private static final long serialVersionUID = 8978723266036027364L;
	
}

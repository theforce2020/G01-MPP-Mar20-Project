package exceptions;

import java.io.Serializable;

public class CheckException extends Exception implements Serializable {

	public CheckException() {
		super();
	}
	
	public CheckException(String msg) {
		super(msg);
	}
	
	public CheckException(Throwable t) {
		super(t);
	}
	
	private static final long serialVersionUID = 8978723266036027364L;
	
}

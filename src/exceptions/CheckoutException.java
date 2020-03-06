package exceptions;

import java.io.Serializable;

public class CheckoutException extends Exception implements Serializable {

	public CheckoutException() {
		super();
	}
	
	public CheckoutException(String msg) {
		super(msg);
	}
	
	public CheckoutException(Throwable t) {
		super(t);
	}
	
	private static final long serialVersionUID = 8978723266036027364L;
	
}

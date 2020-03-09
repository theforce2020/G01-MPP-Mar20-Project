package library.exceptions;

import java.io.Serializable;

public class CheckException extends Exception implements Serializable {

    private static final long serialVersionUID = 8978723266036027364L;

    public CheckException() {
        super();
    }

    public CheckException(String msg) {
        super(msg);
    }

    public CheckException(Throwable t) {
        super(t);
    }
}
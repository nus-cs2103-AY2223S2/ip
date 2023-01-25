package dudu.exception;

import dudu.exception.DuduException;

public class EmptyDescriptionException extends DuduException {
    private String type;
    private String error;
    public EmptyDescriptionException(String type, String error, String msg) {
        super(msg);
        this.type = type;
        this.error = error;
    }
    @Override
    public String toString() {
        return "OOPS!!! The " + error + " of a " + type + " cannot be empty.";
    }
}

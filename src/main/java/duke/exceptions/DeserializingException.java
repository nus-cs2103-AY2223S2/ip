package duke.exceptions;

public class DeserializingException extends Exception {
    public DeserializingException(String message) {
        super(message);
    }

    public DeserializingException(String message, Throwable cause) {
        super(message, cause);
    }
}

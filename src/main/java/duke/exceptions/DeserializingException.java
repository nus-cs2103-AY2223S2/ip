package duke.exceptions;

/**
 * Exception thrown when there is an error deserializing the data.
 *
 * @author Samarth Verma
 */
public class DeserializingException extends Exception {
    public DeserializingException(String message) {
        super(message);
    }

    public DeserializingException(String message, Throwable cause) {
        super(message, cause);
    }
}

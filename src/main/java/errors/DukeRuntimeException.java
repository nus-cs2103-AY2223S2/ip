package errors;

/**
 * Exception class for indicating when a user provides an invalid Duke chatbot command.
 * @author Nicholas Lee
 */
public class DukeRuntimeException extends Exception{
    public DukeRuntimeException(String message) {
        super(message);
    }

}

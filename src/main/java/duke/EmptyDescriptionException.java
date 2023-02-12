package duke;

/**
 * This exception is thrown when there is an empty description
 */
public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String message) {
        super(message);
        assert message.length() > 0;
    }
}

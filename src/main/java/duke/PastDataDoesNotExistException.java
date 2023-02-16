package duke;

/**
 * Thrown when there is no file data/duke in the application, or no past data
 */
public class PastDataDoesNotExistException extends Exception {
    public PastDataDoesNotExistException(String message) {
        super(message);
    }
}

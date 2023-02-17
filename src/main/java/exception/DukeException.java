package exception;

/**
 * Represents a custom runtime exception that occurs during the runtime of Duke.
 * Expected to become an abstract class in the future.
 */
public class DukeException extends RuntimeException {
    /**
     * Constructs a DukeException object.
     *
     * @param errorMessage String to be the error message.
     * @return DukeException object.
     * */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

package Exception;

/**
 * Represents a custom runtime exception that occurs during the runtime of Duke.
 * Expected to become an abstract class in the future.
 */
public class dukeException extends RuntimeException {

    /**
     * Constructor for a dukeException object.
     *
     * @param errorMessage String to be the error message.
     * @return dukeException object.
     * */
    public dukeException(String errorMessage) {
        super(errorMessage);
    }
    
}
package sebastian.exceptions;


/**
 * Exception when a user input cannot be comprehended by sebastian.main.Sebastian
 */
public class IllegalInputException extends SebastianException {
    /**
     * Constructor
     */
    public IllegalInputException() {
        super("Apologies, I'm afraid I don't understand your instruction");
    }
}

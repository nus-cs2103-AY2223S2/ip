package Duke.Exceptions;

/**
 * The class represents the InvalidCommandException
 */
public class InvalidCommandException extends Exception {
    /**
     * The constructor for InvalidCommandException
     */
    public InvalidCommandException() {
        super(String.format("    OOPS!!! What is wrong with you? Put something valid here as command now!"));
    }
}

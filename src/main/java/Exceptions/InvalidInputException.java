package exceptions;

/**
 * This class is used to throw an exception when an unknown command is selected.
 */
public class InvalidInputException extends TaskGenieException {
    /**
     * Constructor for the InvalidInputException.
     * @param err The error.
     */
    public InvalidInputException(Throwable err) {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(", err);
    }
}

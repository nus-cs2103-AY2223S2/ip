package sebastian.exceptions;

/**
 * Exception that tasklist data cannot be loaded from the hard disk
 */
public class CannotLoadDataException extends SebastianException {
    /**
     * Constructor
     */
    public CannotLoadDataException() {
        super("I'll create a new task list to store your data");
    }
}

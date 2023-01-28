package sebastian.exceptions;

/**
 * Exception that tasklist data cannot be loaded from the hard disk
 */
public class CannotLoadDataException extends SebastianException {
    /**
     * Constructor
     */
    public CannotLoadDataException() {
        super("Sorry, I cannot retrieve your past tasks. I'll create a new task list for you");
    }
}

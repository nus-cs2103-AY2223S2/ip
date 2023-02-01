package duke.exceptions;

/**
 * InvalidCmdValueException represents an exception if
 * a delete, mark or unmark command specify a wrong index.
 */
public class InvalidCmdValueException extends Exception {

    /**
     * Create a InvalidCmdValueException
     */
    public InvalidCmdValueException() {
        super("Please input a correct value");
    }
}

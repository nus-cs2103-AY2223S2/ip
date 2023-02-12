package duke.exceptions;

/**
 * InvalidCommandValueException represents an exception if
 * a delete, mark or unmark command specify a wrong index.
 */
public class InvalidCommandValueException extends Exception {

    /**
     * Creates a InvalidCommandValueException
     */
    public InvalidCommandValueException() {
        super("Please input a correct value");
    }
}

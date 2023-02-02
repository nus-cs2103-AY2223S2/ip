package exceptions;

/**
 * Represents exceptions for commands with invalid inputs.
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructs an exception for commands with invalid inputs.
     *
     * @param description Description of the problem.
     */
    public InvalidCommandException(String description) {
        super("OOPS!!! " + description);
    }
}

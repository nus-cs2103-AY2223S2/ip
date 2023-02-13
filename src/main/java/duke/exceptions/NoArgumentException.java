package duke.exceptions;

/**
 * Represents a Duke-specific exception for when user does not input an argument
 * following a Duke command.
 */
public class NoArgumentException extends DukeException {

    /**
     * Constructor for a Duke NoArgumentException.
      */
    public NoArgumentException() {
        super("No arguments found.");
    }
}

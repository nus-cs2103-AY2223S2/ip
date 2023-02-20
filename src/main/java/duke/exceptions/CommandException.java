package duke.exceptions;

/**
 * The {@code CommandException} class represents an exception that
 * is thrown when an error occurs in the execution of a command.
 */
public class CommandException extends DukeException {
    /**
     * Instantiate a command exception with an error message to display to the user.
     * @param errorMessage The error message to display to the user.
     */
    public CommandException(String errorMessage) {
        super("Command error: " + errorMessage);
    }
}

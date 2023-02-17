package vic.exceptions;
/**
 * A DukeException to check if command is valid
 */
public class InvalidCommandException extends DukeException {
    /**
     * Constructor for InvalidCommandException
     *
     * @param errorMessage the invalid command
     */
    public InvalidCommandException(String errorMessage) {
        super("☹ OOPS!!! I'm sorry, but I don't know what '" + errorMessage + "' means :-(");
    }
}

package vic.exceptions;
/**
 * A DukeException to check if command is complete
 */
public class IncompleteCommandException extends DukeException {
    /**
     * Constructor for IncompleteCommandException
     *
     * @param errorMessage the incomplete command
     */
    public IncompleteCommandException(String errorMessage) {
        super("☹ OOPS!!! The " + errorMessage + " cannot be empty from your command");
    }
}


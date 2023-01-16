package duke.exception;

/**
 * InvalidCommandArgsException
 */
public class InvalidCommandArgsException extends DukeException {

    /**
     * Default constructor.
     * @param errorMessage
     */
    public InvalidCommandArgsException(String errorMessage) {
        super(errorMessage);
    }
    
}

package duke.exception;

/**
 * NoSuchCommandException
 */
public class NoSuchCommandException extends DukeException {

    /**
     * Default constructor.
     * @param errorMessage
     */
    public NoSuchCommandException(String errorMessage) {
        super(errorMessage);
    }
}

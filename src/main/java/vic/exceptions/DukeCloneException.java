package vic.exceptions;

/**
 * A DukeException to check if task can be cloned
 */
public class DukeCloneException extends DukeException {
    /**
     * Constructor for CloneException
     *
     * @param errorMessage the object to be cloned
     */
    public DukeCloneException(String errorMessage) {
        super("Apologies unable to clone object" + errorMessage + ".");
    }
}

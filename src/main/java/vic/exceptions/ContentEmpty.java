package vic.exceptions;

/**
 * A DukeException to check if content is empty
 */
public class ContentEmpty extends DukeException {
    /**
     * Constructor for ContentEmpty
     *
     * @param errorMessage the content is missing
     */
    public ContentEmpty(String errorMessage) {
        super("Try to type something in your " + errorMessage + ".");
    }
}

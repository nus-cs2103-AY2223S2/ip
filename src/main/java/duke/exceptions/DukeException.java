package duke.exceptions;
/**
 * Encapsulates errors that occur in the Duke chatbot.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new Duke Exception.
     * @param errorMessage the error message to display
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    };
}

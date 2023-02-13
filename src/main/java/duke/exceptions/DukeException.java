package duke.exceptions;
/**
 * Encapsulates specific errors that occur in the Duke chatbot.
 */
public class DukeException extends Exception {
    public DukeException() {};

    public DukeException(String errorMessage) {
        super(errorMessage);
    };
}

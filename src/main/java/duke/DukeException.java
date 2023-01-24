package duke;

/**
 * Exception Class for Duke and customised error messages.
 */
public class DukeException extends Exception {

    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

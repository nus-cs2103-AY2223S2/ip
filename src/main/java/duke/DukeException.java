package duke;

/**
 * The Exception class for handling exceptions only occurring in this program.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}

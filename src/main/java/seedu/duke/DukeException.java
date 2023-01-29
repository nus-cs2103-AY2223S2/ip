package seedu.duke;

/**
 * An exception resulting from invalid inputs
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

package duke;

/**
 * Exception class representing a Duke error.
 */
public class DukeException extends Exception {
    public DukeException(String errMsg) {
        super(errMsg);
    }
}

package duke;

/**
 * The DukeException class.
 * A DukeException is an exception that is thrown by Duke.
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super("\t☹ OOPS!!! " + message);
    }
}

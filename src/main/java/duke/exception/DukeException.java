package duke.exception;

/**
 * Provides Duke class an Exception.
 */
public class DukeException extends Exception {
    public DukeException(String errMsg) {
        super(String.format("OOPS!!! %s\n", errMsg));
    }
}

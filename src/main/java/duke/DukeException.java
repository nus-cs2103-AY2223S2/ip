package duke;

/**
 * Represents errors thrown when user input
 * does not fit the intended use case.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

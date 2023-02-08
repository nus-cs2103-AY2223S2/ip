package duke.exceptions;

/**
 * An exception superclass for exceptions encountered in Duke.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}


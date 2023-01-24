package seedu.duke.Exceptions;

public class DukeException extends Exception {

    /**
     * Thrown when an exception occurs.
     * Returns an error message for Duke.
     *
     * @param e String of error message to be returned
     * @return  error message
     */
    public DukeException(String e) {
        super(e);
    }
}

package seedu.duke;

/**
 * DukeException to catch exceptions from DukeBot.
 */
public class DukeException extends Exception {

    /**
     * Thrown when an exception occurs.
     * Returns an error message for Duke.
     *
     * @param e String of error message to be returned.
     */

    public DukeException(String e) {
        super(e);
    }
}

package duke;

/**
 * DukeException exception which is an extension of Exception
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super("Sorry, Duke has run into an Error: " + errorMessage);
    }
}

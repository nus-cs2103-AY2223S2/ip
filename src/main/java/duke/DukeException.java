package duke;

/**
 * Handles any undesirable inputs by the user and displays an error message
 */
public class DukeException extends Exception {
    public DukeException(String message) {

        super(message);
    }
}

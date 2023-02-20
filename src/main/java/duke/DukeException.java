package duke;

/**
 * Class representing a DukeException, i.e, exceptions thrown by Duke
 */
public class DukeException extends Exception {
    /**
     * Parameterized constructor to create a DukeException
     * @param message the message to describe the DukeException
     */
    public DukeException(String message) {
        super(message);
    }
}

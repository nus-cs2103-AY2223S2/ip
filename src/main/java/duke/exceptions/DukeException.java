package duke.exceptions;

/**
 * A Duke-related exception.
 * Any Duke-related exceptions should inherit from this class.
 */
public abstract class DukeException extends Exception {
    /** The error-message-prefix for Duke to display. */
    protected static final String DUKE_MESSAGE_PREFIX = ":( OOPS!!! ";

    public DukeException() {
        super();
    }

    public DukeException(String message) {
        super(message);
    }

    /**
     * Gets the formatted error message to be displayed in Duke.
     *
     * @return Formatted error message.
     */
    public String getDukeMessage() {
        return DukeException.DUKE_MESSAGE_PREFIX + this.getMessage();
    }
}

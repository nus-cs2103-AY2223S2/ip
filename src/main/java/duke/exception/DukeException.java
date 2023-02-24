package duke.exception;

/**
 * Custom Exceptions specific to the <code>Duke</code>> class.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class DukeException extends Exception {
    /**
     * In Duke Exceptions, we are not interested in the specific name of the
     * exception, just the message.
     */
    protected String message;

    /**
     * Constructor for duke.exception.DukeException Exception.
     *
     * @param errorMessage String describing the error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        this.message = errorMessage;
    }
    /**
     * Returns the string representation of a <code>DukeException</code>>.
     * @return The string representation of a <code>DukeException</code>>.
     */
    public String getMessage() {
        return this.toString();
    }
}

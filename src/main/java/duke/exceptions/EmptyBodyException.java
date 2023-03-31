package duke.exceptions;

/**
 * Exception to be thrown when parts of a command are missing.
 */
public class EmptyBodyException extends DukeException {
    /**
     * Constructor for an EmptyBodyException.
     */
    public EmptyBodyException() {
        super("What do you wanna do yo~");
    }
}

package duke.exceptions;

/**
 * Exception to be thrown when command cannot be parsed
 */
public class UnknownCommandException extends DukeException {
    /**
     * Constructor for an UnknownCommandException.
     */
    public UnknownCommandException() {
        super("WAKANDEYO!!! >:(");
    }
}

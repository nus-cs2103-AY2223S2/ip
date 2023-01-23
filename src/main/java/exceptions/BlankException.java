package exceptions;

/**
 * Exception class handles if description is empty
 */
public class BlankException extends DukeException {
    public BlankException() {
        super("OOPS!!! This command needs a description. See instruction for more details");
    }
}

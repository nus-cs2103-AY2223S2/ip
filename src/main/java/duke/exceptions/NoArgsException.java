package duke.exceptions;

/**
 * An error for when no arguments are supplied to commands which require them.
 */
public class NoArgsException extends DukeException {
    public NoArgsException(String str) {
        super("☹ OOPS!!! The description of an " + str + " cannot be empty.");
    }
}

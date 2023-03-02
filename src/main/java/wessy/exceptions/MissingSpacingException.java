package wessy.exceptions;

/**
 * MissingSpacingException is an exception that should be thrown when the user
 * does not enter a space after the commands he inputs, for commands that
 * require some arguments (todo, event, deadline, mark, unmark).
 */
public class MissingSpacingException extends WessyException {
    /**
     * Constructs an instance of MissingSpacingException.
     *
     * @param cmd The command requested by the user, in its String form.
     */
    public MissingSpacingException(String cmd) {
        super(String.format("The spacing after '%s' is missing.", cmd));
    }
}

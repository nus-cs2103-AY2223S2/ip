package clippy.exception;

/**
 * A complaint that the command entered was invalid.
 *
 * @author chunzkok
 */
public class ClippyUnknownCommandException extends ClippyException {
    /**
     * Creates a ClippyUnknownCommandException that contains a message
     * informing the user that the command is invalid.
     */
    public ClippyUnknownCommandException() {
        super("the command cannot be recognised.");
    }
}

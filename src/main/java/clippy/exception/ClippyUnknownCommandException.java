package clippy.exception;

/**
 * A complaint that the command entered was invalid.
 *
 * @author chunzkok
 */
public class ClippyUnknownCommandException extends ClippyException {
    public ClippyUnknownCommandException() {
        super("the command cannot be recognised.");
    }
}

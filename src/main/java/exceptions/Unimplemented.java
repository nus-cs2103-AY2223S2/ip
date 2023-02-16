package exceptions;

/**
 * Thrown if unimplemented.
 * Usually as a placeholder until actual function is available.
 */
public class Unimplemented extends DukeException {
    /**
     * This method's constructor passes forward the message to Exception.
     */
    public Unimplemented() {
        super("Apologise the command you just typed has not available yet.");
    }
}

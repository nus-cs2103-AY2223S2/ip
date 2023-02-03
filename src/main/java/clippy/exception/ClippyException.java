package clippy.exception;

/**
 * An Exception signifying the complaints of Clippy.
 *
 * @author chunzkok
 */
public class ClippyException extends Exception {
    private String msg;

    /**
     * Creates a ClippyException instance.
     *
     * @param msg The message to be contained in this ClippyException.
     */
    public ClippyException(String msg) {
        super(msg);
        this.msg = msg;
    }

    /**
     * Returns a String that voices out Clippy's complaints in its own voice.
     *
     * @return Clippy's complaint.
     */
    @Override
    public String toString() {
        return ">>> Hold up! Clippy is confused - " + this.msg;
    }
}

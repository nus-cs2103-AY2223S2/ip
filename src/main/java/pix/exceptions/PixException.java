package pix.exceptions;

/**
 * PixException class to raise exceptions specific to pix.
 */
public class PixException extends Exception {
    /**
     * Raises a duke exception.
     *
     * @param error Error message to be displayed.
     */
    public PixException(String error) {
        super(error);
    }
}

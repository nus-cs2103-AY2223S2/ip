package exception;

/**
 * Thrown when an argument could not be parsed as a valid Miki command.
 */
public class MikiArgsException extends Exception {
    /**
     * Constructs a <code>MikiArgsException</code> with the specified detail message.
     *
     * @param message the detail message.
     */
    public MikiArgsException(String message) {
        super(message);
    }
}

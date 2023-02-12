package alfred.exception;

/**
 * Exception class.
 */
public class AlfredException extends Exception {
    /**
     * Creates an instance of AlfredException.
     * @param errorMessage
     */
    public AlfredException(String errorMessage) {
        super("☹ OOPS!!! " + errorMessage);
    }
}

package exceptions;

/**
 * Represents the InvalidCommandException that is thrown whenever a command called is invalid or unsupported by Duke
 * and extends from the Exception class.
 *
 * @author MrTwit99
 * @since 2023-02-02
 */
public class InvalidCommandException extends Exception {

    /**
     * Returns an InvalidCommandException object whenever a command called is invalid or unsupported by Duke
     *
     * @param str String message that is to be printed with the error.
     */
    public InvalidCommandException(String str) {
        super(str);
    }
}

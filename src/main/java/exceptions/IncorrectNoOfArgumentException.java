package exceptions;

/**
 * Represents the IncorrectNoOfArgumentException that is thrown whenever a command is supplied with insufficient
 * amount of arguments and extends from the Exception class.
 *
 * @author MrTwit99
 * @since 2023-02-02
 */
public class IncorrectNoOfArgumentException extends Exception {
    /**
     * Returns an IncorrectNoOfArgumentException object whenever a command is supplied with insufficient amount of
     * arguments
     *
     * @param str String message that is to be printed with the error.
     */
    public IncorrectNoOfArgumentException(String str) {
        super(str);
    }
}

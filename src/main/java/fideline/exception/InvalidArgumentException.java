package fideline.exception;

/**
 * Informs the user that the arguments provided are invalid for
 * the given command. Exception message should also contain what
 * the valid argument should be in the form of.
 *
 * @author Fun Leon
 */

public class InvalidArgumentException extends FidelineException {

    /**
     * Constructs an exception informing the user that their arguments
     * are invalid and provides the valid type of argument.
     *
     * @param validInput Proper type of input that is accepted.
     */
    public InvalidArgumentException(String validInput) {
        super("argument given has to be a valid " + validInput + "!");
    }
}

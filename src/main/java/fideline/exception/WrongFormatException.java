package fideline.exception;

/**
 * Informs the user that the command was not executed
 * because it was given in the wrong format.
 *
 * @author Fun Leon
 */
public class WrongFormatException extends FidelineException {

    /**
     * Constructs exception that informs the user of their command's
     * wrong format and provides the correct format.
     *
     * @param correctFormat The correct format of the given argument.
     */
    public WrongFormatException(String correctFormat) {
        super("wrong format! command should follow the format:\n\n" + correctFormat);
    }
}

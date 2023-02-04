package sebastian.exceptions;


/**
 * Exception when the user did not specify a body for their task declaration/instruction
 */
public class LackOfArgumentException extends InputFormatMismatchException {
    /**
     * Constructor
     */
    public LackOfArgumentException(String msg) {
        super(msg);
    }
}

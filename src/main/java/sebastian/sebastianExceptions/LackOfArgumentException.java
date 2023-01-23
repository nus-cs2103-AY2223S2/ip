package sebastian.sebastianExceptions;


/**
 * Exception when the user did not specify a body for their task declaration/instruction
 */
public class LackOfArgumentException extends InputFormatMismatchException{
    public LackOfArgumentException() {
        super("Please specify an argument");
    }
}

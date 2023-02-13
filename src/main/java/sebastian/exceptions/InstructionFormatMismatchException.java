package sebastian.exceptions;

/**
 * Exception when an instruction is given in the wrong format
 */
public class InstructionFormatMismatchException extends InputFormatMismatchException {
    /**
     * Constructor
     */
    public InstructionFormatMismatchException(String instruction) {
        super("Please give an instruction in the format of: " + "\n" + instruction + " [task index]");
    }
}

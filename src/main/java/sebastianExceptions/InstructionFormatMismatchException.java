package sebastianExceptions;

import formatters.Formatter;

/**
 * Exception when an instruction is given in the wrong format
 */
public class InstructionFormatMismatchException extends InputFormatMismatchException{
    public InstructionFormatMismatchException(String instruction) {
        super(
                "Please give an instruction in the format of: " + "\n" +
                        Formatter.space() + instruction + " [task index]"
                );
    }
}

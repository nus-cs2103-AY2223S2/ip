package SebastianExceptions;

import Utilities.Utilities;

public class InstructionFormatException extends InputFormatMismatchException{
    public InstructionFormatException(String instruction) {
        super(
                "Please give an instruction in the format of: " + "\n" +
                        Utilities.space() + instruction + " [task index]"
                );
    }
}

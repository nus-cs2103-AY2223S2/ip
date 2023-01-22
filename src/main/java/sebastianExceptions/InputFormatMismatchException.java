package sebastianExceptions;

/**
 * Exception when a user input does not follow the format for that specific type of instruction/task declaration
 * This class is meant to be the superclass of the specific exceptions for each type of instruction/task declaration
 */
abstract public class InputFormatMismatchException extends SebastianException{
    public InputFormatMismatchException(String message) {
        super(message);
    }
}

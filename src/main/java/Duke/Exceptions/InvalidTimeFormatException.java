package Duke.Exceptions;

/**
 * The class represents the InvalidTimeFormatException
 */
public class InvalidTimeFormatException extends Exception {
    /**
     * The constructor for InvalidTimeFormatException
     */
    public InvalidTimeFormatException() {
        super(String.format("OOPS!!! The input format is wrong! Please enter in the form of <event> by <time>. "));
    }
}

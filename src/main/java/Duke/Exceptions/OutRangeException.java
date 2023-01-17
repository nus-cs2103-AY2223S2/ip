package Duke.Exceptions;

/**
 * The class represents the OutRangeException
 */
public class OutRangeException extends Exception {
    /**
     * The constructor for OutRangeException
     */
    public OutRangeException() {
        super(String.format("OOPS!!! The number you entered is out of range!"));
    }

}

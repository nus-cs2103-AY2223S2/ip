package duke.Exceptions;

/**
 * The class represents the OutRangeException
 */
public class OutRangeException extends Exception {
    /**
     * The constructor for OutRangeException
     */
    public OutRangeException() {
        super(String.format("    OOPS!!! The number you entered is out of range!"));
    }

    @Override
    public String getMessage() {
        return "    OOPS!!! The number you entered is out of range!";
    }

}

package wessy.exceptions;

/**
 * WessyException is an abstract base class that serves as the parent for all the
 * exceptions that might occur when the programme, "Wessy" is running.
 */
public abstract class WessyException extends Exception {
    private static final String OPENING = "☹ OOPS!!! ";
    private final String message;

    /**
     * Serves as a constructor for all the child classes to initialise. The
     * error message is specified in str
     *
     * @param str The text where the child class input, to specify the error
     *            message.
     */
    protected WessyException(String str) {
        this.message = OPENING + str;
    }

    /** Returns the String representation of the child exceptions, unless
     * otherwise overridden.
     *
     * @return The string representation of the child exceptions.
     */
    @Override
    public String toString() {
        return message;
    }
}

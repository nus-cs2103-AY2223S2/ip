package chattime.exception;

/**
 * Represents customized exceptions to be thrown due to an invalid input.
 */
public class ChattimeException extends Exception {

    private String errMsg;

    /**
     * Creates ChattimeException object with specific error message.
     *
     * @param message The error message to be displayed to user.
     */
    public ChattimeException(String message) {
        super(message);
        errMsg = message;
    }

    /**
     * Returns the customized error message to be displayed.
     *
     * @return The error message.
     */
    public String getMessage() {
        return toString();
    }

    /**
     * Returns the customized error message to be displayed.
     *
     * @return The error message.
     */
    @Override
    public String toString() {
        return "OOPS!!! " + errMsg;
    }

}

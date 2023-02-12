package wessy.exceptions;

/**
 * UnspecifiedTimeException is an exception that should be thrown when some
 * arguments for "deadline" or "event" commands are missing, such as the task
 * description, the specified time after "/by", "/from" and "/to".
 */
public class UnspecifiedTimeException extends WessyException {
    /**
     * Constructs an instance of UnspecifiedTimeException
     *
     * @param keyword The keyword where the user has missed out the argument
     *                before or after it.
     * @param isBefore A boolean value to indicate the argument is missing
     *                 before or after the said keyword.
     */
    public UnspecifiedTimeException(String keyword, boolean isBefore) {
        super("The input " + (isBefore ? "before '" : "after '") + keyword +
                "' is missing.");
    }
}

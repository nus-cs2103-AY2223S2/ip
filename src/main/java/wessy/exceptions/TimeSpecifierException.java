package wessy.exceptions;

/**
 * TimeSpecifierException is an exception that summarises all other wrong ways
 * of inputting and using "/by", "/from" and "/to" keywords, that are not
 * covered by other exceptions.
 */
public class TimeSpecifierException extends WessyException {
    /**
     * Constructs an instance of TimeSpecifierException.
     *
     * @param keyword The keyword the user uses wrongly. It is either "/by",
     *                  "from" or "to".
     */
    public TimeSpecifierException(String keyword) {
        super("Either '" + keyword + "' is missing, or it is used in the " +
                "wrong format.");
    }
}

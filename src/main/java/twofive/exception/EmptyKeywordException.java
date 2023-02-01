package twofive.exception;

/**
 * Represents an error encountered when a keyword is not provided in a find command.
 */
public class EmptyKeywordException extends TwoFiveException {
    @Override
    public String getMessage() {
        return ":( OOPS!!! The keyword cannot be empty.";
    }
}

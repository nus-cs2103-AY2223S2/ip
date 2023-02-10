package twofive.exception;

/**
 * Represents an error encountered when a tag is not provided in a tag or taglist command.
 */
public class EmptyTagException extends TwoFiveException {
    @Override
    public String getMessage() {
        return ":( OOPS!!! The tag cannot be empty.";
    }
}

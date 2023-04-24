package twofive.exception;

/**
 * Represents an error encountered when a tag is not provided in a tag or taglist command.
 */
public class EmptyTagException extends TwoFiveException {
    @Override
    public String getMessage() {
        return "Oops! TwoFive would like to remind you that the tag cannot be empty.";
    }
}

package twofive.exception;

/**
 * Represents an error encountered when an invalid command is provided for parsing.
 */
public class InvalidCommandException extends TwoFiveException {
    @Override
    public String getMessage() {
        return ":( OOPS!!! I'm sorry, but I don't know what that means.";
    }
}

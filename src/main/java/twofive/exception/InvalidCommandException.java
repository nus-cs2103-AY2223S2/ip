package twofive.exception;

/**
 * Represents an error encountered when an invalid command is provided for parsing.
 */
public class InvalidCommandException extends TwoFiveException {
    @Override
    public String getMessage() {
        return "Oops! TwoFive doesn't know how to do that yet.";
    }
}

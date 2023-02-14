package twofive.exception;

/**
 * Represents an error encountered when one or more arguments are missing
 * in the command typed by the user.
 */
public class MissingArgumentException extends TwoFiveException {
    private String argumentType;

    public MissingArgumentException(String argumentType) {
        this.argumentType = argumentType;
    }

    @Override
    public String getMessage() {
        return "Oops! TwoFive believes that you are missing the argument " + argumentType + ".";
    }
}

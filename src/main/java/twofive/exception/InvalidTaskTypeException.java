package twofive.exception;

/**
 * Represents an error encountered when an invalid task type is specified
 * in the file responsible for saving tasks locally.
 */
public class InvalidTaskTypeException extends TwoFiveException {
    @Override
    public String getMessage() {
        return "Oops! TwoFive thinks you have given an invalid task type.";
    }
}

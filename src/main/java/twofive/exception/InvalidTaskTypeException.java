package twofive.exception;

public class InvalidTaskTypeException extends TwoFiveException {
    @Override
    public String getMessage() {
        return ":( OOPS!!! This task type is invalid.";
    }
}

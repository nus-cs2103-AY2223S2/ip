package twofive.exception;

public class InvalidTaskException extends TwoFiveException {
    @Override
    public String getMessage() {
        return ":( OOPS!!! A task with the given number cannot be found.";
    }
}

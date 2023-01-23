package twofive.exception;

public class InvalidCommandException extends TwoFiveException {
    @Override
    public String getMessage() {
        return ":( OOPS!!! I'm sorry, but I don't know what that means.";
    }
}

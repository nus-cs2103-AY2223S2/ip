package aqua.exception;

public class IllegalSyntaxException extends Exception {
    public IllegalSyntaxException() {}

    public IllegalSyntaxException(String description) {
        super(description);
    }

    public IllegalSyntaxException(String description, Throwable cause) {
        super(description, cause);
    }

    public IllegalSyntaxException(Throwable cause) {
        super(cause);
    }
}

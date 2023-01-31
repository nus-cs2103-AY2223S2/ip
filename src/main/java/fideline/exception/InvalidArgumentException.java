package fideline.exception;

public class InvalidArgumentException extends FidelineException {
    public InvalidArgumentException(String invalidInput) {
        super("argument given has to be a valid " + invalidInput + "!");
    }
}

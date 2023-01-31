package fideline.exception;

public class WrongFormatException extends FidelineException {
    public WrongFormatException(String errorMessage) {
        super("wrong format! " + errorMessage);
    }
}

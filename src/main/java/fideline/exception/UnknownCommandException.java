package fideline.exception;

public class UnknownCommandException extends FidelineException {

    public UnknownCommandException() {
        super("this command does not exist!");
    }
}

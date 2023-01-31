package fideline.exception;

public class EmptyParamException extends FidelineException {

    public EmptyParamException() {
        super("this command cannot have an empty description!");
    }
}

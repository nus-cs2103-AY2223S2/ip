package duke.exception;

public class UnrecognizedInputException extends InvalidInputException{
    public UnrecognizedInputException(String errorMessage) {
        super(errorMessage);
    }
}

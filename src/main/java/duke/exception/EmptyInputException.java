package duke.exception;

public class EmptyInputException extends InvalidInputException{
    public EmptyInputException(String errorMessage) {
        super(errorMessage);
    }
}

package exceptions;

public class IncorrectArgumentException extends CluckException{
    public IncorrectArgumentException(String errorMessage) {
        super(errorMessage);
    }
}

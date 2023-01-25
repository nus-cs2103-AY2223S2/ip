package exception;

public class InvalidDateTimeFormatException extends TaskFactoryException {

    public InvalidDateTimeFormatException(String errorMessage) {
        super(errorMessage);
    }
}

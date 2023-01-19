package exception;

public class MissingTaskDescriptionException extends TaskFactoryException {

    public MissingTaskDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}

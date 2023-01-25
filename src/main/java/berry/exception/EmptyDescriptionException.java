package berry.exception;

public class EmptyDescriptionException extends BerryException {
    public EmptyDescriptionException(String taskType) {
        super("You cannot leave your " + taskType + " description blank!");
    }
}

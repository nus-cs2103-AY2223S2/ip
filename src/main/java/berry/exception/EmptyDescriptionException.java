package berry.exception;

/**
 * Signals that some given data in the description input is empty.
 */
public class EmptyDescriptionException extends BerryException {

    public EmptyDescriptionException(String taskType) {
        super("You cannot leave your " + taskType + " description blank!");
    }
}

package duke.exceptions;

/**
 * The {@code ModelException} class represents an exception that is thrown
 * when an error occurs in the task model.
 */
public class ModelException extends DukeException {
    public ModelException(String errorMessage) {
        super("Model error" + errorMessage);
    }
}

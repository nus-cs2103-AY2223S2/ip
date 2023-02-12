package duke.exceptions;

public class ModelException extends DukeException {
    public ModelException(String errorMessage) {
        super("Model error" + errorMessage);
    }
}

package duke.exceptions;

public class EmptyDescriptionException extends  Exception {

    public EmptyDescriptionException() {
        super("May I please have a task description?");
    }

    public EmptyDescriptionException(String message) {
        super(message);
    }
}

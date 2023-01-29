package duke.exceptions;

public class EmptyDescriptionException extends  Exception {

    public EmptyDescriptionException() {
        super("bro where's the duke.task description?");
    }

    public EmptyDescriptionException(String message) {
        super(message);
    }
}

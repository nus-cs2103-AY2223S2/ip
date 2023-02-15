package duke.exceptions;

public class EmptyDescriptionException extends NeroException {
    public EmptyDescriptionException() {
        super("Description of a task cannot be empty!!");
    }
}

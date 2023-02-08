package exceptions;

public class EmptyContentException extends DukeException {
    public EmptyContentException(String taskType) {
        super(String.format("OOPS!!! The description of a " + taskType + " cannot be empty."));
    }
}

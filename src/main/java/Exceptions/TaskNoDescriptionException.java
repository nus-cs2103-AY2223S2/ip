package Exceptions;

public class TaskNoDescriptionException extends DukeException {
    public TaskNoDescriptionException(String errorMessage) {
        super(errorMessage);
    }
}

package duke.exception;

public class InvalidTodoException extends GeneralDukeException{
    public InvalidTodoException(String errorMessage) {
        super(errorMessage);
    }
}

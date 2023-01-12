package duke.exception;

public class InvalidDeleteException extends GeneralDukeException{
    public InvalidDeleteException(String errorMessage) {
        super(errorMessage);
    }
}

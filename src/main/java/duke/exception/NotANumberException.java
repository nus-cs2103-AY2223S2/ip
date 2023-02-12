package duke.exception;

public class NotANumberException extends DukeException{
    public NotANumberException(String taskType) {
        super(String.format("%s commands need to be followed by an integer!\n", taskType));
    }
}

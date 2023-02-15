package duke.exception;

/**
 * NotANumberException class handles the case when the input is not a number even though it should be
 */
public class NotANumberException extends DukeException {
    public NotANumberException(String taskType) {
        super(String.format("%s commands need to be followed by an integer!\n", taskType));
    }
}

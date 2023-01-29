package duke.exception;

/**
 * An exception when a valid user command does not
 * contain a description.
 */
public class EmptyDescException extends DukeException {
    String taskType;
    public EmptyDescException(String message) {
        super(message);
    }
}

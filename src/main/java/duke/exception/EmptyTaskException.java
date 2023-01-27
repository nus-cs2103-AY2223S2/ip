package duke.exception;

/**
 * EmptyTaskException class handles empty task descriptions
 */
public class EmptyTaskException extends DukeException {
    public EmptyTaskException(String taskType) {
        super(String.format("The description of a %s task cannot be empty.", taskType));
    }
}

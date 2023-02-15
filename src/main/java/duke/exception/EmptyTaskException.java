package duke.exception;

/**
 * EmptyTaskException class handles empty task descriptions
 */
public class EmptyTaskException extends DukeException {
    public EmptyTaskException(String commandType) {
        super(String.format("The description of a %s command cannot be empty.", commandType));
    }
}

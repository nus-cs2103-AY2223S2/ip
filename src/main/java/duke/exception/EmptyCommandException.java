package duke.exception;

/**
 * EmptyTaskException class handles empty task descriptions
 */
public class EmptyCommandException extends DukeException {
    public EmptyCommandException(String commandType) {
        super(String.format("The description of a %s command cannot be empty.", commandType));
    }
}

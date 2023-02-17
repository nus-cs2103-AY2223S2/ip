package duke.exception;

/**
 * EmptyDescException class handles empty task or command descriptions
 */
public class MissingInputsException extends DukeException {
    public MissingInputsException(String commandType) {
        super(String.format("The %s command has missing inputs.", commandType));
    }
}

package duke.exception;

/**
 * Exception for missing arguments
 */
public class DukeInvalidArgumentsException extends DukeException {
    @Override
    public String toString() {
        return String.format("%s You have invalid argument(s) for this command!", super.toString());
    }
}

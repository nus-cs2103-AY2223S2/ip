package duke.exception;

/**
 * Represents Missing content exception
 */
public class MissingContentException extends DukeException {
    public MissingContentException() {
        super("WOOF! The content/detail cannot be empty!");
    }
}
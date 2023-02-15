package duke.exception;

/**
 * Represents Missing content exception
 */
public class MissingContentException extends DukeException {
    public MissingContentException() {
        super("WOOF! BOSS! Kyle cannot proceed :( The content/detail cannot be empty!");
    }
}

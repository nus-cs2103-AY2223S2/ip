package duke.exception;

/**
 * Represents Missing content exception
 */
public class MissingContentException extends DukeException {
    public MissingContentException() {
        super("WOOF! BOSS! Ask the Minions! Kyle cannot proceed :( The content/detail cannot be empty!");
    }
}

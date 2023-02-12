package dukeexceptions;

/**
 * Encapsulates an Exception when the description of the task is empty.
 */
public class EmptyDescriptionException extends DukeException {
    public EmptyDescriptionException() {
        super("☹ OOPS!!! The description of your task cannot be empty.");
    }
}

package duke.dukeExceptions;

public class MarkException extends DukeException {
    /**
     * Exception used by marking function to indicate that user input is invalid
     */

    public MarkException() {
        super("I don't know which task to mark...");
    }
}

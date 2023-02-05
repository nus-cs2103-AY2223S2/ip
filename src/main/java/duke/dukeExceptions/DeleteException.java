package duke.dukeExceptions;

public class DeleteException extends DukeException {
    /**
     * Exception used by delete function to indicate invalid user input
     */
    public DeleteException() {
        super("I don't know which task to delete...");
    }
}

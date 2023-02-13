package duke.dukeexceptions;

public class DeleteException extends DukeException {
    public DeleteException() {
        super("I don't know which task to delete...");
    }
}

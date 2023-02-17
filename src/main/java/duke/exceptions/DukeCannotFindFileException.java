package duke.exceptions;

public class DukeCannotFindFileException extends DukeException {
    public DukeCannotFindFileException() {
        super("I cannot seem to find the saved file, I'll create one whenever you update your list!");
    }
}

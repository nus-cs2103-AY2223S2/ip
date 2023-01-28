package duke.exceptions;

public class LoadException extends DukeException {
    public LoadException() {
        super("☹ OOPS!!! File corrupted! Resetting data...");
    }
}

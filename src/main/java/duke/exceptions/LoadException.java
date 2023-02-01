package duke.exceptions;

/**
 * An error for when a task list cannot be loaded from the file.
 * This may mean that the file is corrupted.
 */
public class LoadException extends DukeException {
    public LoadException() {
        super("OOPS!!! File corrupted! Resetting data...");
    }
}

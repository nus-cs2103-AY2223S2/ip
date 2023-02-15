package duke.exceptions;

/**
 * A class that represents errors created from saving operations while Duke is shutting down.
 */
public class SaveDukeException extends DukeException {
    public SaveDukeException() {
        super("Failed to save your tasks!\nYou may still exit the program but no data will be saved!");
    }
}

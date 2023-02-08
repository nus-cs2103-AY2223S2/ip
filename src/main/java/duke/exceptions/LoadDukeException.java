package duke.exceptions;

/**
 * A class that represents errors created from loading operations while Duke is starting up.
 */
public class LoadDukeException extends DukeException {
    public LoadDukeException() {
        super("Failed to load your tasks!");
    }
}

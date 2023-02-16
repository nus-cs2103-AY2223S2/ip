package berry.exception;

/**
 * Signals that there are no tasks available to list.
 */
public class NoTasksException extends BerryException {

    public NoTasksException() {
        super("There's no tasks at the moment!\nGo take a break, take a kitkat <:");
    }
}

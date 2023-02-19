package berry.exception;

/**
 * Signals that the given task already exists.
 */
public class ExistingTaskException extends BerryException {

    public ExistingTaskException(String command) {
        super("This task already exists!\nYou can use the 'find <keywords>' command to find it.");
    }
}

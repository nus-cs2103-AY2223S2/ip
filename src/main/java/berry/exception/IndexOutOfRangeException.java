package berry.exception;

/**
 * Signals that the given index is not present in the task list.
 */
public class IndexOutOfRangeException extends BerryException {

    /**
     * {@inheritDoc}
     */
    public IndexOutOfRangeException() {
        super("Oh no! \nI cannot find a task with that task number.\n"
                + "You can check them again by asking me to 'list'");
    }
}

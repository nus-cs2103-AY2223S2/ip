package dukeexception;

/**
 * Exception for handling invalid ids in task lists.
 */
public class NotFoundException extends DukeException {
    /**
     * Constructor for NotFoundException.
     * @param source Where the exception is raised.
     * @param reason Why the exception is raised.
     * @param err Throwable to pass to parent constructor.
     */
    public NotFoundException(String source, String reason, Throwable err) {
        super(String.format("Haiya cannot find in %s. %s", source, reason), err);
    }
}

package dukeexception;

/**
 * Exception for handling invalid index for task lists.
 */
public class NotFoundException extends DukeException {
    /**
     * Constructor for NotFoundException.
     * @param source Where the exception is raised.
     * @param reason Why the exception is raised.
     * @param err Throwable to pass to parent constructor.
     */
    public NotFoundException(String source, String reason, Throwable err) {
        super(String.format("Haiya nothing here in the %s lah. \n%s", source, reason), err);
    }
}
